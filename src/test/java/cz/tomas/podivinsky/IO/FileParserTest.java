package cz.tomas.podivinsky.IO;

import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.testUtil.TestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileParserTest {

    private FileParser fileParser;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void beforeEach() {
        fileParser = new FileParser();
    }

    @Test
    void getStructuredFileContent_validFile_thenMinAndMaxDistanceIsValid() throws IOException {
        InputFileContent content = fileParser.getStructuredFileContent(getDefaultInputFile().toString());

        assertEquals(content.getMinDistance(), 100);
        assertEquals(content.getMaxDistance(), 101);
    }

    @Test
    void getStructuredFileContent_validFile_thenNumberOfEventsIsValid() throws IOException {
        InputFileContent content = fileParser.getStructuredFileContent(getDefaultInputFile().toString());

        assertEquals(content.getAllPaths().size(), 8);
    }

    @Test
    void getStructuredFileContent_fileWithInvalidWayIdentifiers_thenDistanceIsValid() throws IOException {
        String inputData = """
                100-101
                3N,3P,3M
                3N,3Z,3L
                """;
        InputFileContent content = fileParser.getStructuredFileContent(TestUtility.createInputFile(tempDir, inputData).toString());
        content.getAllPaths().forEach(x-> {
            assertTrue(x.getPoint1().getDistance() < 4);
            assertTrue(x.getPoint2().getDistance() < 4);
        });
    }

    private Path getDefaultInputFile() throws IOException {
        String inputData = """
                100-101
                1N,3S,5W
                7N,5S,3W
                """;
        return TestUtility.createInputFile(tempDir, inputData);
    }

    @Test
    void getStructuredFileContent_nonExistingFile_throwsFileNotFoundException() {
        assertThrows(FileNotFoundException.class, ()->
                fileParser.getStructuredFileContent("non_existing_file.txt"));
    }

    @Test
    void getStructuredFileContent_emptyFile_throwsIOException() {
        Path inputFile = tempDir.resolve("empty_file.txt");
        assertThrows(IOException.class, ()-> fileParser.getStructuredFileContent(inputFile.toString()));
    }

}
