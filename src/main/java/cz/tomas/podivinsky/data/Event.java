package cz.tomas.podivinsky.data;

import cz.tomas.podivinsky.data.enums.EventType;

public record Event(Point point1, Point point2,
                    EventType type) {

    public Point getPoint1() {
        return this.point1;
    }

    public Point getPoint2() {
        return this.point2;
    }

    public EventType getType() {
        return this.type;
    }
}
