package cz.tomas.podivinsky.IO;

import cz.tomas.podivinsky.data.Event;
import cz.tomas.podivinsky.data.InputFileContent;
import cz.tomas.podivinsky.data.Point;
import cz.tomas.podivinsky.data.enums.EventType;

import java.awt.*;
import java.io.*;
import java.util.*;

public class FileParser {

    private BufferedReader reader;
    private final Queue<Event> allPaths = new PriorityQueue<>(Comparator.comparingInt(o -> o.getPoint1().getX()));
    private int currentDistance;
    private int minDistance;
    private int maxDistance;
    private int driverNumber;

    public String chooseFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Choose input file");
        dialog.setFilenameFilter((dir, name) -> name.endsWith(".txt"));
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        dialog.dispose();
        // check to return null instead of concatenated nullnull value
        if (dialog.getDirectory() == null || dialog.getFile() == null) {
            return null;
        }
        return dialog.getDirectory() + dialog.getFile();
    }

    public InputFileContent getStructuredFileContent(String chosenFile) throws IOException {
        reader = new BufferedReader(new FileReader(chosenFile));
        getMinAndMaxDistance();
        driverNumber = 0;
        readDriverData(); // first driver
        driverNumber = 1;
        readDriverData(); // second driver
        reader.close();

        return new InputFileContent(allPaths, minDistance, maxDistance);
    }

    private void getMinAndMaxDistance() throws IOException {
        String minAndMaxDistanceForRest = reader.readLine();
        if (minAndMaxDistanceForRest == null) throw new IOException();
        String[] minAndMaxDistanceForRestSplit = minAndMaxDistanceForRest.split("-");
        minDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[0]);
        maxDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[1]);
    }

    private void readDriverData() throws IOException {
        currentDistance = 0;

        Point startingCoordinates = new Point(0, 0, 0, driverNumber);
        for (String instruction : splitLine()) {
            Point finalCoordinates = parseInstructionToNewPoint(instruction, startingCoordinates);
            currentDistance = finalCoordinates.getDistance();
            addEvent(startingCoordinates, finalCoordinates);
            startingCoordinates = finalCoordinates;
        }
    }

    private String[] splitLine() throws IOException {
        String line = reader.readLine();
        if (line == null) throw new IOException();
        return line.split(",");
    }

    private Point parseInstructionToNewPoint(String instruction, Point startingPoint) {
        int distanceToMove = Integer.parseInt(instruction.substring(0, instruction.length() - 1));
        currentDistance += distanceToMove;
        int x = startingPoint.getX();
        int y = startingPoint.getY();
        switch (instruction.charAt(instruction.length() - 1)) {
            case 'N'-> y += distanceToMove;
            case 'S'-> y -= distanceToMove;
            case 'E'-> x += distanceToMove;
            case 'W'-> x -= distanceToMove;
            default -> currentDistance -= distanceToMove; // do not move on unknown directions
        }
        return new Point(x, y, currentDistance, driverNumber);
    }

    private void addEvent(Point startingCoordinates, Point finalCoordinates) {
        if (startingCoordinates.getX() == finalCoordinates.getX()) {
            allPaths.add(new Event(startingCoordinates, finalCoordinates, EventType.VERTICAL));
        } else if (startingCoordinates.getX() < finalCoordinates.getX()) {
            addHorizontalEvents(startingCoordinates, finalCoordinates);
        } else {
            addHorizontalEvents(finalCoordinates, startingCoordinates);
        }
    }

    private void addHorizontalEvents(Point point1, Point point2) {
        allPaths.add(new Event(point1, point2, EventType.HORIZONTAL_START));
        allPaths.add(new Event(point2, point1, EventType.HORIZONTAL_END));
    }
}
