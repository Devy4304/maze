package maze;

import java.util.HashMap;

public class Renderer {
    // Info about what to draw for a given pattern key
    private static class DrawInfo {
        int charIndex; // index into RendererData.CHARS ("░▒▓█")
        int priority;  // higher = closer / more in front
        boolean isExit;
    }

    public static void render(int[][] area, int playerDirection) {
        // Make sure the area is valid
        if (area.length != 4 || area[0].length != 3) {
            throw new IllegalArgumentException(
                    "Specified render area is not correctly sized. Expected size: 4r x 3c, provided is "
                            + area.length + "r x " + area[0].length + "c.");
        }

        // Screen buffer
        String[][] buffer = new String[RendererData.ROWS][RendererData.COLUMNS];

        // Map pattern letter -> what to draw there
        HashMap<Character, DrawInfo> draw = new HashMap<>();

        // Build draw map
        for (int row = 0; row < area.length; row++) {
            for (int col = 0; col < area[row].length; col++) {
                int cell = area[row][col];
                if (cell != 0) {
                    // Create a DrawInfo object
                    DrawInfo info = new DrawInfo();

                    info.charIndex = row;      // 0..3 -> "░▒▓█"
                    info.priority  = row;      // back (0) .. front (3)
                    info.isExit = (cell == 2); // If the cell is an exit, set it to be so

                    for (char key : RendererData.LOOKUP[row][col]) {
                        // Front (higher priority) overwrites back for the same key
                        draw.merge(key, info, (oldV, newV) ->
                                (newV.priority >= oldV.priority) ? newV : oldV);
                    }
                }
            }
        }

        // Fill buffer from PATTERN + draw the map
        for (int row = 0; row < RendererData.ROWS; row++) {
            for (int col = 0; col < RendererData.COLUMNS; col++) {
                buffer[row][col] = "";
                char patternKey = RendererData.PATTERN[row][col];

                if (draw.containsKey(patternKey)) {
                    DrawInfo info = draw.get(patternKey);

                    // Color exits red (optional, uses the high priority)
                    if (Main.ANSI && info.isExit) {
                        buffer[row][col] += RendererData.Colors.RED;
                    }

                    char ch = RendererData.CHARS.charAt(info.charIndex);
                    buffer[row][col] += String.valueOf(ch).repeat(3);

                    if (Main.ANSI && info.isExit) {
                        buffer[row][col] += RendererData.Colors.RESET;
                    }
                } else {
                    if (row <= 10) {
                        if (Main.ANSI && RendererData.STARS[playerDirection][row][col] == '⁜') {
                            buffer[row][col] += RendererData.Colors.BLUE;
                        }
                        buffer[row][col] += " " + RendererData.STARS[playerDirection][row][col] + " ";
                        if (Main.ANSI && RendererData.STARS[playerDirection][row][col] == '⁜') {
                            buffer[row][col] += RendererData.Colors.RESET;
                        }
                    } else buffer[row][col] += " ".repeat(3);
                }
            }
        }

        // Print buffer
        for (String[] row : buffer) {
            for (String t : row) {
                System.out.print(t);
            }
            System.out.println();
        }
        System.out.println();
    }
}
