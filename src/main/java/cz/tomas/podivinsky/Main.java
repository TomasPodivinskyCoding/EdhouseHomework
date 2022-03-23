package cz.tomas.podivinsky;

import cz.tomas.podivinsky.IO.FileInterpreter;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.algorithm.BentleyOttmann;
import cz.tomas.podivinsky.data.IntersectionPoint;

import java.awt.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        String chosenFileName = chooseFile();
        if (chosenFileName != null) {
            FileInterpreter fileInterpreter = new FileInterpreter();

            try {
                InputFileContent fileContent = fileInterpreter.getStructuredFileContent(chosenFileName);
                if (fileContent != null) {
                    BentleyOttmann bentleyOttmann = new BentleyOttmann();
                    IntersectionPoint intersection = bentleyOttmann.findIntersections(fileContent);
                    System.out.println("[" + intersection.getX() + "," + intersection.getY() + "]");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File with name: " + chosenFileName + " not found.");
            }
        }
    }

    private static String chooseFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Choose input file");
        dialog.setFilenameFilter((dir, name) -> name.endsWith(".txt"));
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dialog.dispose();
        if (dialog.getFile() == null) {
            System.out.println("You didn't choose any files");
            return null;
        }
        return dialog.getDirectory() + dialog.getFile();
    }
}
