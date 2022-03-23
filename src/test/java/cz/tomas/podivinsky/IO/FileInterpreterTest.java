package cz.tomas.podivinsky.IO;

import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.testUtil.TestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileInterpreterTest {

    private FileInterpreter fileInterpreter;

    @TempDir
    private Path tempDir;

    @BeforeEach
    void beforeEach() {
        fileInterpreter = new FileInterpreter();
    }

    @Test
    void getStructuredFileContent_validFile_thenMinAndMaxDistanceIsValid() throws IOException {
        String inputData = """
                100-101
                1N,3S,5W
                7N,5S,3W
                """;
        Path inputFile = TestUtility.createInputFile(tempDir, inputData);

        InputFileContent content = fileInterpreter.getStructuredFileContent(inputFile.toString());

        assertEquals(content.getMinDistance(), 100);
        assertEquals(content.getMaxDistance(), 101);
    }


    @Test
    void getStructuredFileContent_fileNotFound_throwsFileNotFoundException() {
        assertThrows(FileNotFoundException.class, ()->
                fileInterpreter.getStructuredFileContent("non_existing_file.txt"));
    }

    @Test
    void getStructuredFileContent_emptyFile_throwsIOException() {
        Path inputFile = tempDir.resolve("empty_file.txt");
        assertThrows(IOException.class, ()-> fileInterpreter.getStructuredFileContent(inputFile.toString()));
    }
}
