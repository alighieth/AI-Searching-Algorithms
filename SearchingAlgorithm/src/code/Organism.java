package code;

public class Organism {
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private int x;
    private int y;

    public Organism(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                this.y--;
                break;
            case DOWN:
                this.y++;
                break;
            case LEFT:
                this.x--;
                break;
            case RIGHT:
                this.x++;
                break;
        }
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
}