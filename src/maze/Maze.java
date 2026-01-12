import java.util.Arrays;

public class Maze {
    private int[][] maze;


    public Maze(int size) {
        maze = new int[size * 2 + 1][size * 2 + 1];

        for (int r = 0; r < maze.length; r++) {
            Arrays.fill(maze[r], 1);
        }
    }

    private
}
