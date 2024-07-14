package code;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int width;
    private int height;
    private List<Organism> organisms;
    private List<int[]> obstacles;

    public Grid(int width, int height, List<Organism> organisms, List<int[]> obstacles) {
        this.width = width;
        this.height = height;
        this.organisms = organisms;
        this.obstacles = obstacles;
    }

    public static Grid generateGrid(String input) {
        String[] parts = input.split(";");

        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);

        ArrayList<Organism> organisms = new ArrayList<>();
        if (!parts[2].isEmpty()) {
            String[] organismCoords = parts[2].split(",");
            for (int i = 0; i < organismCoords.length; i += 2) {
                int x = Integer.parseInt(organismCoords[i]);
                int y = Integer.parseInt(organismCoords[i + 1]);
                Organism organism = new Organism(x, y);
                organisms.add(organism);
            }
        }

        List<int[]> obstacles = new ArrayList<>();
        if (!parts[3].isEmpty()) {
            String[] obstacleCoords = parts[3].split(",");
            for (int i = 0; i < obstacleCoords.length; i += 2) {
                int x = Integer.parseInt(obstacleCoords[i]);
                int y = Integer.parseInt(obstacleCoords[i + 1]);
                obstacles.add(new int[] { x, y });
            }
        }

        return new Grid(width, height, organisms, obstacles);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Organism> getOrganisms() {
        return organisms;
    }

    public List<int[]> getObstacles() {
        return obstacles;
    }
}
