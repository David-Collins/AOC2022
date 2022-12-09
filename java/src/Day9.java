import java.util.*;

public class Day9 {

    public static Map<String,Integer> directions = new HashMap<>() {{
        this.put("U", 1);
        this.put("D", -1);
        this.put("R", 1);
        this.put("L", -1);
    }};
    public static List<String> lines = Main.readInputLines("9");
    public static void main(String[] args){
        System.out.println("Part 1:");
        System.out.println(part1());
        System.out.println("Part 2:");
        System.out.println(part2());
    }

    public static Object part1() {
        return solve(2);
    }

    public static Object part2() {
        return solve(10);
    }

    public static int solve(int ropeSize) {
        int x=0, y=0;
        Set<Coord> visited = new HashSet<>();
        Coord[] rope = new Coord[ropeSize];
        for (int i = 0; i < ropeSize; i++)
            rope[i] = new Coord(x, y);
        visited.add(rope[ropeSize - 1]);

        for (String line : lines) {
            String[] split = line.split(" ");
            String direction = split[0];
            int movement = directions.get(direction);
            for (int i = 0; i < Integer.parseInt(split[1]); i++) {
                Coord head = rope[0];
                x = head.getX();
                y = head.getY();
                if (direction.equals("U") || direction.equals("D")) {
                    y += movement;
                }
                else {
                    x += movement;
                }
                rope[0] = new Coord(x, y);
                for (int j = 1; j < ropeSize; j++) {
                    head = rope[j - 1];
                    Coord curr = rope[j];
                    rope[j] = calculateMovement(head.getX(), head.getY(), curr.getX(), curr.getY());
                }
                visited.add(rope[ropeSize - 1]);
            }
        }
        return visited.size();
    }

    public static Coord calculateMovement(int hx, int hy, int tx, int ty) {
        if (hx == tx && Math.abs(hy - ty) > 1)
            // move along y axis
            return hy > ty ? new Coord(tx, ty + 1) : new Coord(tx, ty - 1);
        else if (hy == ty && Math.abs(hx - tx) > 1)
            // move along x axis
            return hx > tx ? new Coord(tx + 1, ty) : new Coord(tx - 1, ty);
        else if (Math.abs(hy - ty) > 1 || Math.abs(hx - tx) > 1) {
            // NE
            if (checkMovement(hx, hy, tx + 1, ty + 1) <= 2)
                return new Coord(tx + 1, ty + 1);
            // NW
            else if (checkMovement(hx, hy, tx - 1 , ty + 1) <= 2)
                return new Coord(tx - 1, ty + 1);
            // SE
            else if (checkMovement(hx, hy, tx + 1, ty - 1) <= 2)
                return new Coord(tx + 1, ty - 1);
            // SW
            else if (checkMovement(hx, hy, tx - 1, ty - 1) <= 2)
                return new Coord(tx - 1, ty - 1);
        }
        return new Coord(tx, ty);
    }

    public static int checkMovement(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
