package cz.tomas.podivinsky.algorithm;

import cz.tomas.podivinsky.data.InputFileContent;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BentleyOttmanAlgorithmTest {

    @Test
    void findIntersections_emptyQueue_isNull() {
        BentleyOttmannAlgorithm bentleyOttmann = new BentleyOttmannAlgorithm();
        assertNull(bentleyOttmann.findIntersections(new InputFileContent(new PriorityQueue<>(), 0, 0)));
    }

}
