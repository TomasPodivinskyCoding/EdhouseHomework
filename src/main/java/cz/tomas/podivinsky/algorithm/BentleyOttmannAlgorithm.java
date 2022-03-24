package cz.tomas.podivinsky.algorithm;


import cz.tomas.podivinsky.data.Event;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.data.IntersectionPoint;
import cz.tomas.podivinsky.data.Point;

import java.util.*;

public class BentleyOttmannAlgorithm {

    private final ArrayList<Point> points = new ArrayList<>();
    private final HashMap<Point, Point> endingPoints = new HashMap<>();

    private int minDistance;
    private int maxDistance;

    public IntersectionPoint findIntersections(InputFileContent inputFileContent) {
        this.minDistance = inputFileContent.getMinDistance();
        this.maxDistance = inputFileContent.getMaxDistance();
        while (!inputFileContent.getAllPaths().isEmpty()) {
            Event currentLine = inputFileContent.getAllPaths().poll();
            assert currentLine != null;
            switch (currentLine.getType()) {
                case HORIZONTAL_START -> handleHorizontalStart(currentLine);
                case HORIZONTAL_END -> handleHorizontalEnd(currentLine);
                case VERTICAL -> {
                    for (Point point : points) {
                        if (validateIntersection(currentLine, point)) {
                            return new IntersectionPoint(currentLine.getPoint1().getX(), point.getY());
                        }
                    }
                }
            }
        }
        return null;
    }

    private void handleHorizontalStart(Event currentLine) {
        points.add(currentLine.getPoint1());
        endingPoints.put(currentLine.getPoint1(), currentLine.getPoint2());
    }

    private void handleHorizontalEnd(Event currentLine) {
        points.remove(currentLine.getPoint2());
        endingPoints.remove(currentLine.getPoint2());
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
        Point smallerDistancePoint = getSmallerDistancePoint(point, counterPoint);
        int horizontalDistance = smallerDistancePoint.getDistance();
        return horizontalDistance + getDistanceToIntersection(event.getPoint1().getX(), smallerDistancePoint.getX());
    }

    private int getVerticalDistance(Point point, Event c) {
        Point smallerDistancePoint = getSmallerDistancePoint(c.getPoint1(), c.getPoint2());
        int verticalDistance = smallerDistancePoint.getDistance();
        return verticalDistance + getDistanceToIntersection(point.getY(), smallerDistancePoint.getY());
    }

    private Point getSmallerDistancePoint(Point point1, Point point2) {
        if (point1.getDistance() > point2.getDistance()) {
            return point2;
        } else {
            return point1;
        }
    }
    private int getDistanceToIntersection(int num1, int num2) {
        if (num1 > num2) {
            return Math.abs(num1 - num2);
        } else {
            return Math.abs(num2 - num1);
        }
    }

}