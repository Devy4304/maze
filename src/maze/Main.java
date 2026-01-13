package maze;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    private static boolean textFoundInList(String[] list, String text) {
        for (String item : list) {
            if (item.equals(text.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static void getPlayerInput(Maze maze) {
        System.out.print(" > ");
        String input = scanner.nextLine();

        String[] forward = {"forward", "w", "mv", "f"};
        String[] right = {"right", "d", "r", "e"};
        String[] left = {"left", "a", "l", "q"};
        String[] turnaround = {"turn", "tr", "s", "turnaround"};
        String[] help = {"help", "h"};

        if (textFoundInList(help, input)) {
            System.out.println("Available Commands:");
            System.out.println();
            System.out.println("Function     | Main    | Alts");
            System.out.println("=============|=========|===========");
            System.out.println("Move Forward | forward | w, mv, f");
            System.out.println("Turn Right   | right   | d, r, e");
            System.out.println("Turn Left    | left    | a, l, q");
            System.out.println("Turn Around  | turn    | tr, s");
            System.out.println("Open Help    | help    | h");
        } else if (textFoundInList(forward, input)) {
            maze.movePlayerForward();
        } else if (textFoundInList(right, input)) {
            maze.rotatePlayer(Vec2.Rotation.RIGHT);
        } else if (textFoundInList(left, input)) {
            maze.rotatePlayer(Vec2.Rotation.LEFT);
        } else if (textFoundInList(turnaround, input)) {
            maze.rotatePlayer(Vec2.Rotation.TURNAROUND);
        }
    }

    public static void main(String[] args) {
        Maze maze = new Maze(8);
        maze.renderViewport();
        System.out.println("You can type HELP for a list of commands.");
        getPlayerInput(maze);
        while (!maze.hasWon()) {
            maze.renderViewport();
            getPlayerInput(maze);
        }
        if (maze.hasWon()) {
            System.out.println(" __ __   ___   __ __        ___  _____   __   ____  ____   ___  ___    __ \n|  |  | /   \\ |  |  |      /  _]/ ___/  /  ] /    ||    \\ /  _]|   \\  |  |\n|  |  ||     ||  |  |     /  [_(   \\_  /  / |  o  ||  o  )  [_ |    \\ |  |\n|  ~  ||  O  ||  |  |    |    _]\\__  |/  /  |     ||   _/    _]|  D  ||__|\n|___, ||     ||  :  |    |   [_ /  \\ /   \\_ |  _  ||  | |   [_ |     | __ \n|     ||     ||     |    |     |\\    \\     ||  |  ||  | |     ||     ||  |\n|____/  \\___/  \\__,_|    |_____| \\___|\\____||__|__||__| |_____||_____||__|");
        }
    }
}
