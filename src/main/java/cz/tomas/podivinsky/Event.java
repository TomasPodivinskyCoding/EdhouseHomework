package cz.tomas.podivinsky;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Event {
    private Point point1, point2;
    private int type;

    public Event(Point point1, Point point2, int type) {
        this.point1 = point1;
        this.point2 = point2;
        this.type = type;
    }
}
