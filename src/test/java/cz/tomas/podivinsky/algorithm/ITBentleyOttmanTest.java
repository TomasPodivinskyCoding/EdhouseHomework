package cz.tomas.podivinsky.algorithm;

import cz.tomas.podivinsky.IO.FileInterpreter;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.data.IntersectionPoint;
import cz.tomas.podivinsky.testUtil.TestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ITBentleyOttmanTest {

    private FileInterpreter fileInterpreter;
    private BentleyOttmann bentleyOttmann;

    @TempDir
    private Path tempDir;

    @BeforeEach
    public void beforeEach() {
        fileInterpreter = new FileInterpreter();
        bentleyOttmann = new BentleyOttmann();
    }

    @Test
    void findIntersections_horizontalIntersectionFromLeft_correctlyCalculatesDistance() throws IOException {
        String inputData = """
                6-10
                3N,4E
                3E,4N
                """;
        Path testFile = TestUtility.createInputFile(tempDir, inputData);

        InputFileContent content = fileInterpreter.getStructuredFileContent(testFile.toString());

        assertDoesNotThrow(()-> bentleyOttmann.findIntersections(content));
    }

    @Test
    void findIntersections_horizontalIntersectionFromRight_correctlyCalculatesDistance() throws IOException {
        String inputData = """
                6-10
                3N,4W
                3W,4N
                """;
        Path testFile = TestUtility.createInputFile(tempDir, inputData);

        InputFileContent content = fileInterpreter.getStructuredFileContent(testFile.toString());

        assertDoesNotThrow(()-> bentleyOttmann.findIntersections(content));
    }

    @Test
    void findIntersections_verticalIntersectionFromAbove_correctlyCalculatesDistance() throws IOException {
        String inputData = """
                6-10
                3W,4S
                3S,4W
                """;
        Path testFile = TestUtility.createInputFile(tempDir, inputData);

        InputFileContent content = fileInterpreter.getStructuredFileContent(testFile.toString());

        assertDoesNotThrow(()-> bentleyOttmann.findIntersections(content));
    }
}
