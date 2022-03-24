package cz.tomas.podivinsky;

import cz.tomas.podivinsky.IO.FileParser;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.algorithm.BentleyOttmannAlgorithm;
import cz.tomas.podivinsky.data.IntersectionPoint;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        FileParser fileParser = new FileParser();
        String chosenFileName = fileParser.chooseFile();
        if (chosenFileName == null) {
            System.err.println("You didn't choose a file!");
            return;
        }

        try {
            InputFileContent fileContent = fileParser.getStructuredFileContent(chosenFileName);

            BentleyOttmannAlgorithm bentleyOttmann = new BentleyOttmannAlgorithm();
            IntersectionPoint intersection = bentleyOttmann.findIntersections(fileContent);
            if (intersection != null) System.out.println("[" + intersection.getX() + "," + intersection.getY() + "]");
            else System.err.println("Couldn't find a good intersection for a pause!");
        } catch (IOException e) {
            handleIOException(e, chosenFileName);
        }
    }

    private static void handleIOException(IOException e, String fileName) {
        String startOfErrorMessage = "File with name: " + fileName;
        if (e instanceof FileNotFoundException) {
            System.err.println(startOfErrorMessage + " not found.");
        } else {
            System.err.println(startOfErrorMessage + " does not have the correct input format");
        }
    }
}
