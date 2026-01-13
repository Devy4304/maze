package maze;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static boolean cheats = false;

    private static boolean textFoundInList(String[] list, String text) {
        for (String item : list) {
            if (item.equalsIgnoreCase(text)) {
                return true;
            }
        }
        return false;
    }

    public static void getPlayerInput(Maze maze) {
        System.out.print(" > ");
        String input = scanner.nextLine();

        String[] forward = {"forward", "w", "mv", "f", ""};
        String[] right = {"right", "d", "r", "e"};
        String[] left = {"left", "a", "l", "q"};
        String[] turnaround = {"turn", "tr", "s", "turnaround"};
        String[] help = {"help", "h"};
        String[] quit = {"exit", "quit"};

        if (textFoundInList(help, input)) {
            System.out.println();
            System.out.println("Available Commands:");
            System.out.println();
            System.out.println("Function     | Main    | Alts");
            System.out.println("=============|=========|===========");
            System.out.println("Move Forward | forward | w, mv, f");
            System.out.println("Turn Right   | right   | d, r, e");
            System.out.println("Turn Left    | left    | a, l, q");
            System.out.println("Turn Around  | turn    | tr, s");
            System.out.println("Exit Game    | exit    | quit");
            System.out.println("Open Help    | help    | h");
            System.out.println();
        } else if (textFoundInList(forward, input)) {
            maze.movePlayerForward();
        } else if (textFoundInList(right, input)) {
            maze.rotatePlayer(Vec2.Rotation.RIGHT);
        } else if (textFoundInList(left, input)) {
            maze.rotatePlayer(Vec2.Rotation.LEFT);
        } else if (textFoundInList(turnaround, input)) {
            maze.rotatePlayer(Vec2.Rotation.TURNAROUND);
        } else if (input.equalsIgnoreCase("i hate this level, let me out!")) {
            if (cheats) {
                maze.win();
            } else {
                System.out.println("Cheats must be enabled to use this command. Type 'cheats' to enable cheats.");
            }
        } else if (input.equalsIgnoreCase("showgamemap")) {
            if (cheats) {
                maze.printMaze();
            } else {
                System.out.println("Cheats must be enabled to use this command. Type 'cheats' to enable cheats.");
            }
        } else if (input.equalsIgnoreCase("cheats")) {
            cheats = !cheats;
            System.out.println("Cheats are now " + ((cheats) ? "enabled" : "disabled") + "!");
        } else if (textFoundInList(quit, input)) {
            System.out.println("Thank you for playing!");
            System.exit(0);
        } else {
            System.out.println("Command not found. Type 'help' for list of commands.");
        }
    }

    private static int askForDifficulty() {
        System.out.println();
        System.out.println("Select Difficulty");
        System.out.println();
        System.out.println("   Difficulty  |  Size    ");
        System.out.println("===============|==========");
        System.out.println("1) Easy        |  8x8     ");
        System.out.println("2) Medium      |  16x16   ");
        System.out.println("3) Hard        |  32x32   ");
        System.out.println("4) Impossible  |  100x100 ");
        System.out.println();

        int input;
        do {
            System.out.print(" > ");
            input = scanner.nextInt();
            System.out.println();
        } while (input <= 0 || input >= 5);

        scanner.nextLine();
        return switch (input) {
            case 1 -> 8;
            case 2 -> 16;
            case 3 -> 32;
            case 4 -> 100;
            default -> throw new IllegalStateException("Unexpected value: " + input);
        };
    }

    private static void play(int difficulty) {
        Maze maze = new Maze(difficulty);
        maze.renderViewport();
        System.out.println("You can type HELP for a list of commands.");
        getPlayerInput(maze);
        while (!maze.hasWon()) {
            maze.renderViewport();
            getPlayerInput(maze);
        }
        System.out.println(" __ __   ___   __ __        ___  _____   __   ____  ____   ___  ___    __ \n|  |  | /   \\ |  |  |      /  _]/ ___/  /  ] /    ||    \\ /  _]|   \\  |  |\n|  |  ||     ||  |  |     /  [_(   \\_  /  / |  o  ||  o  )  [_ |    \\ |  |\n|  ~  ||  O  ||  |  |    |    _]\\__  |/  /  |     ||   _/    _]|  D  ||__|\n|___, ||     ||  :  |    |   [_ /  \\ /   \\_ |  _  ||  | |   [_ |     | __ \n|     ||     ||     |    |     |\\    \\     ||  |  ||  | |     ||     ||  |\n|____/  \\___/  \\__,_|    |_____| \\___|\\____||__|__||__| |_____||_____||__|");
        System.out.println();
        System.out.print("Would you like to play again (y/n)?");
    }

    public static void main(String[] args) {
        do {
            play(askForDifficulty());
        } while (!scanner.nextLine().equals("n"));
        System.out.println("Farewell adventurer!");
    }
}
