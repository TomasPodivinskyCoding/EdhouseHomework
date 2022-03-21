package cz.tomas.podivinsky;

import java.io.*;

public class FileInterpreter {

    private BufferedReader reader;

    public void getStructuredFileContent(File chosenFile) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(chosenFile));
        try {
            getMinAndMaxDistance();
        } catch (IOException e) {
            System.out.println("Couldn't read the given file properly");
        }
    }

    private MinAndMaxDistance getMinAndMaxDistance() throws IOException {
        String minAndMaxDistanceForRest = reader.readLine();
        String[] minAndMaxDistanceForRestSplit = minAndMaxDistanceForRest.split("-");
        int minDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[0]);
        int maxDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[1]);
        return new MinAndMaxDistance(minDistance, maxDistance);
    }
}
