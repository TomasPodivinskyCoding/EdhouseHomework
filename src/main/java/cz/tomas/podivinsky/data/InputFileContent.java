package cz.tomas.podivinsky.data;

import java.util.Queue;

public record InputFileContent(Queue<Event> allPaths, int minDistance,
                               int maxDistance) {

    public Queue<Event> getAllPaths() {
        return this.allPaths;
    }

    public int getMinDistance() {
        return this.minDistance;
    }

    public int getMaxDistance() {
        return this.maxDistance;
    }
}
