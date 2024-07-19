package code;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    public static Cell[][] genGrid(String input) {
        String[] parts = input.split(";");

        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        Cell[][] grid = new Cell[width][height];

        if (!parts[2].isEmpty()) {
            String[] organismCoords = parts[2].split(",");
            for (int i = 0; i < organismCoords.length; i += 2) {
                int x = Integer.parseInt(organismCoords[i]);
                int y = Integer.parseInt(organismCoords[i + 1]);
                grid[x][y] = new Organism(x, y);
            }
        }

        if (!parts[3].isEmpty()) {
            String[] obstacleCoords = parts[3].split(",");
            for (int i = 0; i < obstacleCoords.length; i += 2) {
                int x = Integer.parseInt(obstacleCoords[i]);
                int y = Integer.parseInt(obstacleCoords[i + 1]);
                grid[x][y] = new Obstacles(x, y);
            }
        }
        return grid;
    }

    public static List<Cell> getOrganisms(Cell[][] grid) {
        List<Cell> organisms = new ArrayList<>();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell instanceof Organism) {
                    organisms.add(cell);
                }
            }
        }
        return organisms;
    }

    public static List<Cell> getObstacles(Cell[][] grid) {
        List<Cell> obstacles = new ArrayList<>();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell instanceof Obstacles) {
                    obstacles.add(cell);
                }
            }
        }
        return obstacles;
    }
}
