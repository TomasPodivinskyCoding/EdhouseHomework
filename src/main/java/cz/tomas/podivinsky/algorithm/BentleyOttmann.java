package cz.tomas.podivinsky.algorithm;


import cz.tomas.podivinsky.data.Event;
import cz.tomas.podivinsky.data.Point;

import java.util.*;

public class BentleyOttmann {

    private final Queue<Event> events;
    private final ArrayList<Point> points = new ArrayList<>();
    private final HashMap<Point, Point> endingPoints = new HashMap<>();

    public BentleyOttmann(Queue<Event> events) {
        this.events = events;
    }

    public void findIntersections(int minDistance, int maxDistance) {
        while (!events.isEmpty()) {
            Event c = events.poll();
            assert c != null;
            if (c.getType() == 0) {
                points.add(c.getPoint1());
                endingPoints.put(c.getPoint1(), c.getPoint2());
            } else if (c.getType() == 1) {
                points.remove(c.getPoint2());
                endingPoints.put(c.getPoint2(), c.getPoint1());
            } else {
                for (Point point : points) {
                    if (point.getDriverNumber() == c.getPoint1().getDriverNumber()) continue;
                    int biggerY = Math.max(c.getPoint2().getY(), c.getPoint1().getY());
                    int smallerY = Math.min(c.getPoint2().getY(), c.getPoint1().getY());
                    if (point.getY() < biggerY && point.getY() > smallerY) {
                        int horizontalDistance = getHorizontalDistance(point, c);
                        int verticalDistance = getVerticalDistance(point, c);
                        if (horizontalDistance > minDistance && horizontalDistance < maxDistance &&
                            verticalDistance > minDistance && verticalDistance < maxDistance) {
                            System.out.println("[" + c.getPoint1().getX() + "," + point.getY() + "]");
                            return;
                        }
                    }
                }
            }
        }
        throw new RuntimeException("Coudln't find a good intersection for a pause!");
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