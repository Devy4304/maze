package maze;

import java.util.ArrayList;   // List for neighbor cells
import java.util.Arrays;      // Utility for filling arrays
import java.util.Collections; // Utility for shuffling lists

public class Maze {
    private final int[][] maze; // Grid storing maze cells
    private final int size;     // Logical maze size (without walls)
    private Vec2 playerPos;
    private int playerDirection;
    private boolean win;

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

        playerPos = new Vec2(1,1);
        playerDirection = 2;
        win = false;
    }

    private void generateMaze() {
        carvePath(new Vec2(1, 1)); // Start carving at (1,1)
        maze[size * 2 - 1][size * 2 - 1] = 2; // Mark maze end as 2
    }

    private void carvePath(Vec2 c) {
        maze[c.row][c.column] = 0; // Open the current cell

        ArrayList<Vec2> neighbors = new ArrayList<>(); // Stores candidate neighbors

        for (Vec2 dir : Vec2.DIRECTIONS) { // Check four directions
            Vec2 cc = c.add(dir.multiply(2)); // Jump two cells ahead
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

    public void movePlayerForward() {
        Vec2 dir = Vec2.DIRECTIONS[Math.floorMod(playerDirection, 4)];
        Vec2 newPlayerPos = playerPos.add(dir);
        if (maze[newPlayerPos.row][newPlayerPos.column] == 0) {
            playerPos = newPlayerPos;
        } else if (maze[newPlayerPos.row][newPlayerPos.column] == 2) {
            win = true;
        }
    }

    public boolean hasWon() {
        return win;
    }

    public void rotatePlayer(Vec2.Rotation rotator) {
        playerDirection = Math.floorMod(playerDirection + rotator.delta() + 16, 4);
    }

    public void printMaze() {
        for (int row = 0; row < maze.length; row++) { // Loop through each row
            for (int col = 0; col < maze[row].length; col++) { // Loop through each cell
                if (row == playerPos.row && col == playerPos.column) {
                    System.out.print("**");
                } else if (row == playerPos.add(Vec2.DIRECTIONS[playerDirection]).row && col == playerPos.add(Vec2.DIRECTIONS[playerDirection]).column) {
                    if (maze[row][col] == 1) {
                        System.out.print("##");
                    } else {
                        System.out.print("^^");
                    }
                } else if (maze[row][col] == 1) { // Wall cell
                    System.out.print("██");
                } else if (maze[row][col] == 2) { // End cell
                    System.out.print("▒▒");
                } else { // Open passage
                    System.out.print("  ");
                }
            }
            System.out.println(); // New line per row
        }
    }

    public void renderViewport() {
        int[][] viewport = new int[4][3];
        for (int row = 0; row <= 3; row++) {
            for (int col = -1; col <= 1; col++) {
                int dir = Math.floorMod(playerDirection, 4);

                Vec2 forward = Vec2.DIRECTIONS[dir].multiply(row);
                Vec2 right   = Vec2.DIRECTIONS[(dir + 1) % 4].multiply(col);
                Vec2 cell    = playerPos.add(forward).subtract(right);

                if (cell.row >= 0 && cell.row < maze.length
                        && cell.column >= 0 && cell.column < maze[0].length) {
                    viewport[3 - row][col + 1] = maze[cell.row][cell.column];
                } else {
                    viewport[3 - row][col + 1] = 0;
                }
            }
        }

        // for (int row[] : viewport) {
        //     for (int col : row) {
        //         System.out.print(col);
        //     }
        //     System.out.println();
        // }

        Renderer.render(viewport);
    }
}
