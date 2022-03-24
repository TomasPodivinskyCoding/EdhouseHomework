package cz.tomas.podivinsky.data;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Point {
    private int x;
    private int y;
    private int distance;
    private int driverNumber;
}
