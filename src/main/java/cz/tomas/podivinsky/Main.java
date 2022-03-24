package cz.tomas.podivinsky;

import cz.tomas.podivinsky.IO.FileInterpreter;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.algorithm.BentleyOttmannAlgorithm;
import cz.tomas.podivinsky.data.IntersectionPoint;

import java.awt.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        String chosenFileName = chooseFile();
        if (chosenFileName == null) return;

        try {
            FileInterpreter fileInterpreter = new FileInterpreter();
            InputFileContent fileContent = fileInterpreter.getStructuredFileContent(chosenFileName);

            BentleyOttmannAlgorithm bentleyOttmann = new BentleyOttmannAlgorithm();
            IntersectionPoint intersection = bentleyOttmann.findIntersections(fileContent);
            System.out.println("[" + intersection.getX() + "," + intersection.getY() + "]");
        } catch (IOException e) {
            handleIOException(e, chosenFileName);
        }
    }

    private static void handleIOException(IOException e, String chosenFileName) {
        String startOfErrorMessage = "File with name: " + chosenFileName;
        if (e instanceof FileNotFoundException) {
            System.out.println(startOfErrorMessage + " not found.");
        } else {
            System.out.println(startOfErrorMessage + " does not have the correct input format");
        }
    }

    private static String chooseFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Choose input file");
        dialog.setFilenameFilter((dir, name) -> name.endsWith(".txt"));
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dialog.dispose();
        if (dialog.getFile() == null) {
            System.out.println("You didn't choose a file!");
            return null;
        }
        return dialog.getDirectory() + dialog.getFile();
    }
}
