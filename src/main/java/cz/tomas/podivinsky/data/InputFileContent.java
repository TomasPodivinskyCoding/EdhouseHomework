package cz.tomas.podivinsky.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Queue;

@AllArgsConstructor
@Getter
public class InputFileContent {
    private final Queue<Event> allPaths;
    private int minDistance;
    private int maxDistance;
}
