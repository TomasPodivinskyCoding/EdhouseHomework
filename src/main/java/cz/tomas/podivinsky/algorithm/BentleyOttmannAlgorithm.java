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
                        if (validateIntersection(currentLine, point))
                            return new IntersectionPoint(currentLine.getPoint1().getX(), point.getY());
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

    private boolean validateIntersection(Event lineEvent, Point point) {
        if (point.getDriverNumber() == lineEvent.getPoint1().getDriverNumber()) return false;

        int biggerY = Math.max(lineEvent.getPoint2().getY(), lineEvent.getPoint1().getY());
        int smallerY = Math.min(lineEvent.getPoint2().getY(), lineEvent.getPoint1().getY());
        return (point.getY() > smallerY && point.getY() < biggerY) && checkDistance(point, lineEvent);
    }

    private boolean checkDistance(Point point, Event lineEvent) {
        int horizontalDistance = getHorizontalDistance(point, lineEvent);
        int verticalDistance = getVerticalDistance(point, lineEvent);
        return horizontalDistance >= minDistance && horizontalDistance <= maxDistance &&
                verticalDistance >= minDistance && verticalDistance <= maxDistance;
    }

    private int getHorizontalDistance(Point point, Event event) {
        Point counterPoint = endingPoints.get(point);
        Point smallerDistancePoint = getSmallerDistancePoint(point, counterPoint);
        return smallerDistancePoint.getDistance() + getDistanceToIntersection(event.getPoint1().getX(), smallerDistancePoint.getX());
    }

    private int getVerticalDistance(Point point, Event event) {
        Point smallerDistancePoint = getSmallerDistancePoint(event.getPoint1(), event.getPoint2());
        return smallerDistancePoint.getDistance() + getDistanceToIntersection(point.getY(), smallerDistancePoint.getY());
    }

    private Point getSmallerDistancePoint(Point point1, Point point2) {
        if (point1.getDistance() > point2.getDistance()) {
            return point2;
        } else {
            return point1;
        }
    }

    private int getDistanceToIntersection(int num1, int num2) {
        return num1 > num2 ? Math.abs(num1 - num2) : Math.abs(num2 - num1);
    }

}