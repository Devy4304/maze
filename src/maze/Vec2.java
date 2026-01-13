package maze;

public class Vec2 {
    public static final Vec2 NORTH = new Vec2(-1,0);
    public static final Vec2 EAST = new Vec2(0,1);
    public static final Vec2 SOUTH = new Vec2(1,0);
    public static final Vec2 WEST = new Vec2(0,-1);
    
    public static final Vec2[] DIRECTIONS = {NORTH, EAST, SOUTH, WEST};

    public enum Rotation {
        LEFT(1),
        RIGHT(-1),
        TURNAROUND(2);

        private final int delta;

        Rotation(int delta) {
            this.delta = delta;
        }

        public int delta() {
            return delta;
        }
    }

    public int row;
    public int column;

    public Vec2(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Vec2(Vec2 other) {
        this.row = other.row;
        this.column = other.column;
    }

    public Vec2() {
        row = 0;
        column = 0;
    }

    public Vec2 add(Vec2 other) {
        Vec2 n = new Vec2(this);
        n.row += other.row;
        n.column += other.column;
        return n;
    }

    public Vec2 subtract(Vec2 other) {
        Vec2 n = new Vec2(this);
        n.row -= other.row;
        n.column -= other.column;
        return n;
    }

    public Vec2 multiply(Vec2 other) {
        Vec2 n = new Vec2(this);
        n.row *= other.row;
        n.column *= other.column;
        return n;
    }

    public Vec2 divide(Vec2 other) {
        Vec2 n = new Vec2(this);
        n.row /= other.row;
        n.column /= other.column;
        return n;
    }

    public Vec2 add(int c) {
        Vec2 n = new Vec2(this);
        n.row += c;
        n.column += c;
        return n;
    }

    public Vec2 subtract(int c) {
        Vec2 n = new Vec2(this);
        n.row -= c;
        n.column -= c;
        return n;
    }

    public Vec2 multiply(int c) {
        Vec2 n = new Vec2(this);
        n.row *= c;
        n.column *= c;
        return n;
    }

    public Vec2 divide(int c) {
        Vec2 n = new Vec2(this);
        n.row /= c;
        n.column /= c;
        return n;
    }

    public double distance(Vec2 other) {
        return Math.sqrt(Math.pow(other.row - row, 2) + Math.pow(other.column - column, 2));
    }
}