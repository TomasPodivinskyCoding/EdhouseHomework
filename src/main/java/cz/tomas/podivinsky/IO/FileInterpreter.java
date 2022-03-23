package cz.tomas.podivinsky.IO;

import cz.tomas.podivinsky.algorithm.BentleyOttmann;
import cz.tomas.podivinsky.data.Event;
import cz.tomas.podivinsky.data.Point;
import cz.tomas.podivinsky.data.enums.EventType;

import java.io.*;
import java.util.*;

public class FileInterpreter {

    private BufferedReader reader;
    private final Queue<Event> allPaths = new PriorityQueue<>(new EventComparator());
    private int minDistance;
    private int maxDistance;

    public ParsedFileContent getStructuredFileContent(String chosenFile) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(chosenFile));
        try {
            getMinAndMaxDistance();
            readDriverData(0); // first driver
            readDriverData(1); // second driver
            reader.close();

            return new ParsedFileContent(allPaths, minDistance, maxDistance);
        } catch (IOException e) {
            System.out.println("Couldn't read the given file properly");
        }
        return null;
    }

    private void getMinAndMaxDistance() throws IOException {
        String minAndMaxDistanceForRest = reader.readLine();
        if (minAndMaxDistanceForRest == null) throw new IOException();
        String[] minAndMaxDistanceForRestSplit = minAndMaxDistanceForRest.split("-");
        minDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[0]);
        maxDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[1]);
    }

    private void readDriverData(int driverNumber) throws IOException {
        int distance = 0;
        String line = reader.readLine();
        if (line == null) throw new IOException();
        String[] instructions = line.split(",");

        Point startingCoordinates = new Point(0, 0, 0, driverNumber);
        for (String instruction : instructions) {
            int distanceToMove = Integer.parseInt(instruction.substring(0, instruction.length() - 1));
            distance += distanceToMove;
            int x = startingCoordinates.getX();
            int y = startingCoordinates.getY();
            switch (instruction.charAt(instruction.length() - 1)) {
                case 'N'-> y += distanceToMove;
                case 'S'-> y -= distanceToMove;
                case 'E'-> x += distanceToMove;
                case 'W'-> x -= distanceToMove;
                default -> distance -= distanceToMove;
            }
            Point finalCoordinates = new Point(x, y, distance, driverNumber);
            addEvent(startingCoordinates, finalCoordinates);
            startingCoordinates = finalCoordinates;
        }
    }

    private void addEvent(Point startingCoordinates, Point finalCoordinates) {
        if (startingCoordinates.getX() == finalCoordinates.getX()) {
            allPaths.add(new Event(startingCoordinates, finalCoordinates, EventType.VERTICAL));
            return;
        }
        if (finalCoordinates.getX() < startingCoordinates.getX()) {
            allPaths.add(new Event(startingCoordinates, finalCoordinates, EventType.HORIZONTAL_END));
            allPaths.add(new Event(finalCoordinates, startingCoordinates, EventType.HORIZONTAL_START));
        } else {
            allPaths.add(new Event(startingCoordinates, finalCoordinates, EventType.HORIZONTAL_START));
            allPaths.add(new Event(finalCoordinates, startingCoordinates, EventType.HORIZONTAL_END));
        }
    }

    public static class EventComparator implements Comparator<Event> {
        @Override
        public int compare(Event o1, Event o2) {
            return Integer.compare(o1.getPoint1().getX(), o2.getPoint1().getX());
        }
    }
}
