package maze;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    static boolean cheats = false;
    static boolean ANSI = true;

    private static boolean textFoundInList(String[] list, String text) {
        // Case-insensitive command matcher for multiple aliases.
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

        // Command aliases (supports short keys and alternate phrases).
        String[] forward = {"forward", "w", "mv", "f", "", "2"};
        String[] right = {"right", "d", "r", "e", "3"};
        String[] left = {"left", "a", "l", "q", "1"};
        String[] turnaround = {"turn", "tr", "s", "turnaround"};
        String[] ansi = {"ansi", "color", "disable ansi", "my screen is broken", "what the #### are these random characters!"};
        String[] help = {"help", "h"};
        String[] quit = {"exit", "quit"};

        // Single command-dispatch chain (parse input and trigger the matching action).
        if (textFoundInList(help, input)) {
            System.out.println();
            System.out.println("Available Commands:");
            System.out.println();
            System.out.println("Function     | Main    | Alts");
            System.out.println("=============|=========|===========");
            System.out.println("Move Forward | forward | w, (enter)");
            System.out.println("Turn Right   | right   | d, e");
            System.out.println("Turn Left    | left    | a, q");
            System.out.println("Turn Around  | turn    | tr, s");
            System.out.println("Toggle ANSI  | ansi    | color");
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
        } else if (textFoundInList(ansi, input)) {
            // Toggle ANSI rendering for terminals that don't support escape codes.
            ANSI = !ANSI;
            System.out.println("ANSI is now " + ((ANSI) ? "enabled" : "disabled") + ".");
        } else if (input.equalsIgnoreCase("i hate this level, let me out!")) {
            // Cheat-only instant win.
            if (cheats) {
                maze.win();
            } else {
                System.out.println("Cheats must be enabled to use this command. Type 'cheats' to enable cheats.");
            }
        } else if (input.equalsIgnoreCase("showgamemap")) {
            // Cheat-only full map reveal.
            if (cheats) {
                maze.printMaze();
            } else {
                System.out.println("Cheats must be enabled to use this command. Type 'cheats' to enable cheats.");
            }
        } else if (input.equalsIgnoreCase("cheats")) {
            // Toggle cheat mode (enables debug/skip commands).
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

        scanner.nextLine(); // consume leftover newline from nextInt()
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
            if (ANSI) {
                // "Clear" the console and move cursor to top-left using ANSI escape codes.
                for (int i = 0; i < 40; i++) System.out.println();
                System.out.print("\u001b[1;1H");
            }
            maze.renderViewport();
            getPlayerInput(maze);
        }
        if (ANSI) System.out.print(RendererData.Colors.RED); // set color to RED
        System.out.println(" __ __   ___   __ __        ___  _____   __   ____  ____   ___  ___    __ \n|  |  | /   \\ |  |  |      /  _]/ ___/  /  ] /    ||    \\ /  _]|   \\  |  |\n|  |  ||     ||  |  |     /  [_(   \\_  /  / |  o  ||  o  )  [_ |    \\ |  |\n|  ~  ||  O  ||  |  |    |    _]\\__  |/  /  |     ||   _/    _]|  D  ||__|\n|___, ||     ||  :  |    |   [_ /  \\ /   \\_ |  _  ||  | |   [_ |     | __ \n|     ||     ||     |    |     |\\    \\     ||  |  ||  | |     ||     ||  |\n|____/  \\___/  \\__,_|    |_____| \\___|\\____||__|__||__| |_____||_____||__|");
        System.out.println(RendererData.Colors.RESET); // reset ANSI color
        System.out.print("Would you like to play again (y/n)?");
    }

    public static void main(String[] args) {
        // Main game loop: pick difficulty, play, repeat until user enters 'n'.
        do {
            play(askForDifficulty());
        } while (!scanner.nextLine().equals("n"));
        System.out.println("Farewell adventurer!");
    }
}
