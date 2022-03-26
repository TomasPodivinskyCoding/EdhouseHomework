package cz.tomas.podivinsky.data;

public class Point {
    private int x;
    private int y;
    private int distance;
    private int driverNumber;

    public Point(int x, int y, int distance, int driverNumber) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.driverNumber = driverNumber;
    }

    public Point() {
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getDriverNumber() {
        return this.driverNumber;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setDriverNumber(int driverNumber) {
        this.driverNumber = driverNumber;
    }
}
