package cz.tomas.podivinsky.IO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FileInterpreterTest {

    private FileInterpreter fileInterpreter;

    @BeforeEach
    void setUp() {
        fileInterpreter = new FileInterpreter();
    }

    @Test
    void getStructuredFileContent_fileNotFound_throwsException() {
        assertThrows(FileNotFoundException.class, ()->
                fileInterpreter.getStructuredFileContent("non_existing_file.txt"));
    }

}
