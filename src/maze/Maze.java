package maze;

import java.util.ArrayList;   // List for neighbor cells
import java.util.Arrays;      // Utility for filling arrays
import java.util.Collections; // Utility for shuffling lists

public class Maze {
    private final int[][] maze; // Grid storing maze cells
    private final int size;     // Logical maze size (without walls)

    public Maze(int size) {
        if (size < 1) { // Ensure maze size is valid
            throw new IllegalArgumentException("size must be >= 1");
        }
        this.size = size;
        maze = new int[size * 2 + 1][size * 2 + 1]; // Create a grid with walls

        for (int[] row : maze) {
            Arrays.fill(row, 1); // Fill the grid with walls
        }

        generateMaze(); // Build the maze layout
    }

    private void generateMaze() {
        carvePath(new Vec2(1, 1)); // Start carving at (1,1)
        maze[size * 2 - 1][size * 2 - 1] = 2; // Mark maze end as 2
    }

    private void carvePath(Vec2 c) {
        maze[c.row][c.column] = 0; // Open the current cell

        ArrayList<Vec2> neighbors = new ArrayList<>(); // Stores candidate neighbors

        for (Vec2 dir : Vec2.DIRECTIONS) { // Check four directions
            Vec2 cc = c.add(dir.multiply(new Vec2(2, 2))); // Jump two cells ahead
            if (0 <= cc.row && cc.row < size * 2 + 1 &&    // Check row bounds
                0 <= cc.column && cc.column < size * 2 + 1 && // Check column bounds
                maze[cc.row][cc.column] == 1) { // Only visit walls
                neighbors.add(cc); // Add valid neighbor
            }
        }

        Collections.shuffle(neighbors); // Randomize neighbor order

        for (Vec2 pos : neighbors) { // Visit each neighbor
            if (maze[pos.row][pos.column] == 1) { // Skip if already carved
                maze[c.row + (pos.row - c.row) / 2]              // Row between cells
                    [c.column + (pos.column - c.column) / 2] = 0; // Open wall between
                carvePath(pos); // Recurse into a neighbor
            }
        }
    }

    public void printMaze() {
        for (int[] row : maze) { // Loop through each row
            for (int col : row) { // Loop through each cell
                if (col == 1) { // Wall cell
                    System.out.print("██");
                } else if (col == 2) { // End cell
                    System.out.print("▒▒");
                } else { // Open passage
                    System.out.print("  ");
                }
            }
            System.out.println(); // New line per row
        }
    }
}
