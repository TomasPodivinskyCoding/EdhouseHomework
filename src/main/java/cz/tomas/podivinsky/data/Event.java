package cz.tomas.podivinsky.data;

import cz.tomas.podivinsky.data.enums.EventType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private Point point1, point2;
    private EventType type;

    public Event(Point point1, Point point2, EventType type) {
        this.point1 = point1;
        this.point2 = point2;
        this.type = type;
    }
}
