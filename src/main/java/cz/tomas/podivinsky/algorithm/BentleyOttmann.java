package cz.tomas.podivinsky.algorithm;


import cz.tomas.podivinsky.data.Event;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.data.IntersectionPoint;
import cz.tomas.podivinsky.data.Point;
import cz.tomas.podivinsky.data.enums.EventType;

import java.util.*;

public class BentleyOttmann {

    private final ArrayList<Point> points = new ArrayList<>();
    private final HashMap<Point, Point> endingPoints = new HashMap<>();

    private int minDistance;
    private int maxDistance;

    public IntersectionPoint findIntersections(InputFileContent inputFileContent) {
        this.minDistance = inputFileContent.getMinDistance();
        this.maxDistance = inputFileContent.getMaxDistance();
        while (!inputFileContent.getAllPaths().isEmpty()) {
            Event c = inputFileContent.getAllPaths().poll();
            assert c != null;
            switch (c.getType()) {
                case HORIZONTAL_START -> {
                    points.add(c.getPoint1());
                    endingPoints.put(c.getPoint1(), c.getPoint2());
                }
                case HORIZONTAL_END -> {
                    points.remove(c.getPoint2());
                    endingPoints.put(c.getPoint2(), c.getPoint1());
                }
                case VERTICAL -> {
                    for (Point point : points) {
                        if (validateIntersection(c, point)) {
                            return new IntersectionPoint(c.getPoint1().getX(), point.getY());
                        }
                    }
                }
            }
        }
        throw new RuntimeException("Coudln't find a good intersection for a pause!");
    }

    private boolean validateIntersection(Event c, Point point) {
        if (point.getDriverNumber() == c.getPoint1().getDriverNumber()) return false;

        int biggerY = Math.max(c.getPoint2().getY(), c.getPoint1().getY());
        int smallerY = Math.min(c.getPoint2().getY(), c.getPoint1().getY());
        if (point.getY() < biggerY && point.getY() > smallerY) {
            int horizontalDistance = getHorizontalDistance(point, c);
            int verticalDistance = getVerticalDistance(point, c);
            return horizontalDistance >= minDistance && horizontalDistance <= maxDistance &&
                    verticalDistance >= minDistance && verticalDistance <= maxDistance;
        }
        return false;
    }

    private int getHorizontalDistance(Point point, Event event) {
        Point counterPoint = endingPoints.get(point);
        Point smallerDistancePoint;
        if (point.getDistance() > counterPoint.getDistance()) {
            smallerDistancePoint = counterPoint;
        } else {
            smallerDistancePoint = point;
        }
        int horizontalDistance = smallerDistancePoint.getDistance();
        if (event.getPoint1().getX() > smallerDistancePoint.getX()) {
            horizontalDistance += Math.abs(event.getPoint1().getX() - smallerDistancePoint.getX());
        } else {
            horizontalDistance += Math.abs(smallerDistancePoint.getX() - event.getPoint1().getX());
        }
        return horizontalDistance;
    }

    private int getVerticalDistance(Point point, Event c) {
        Point smallerDistancePoint;
        if (c.getPoint1().getDistance() > c.getPoint2().getDistance()) {
            smallerDistancePoint = c.getPoint2();
        } else {
            smallerDistancePoint = c.getPoint1();
        }
        int verticalDistance = smallerDistancePoint.getDistance();
        if (point.getY() > smallerDistancePoint.getY()) {
            verticalDistance += Math.abs(point.getY() - smallerDistancePoint.getY());
        } else {
            verticalDistance += Math.abs(smallerDistancePoint.getY() - point.getY());
        }
        return verticalDistance;
    }
}