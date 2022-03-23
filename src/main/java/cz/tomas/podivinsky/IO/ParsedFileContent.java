package cz.tomas.podivinsky.IO;

import cz.tomas.podivinsky.data.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.PriorityQueue;
import java.util.Queue;

@AllArgsConstructor
@Getter
@Setter
public class ParsedFileContent {
    private final Queue<Event> allPaths;
    private int minDistance;
    private int maxDistance;
}
