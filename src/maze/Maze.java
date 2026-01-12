package maze;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Maze {
    private final int[][] maze;
    private final int size;


    public Maze(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size must be >= 1");
        }
        this.size = size;
        maze = new int[size * 2 + 1][size * 2 + 1];

        for (int[] row : maze) {
            Arrays.fill(row, 1);
        }

        generateMaze();
    }

    private void generateMaze() {
        carvePath(new Vec2(1, 1));
        maze[size * 2 - 1][size * 2 - 1] = 2;
    }

    private void carvePath(Vec2 c) {
        maze[c.row][c.column] = 0;

        ArrayList<Vec2> neighbors = new ArrayList<>();

        for (Vec2 dir : Vec2.DIRECTIONS) {
            Vec2 cc = c.add(dir.multiply(new Vec2(2, 2)));
            if (0 <= cc.row && cc.row < size * 2 + 1 &&
                    0 <= cc.column && cc.column < size * 2 + 1 &&
                    maze[cc.row][cc.column] == 1) {
                neighbors.add(cc);
            }
        }

        Collections.shuffle(neighbors);

        for (Vec2 pos : neighbors) {
            if (maze[pos.row][pos.column] == 1) {
                maze[c.row + (pos.row - c.row) / 2]
                        [c.column + (pos.column - c.column) / 2] = 0;
                carvePath(pos);
            }
        }
    }

    public void printMaze() {
        for (int[] row : maze) {
            for (int col : row) {
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
