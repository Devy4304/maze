package maze;

public class RendererData {
    public static class Colors {
        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
        // Background colors
        public static final String BLACK_BACKGROUND = "\u001B[40m";
        public static final String RED_BACKGROUND = "\u001B[41m";
        public static final String GREEN_BACKGROUND = "\u001B[42m";
        public static final String YELLOW_BACKGROUND = "\u001B[43m";
        public static final String BLUE_BACKGROUND = "\u001B[44m";
        public static final String PURPLE_BACKGROUND = "\u001B[45m";
        public static final String CYAN_BACKGROUND = "\u001B[46m";
        public static final String WHITE_BACKGROUND = "\u001B[47m";
    }

    public static final int ROWS = 20;
    public static final int COLUMNS = 22;

    public static final String CHARS = "░▒▓█";

    public static final char[][] PATTERN = {
            {'q', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'l'},
            {'q', 'q', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'l', 'l'},
            {'w', 'w', 'e', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'h', 'z', 'z'},
            {'w', 'w', 'e', 'e', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'h', 'h', 'z', 'z'},
            {'w', 'w', 'r', 'r', 't', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'f', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 't', 't', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'f', 'f', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'u', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'p', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'u', 'u', 'o', 'o', 'o', 'o', 'o', 'o', 'p', 'p', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'o', 'a', 'a', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'o', 'a', 'a', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'o', 'a', 'a', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'i', 'i', 'o', 'o', 'o', 'o', 'o', 'o', 'a', 'a', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'u', 'u', 'o', 'o', 'o', 'o', 'o', 'o', 'p', 'p', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 'y', 'y', 'u', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'p', 'd', 'd', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 't', 't', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'f', 'f', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'r', 'r', 't', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 's', 'f', 'j', 'j', 'z', 'z'},
            {'w', 'w', 'e', 'e', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'h', 'h', 'z', 'z'},
            {'w', 'w', 'e', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'h', 'z', 'z'},
            {'q', 'q', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'l', 'l'},
            {'q', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'k', 'l'}
    };

    public static final char[][][] LOOKUP = {
            {{'u', 'i', 'y'}, {'u', 'i', 'o', 'p', 'a'}, {'a', 'p', 'd'}},
            {{'t', 'y', 'r'}, {'u', 'i', 'o', 'p', 'a', 's', 'f', 'd', 't', 'y'}, {'d', 'f', 'j'}},
            {{'w', 'e', 'r'}, {'u', 'i', 'o', 'p', 'a', 's', 'f', 'd', 't', 'y', 'r', 'e', 'g'}, {'j', 'h', 'z'}},
            {{'q', 'w'}, {'u', 'i', 'o', 'p', 'a', 's', 'f', 'd', 't', 'y', 'r', 'e', 'g', 'w', 'q', 'k', 'l', 'z'}, {'l', 'z'}},
    };
}
