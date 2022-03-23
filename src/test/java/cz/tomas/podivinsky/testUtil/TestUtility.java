package cz.tomas.podivinsky.testUtil;

import org.junit.jupiter.api.io.TempDir;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

public class TestUtility {

    public static Path createInputFile(Path tempDir, String data) throws IOException {
        Path inputFile = tempDir.resolve(getRandomFileName());
        FileWriter writer = new FileWriter(inputFile.toString());
        writer.write(data);
        writer.close();
        return inputFile;
    }

    private static String getRandomFileName() {
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
