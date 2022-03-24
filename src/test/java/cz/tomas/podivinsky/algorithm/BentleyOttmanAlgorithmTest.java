package cz.tomas.podivinsky.algorithm;

import cz.tomas.podivinsky.data.InputFileContent;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BentleyOttmanAlgorithmTest {

    @Test
    void findIntersections_emptyQueue_throwsException() {
        BentleyOttmannAlgorithm bentleyOttmann = new BentleyOttmannAlgorithm();
        assertThrows(RuntimeException.class, ()-> bentleyOttmann.findIntersections(new InputFileContent(new PriorityQueue<>(), 0, 0)));
    }

}
