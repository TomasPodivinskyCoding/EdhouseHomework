package cz.tomas.podivinsky;

import cz.tomas.podivinsky.IO.FileInterpreter;
import cz.tomas.podivinsky.IO.ParsedFileContent;
import cz.tomas.podivinsky.algorithm.BentleyOttmann;

import java.awt.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        String chosenFileName = chooseFile();
        FileInterpreter fileInterpreter = new FileInterpreter();

        try {
            ParsedFileContent fileContent = fileInterpreter.getStructuredFileContent(chosenFileName);
            if (fileContent != null) {
                BentleyOttmann bentleyOttmann = new BentleyOttmann(fileContent.getAllPaths());
                bentleyOttmann.findIntersections(fileContent.getMinDistance(), fileContent.getMaxDistance());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File with name: " + chosenFileName + " not found.");
        }
    }

    private static String chooseFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Choose input file");
        dialog.setFilenameFilter((dir, name) -> name.endsWith(".txt"));
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dialog.dispose();
        return dialog.getDirectory() + dialog.getFile();
    }

}
