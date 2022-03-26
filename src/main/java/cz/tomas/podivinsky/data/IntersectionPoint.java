package cz.tomas.podivinsky.data;

public record IntersectionPoint(int x, int y) {

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
