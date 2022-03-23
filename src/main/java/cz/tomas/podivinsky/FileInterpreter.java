package cz.tomas.podivinsky;

import java.io.*;
import java.util.*;

public class FileInterpreter {

    private BufferedReader reader;
    private final Queue<Event> allPaths = new PriorityQueue<>(new EventComparator());
    private int minDistance;
    private int maxDistance;

    public void getStructuredFileContent(File chosenFile) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(chosenFile));
        try {
            getMinAndMaxDistance();
            readDriverData(0); // first driver
            readDriverData(1); // second driver
            reader.close();

            BentleyOttmann algorithm = new BentleyOttmann(allPaths);
            algorithm.findIntersections(minDistance, maxDistance);
        } catch (IOException e) {
            System.out.println("Couldn't read the given file properly");
        }
    }

    private void getMinAndMaxDistance() throws IOException {
        String minAndMaxDistanceForRest = reader.readLine();
        String[] minAndMaxDistanceForRestSplit = minAndMaxDistanceForRest.split("-");
        minDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[0]);
        maxDistance = Integer.parseInt(minAndMaxDistanceForRestSplit[1]);
    }

    private void readDriverData(int driverNumber) throws IOException {
        int distance = 0;
        String line = reader.readLine();
        String[] instructions = line.split(",");

        Point startingCoordinates = new Point(0, 0, 0, driverNumber);
        for (String instruction : instructions) {
            int distanceToMove = Integer.parseInt(instruction.substring(0, instruction.length() - 1));
            distance += distanceToMove;
            int x = startingCoordinates.getX();
            int y = startingCoordinates.getY();
            char currentWay = instruction.charAt(instruction.length() - 1);
            switch (currentWay) {
                case 'N'-> y += distanceToMove;
                case 'S'-> y -= distanceToMove;
                case 'E'-> x += distanceToMove;
                case 'W'-> x -= distanceToMove;
            }
            Point finalCoordinates = new Point(x, y, distance, driverNumber);

            if (startingCoordinates.getX() == finalCoordinates.getX()) {
                allPaths.add(new Event(startingCoordinates, finalCoordinates, 2));
            } else {
                if (finalCoordinates.getX() < startingCoordinates.getX()) {
                    allPaths.add(new Event(startingCoordinates, finalCoordinates, 1));
                    allPaths.add(new Event(finalCoordinates, startingCoordinates, 0));
                } else {
                    allPaths.add(new Event(startingCoordinates, finalCoordinates, 0));
                    allPaths.add(new Event(finalCoordinates, startingCoordinates, 1));
                }
            }
            startingCoordinates = finalCoordinates;
        }
    }

    public static class EventComparator implements Comparator<Event> {
        @Override
        public int compare(Event o1, Event o2) {
            return Integer.compare(o1.getPoint1().getX(), o2.getPoint1().getX());
        }
    }
}
