package code;

public class Organism implements Cell {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private int x;
    private int y;
    private int weight = 1;

    public Organism(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newPosition) {
        this.x = newPosition;
    }

    public void setY(int newPosition) {
        this.y = newPosition;
    }

    public int getSize() {
        return weight;
    }

    public int setSize() {
        return weight++;
    }
}