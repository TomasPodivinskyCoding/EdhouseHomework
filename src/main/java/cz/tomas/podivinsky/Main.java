package cz.tomas.podivinsky;

import java.awt.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        String chosenFileName = chooseFile();
        File chosenFile = new File(chosenFileName);
        FileInterpreter fileInterpreter = new FileInterpreter();

        try {
            fileInterpreter.getStructuredFileContent(chosenFile);
        } catch (FileNotFoundException e) {
            System.out.println("File with name: " + chosenFileName + " not found.");
        }
    }

    private static String chooseFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Choose input file");
        dialog.setFilenameFilter((dir, name) -> name.endsWith(".txt"));
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        return dialog.getDirectory() + dialog.getFile();
    }

}
