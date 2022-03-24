package cz.tomas.podivinsky.data;

import cz.tomas.podivinsky.data.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Event {
    private Point point1, point2;
    private EventType type;
}
