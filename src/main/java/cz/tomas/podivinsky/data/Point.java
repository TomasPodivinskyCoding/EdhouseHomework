package cz.tomas.podivinsky.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Point {
    private int x;
    private int y;
    private int distance;
    private int driverNumber;
}
