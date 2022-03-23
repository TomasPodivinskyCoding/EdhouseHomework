package cz.tomas.podivinsky.algorithm;

import cz.tomas.podivinsky.algorithm.BentleyOttmann;
import cz.tomas.podivinsky.data.Event;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.data.Point;
import cz.tomas.podivinsky.data.enums.EventType;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BentleyOttmanTest {

    @Test
    void findIntersections_emptyQueue_throwsException() {
        BentleyOttmann bentleyOttmann = new BentleyOttmann();
        assertThrows(RuntimeException.class, ()-> bentleyOttmann.findIntersections(new InputFileContent(new PriorityQueue<>(), 0, 0)));
    }

}
