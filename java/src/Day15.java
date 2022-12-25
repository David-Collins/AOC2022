import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {

    public static List<String> lines = Main.readInputLines("15");
    public static List<Coord> beacons = new ArrayList<>();
    public static Pattern reg = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
    public static void main(String[] args){
        getBeacons();
        System.out.println("Part 1:");
        System.out.println(part1(2000000));
        System.out.println("Part 2:");
        part2(4000000);
    }
    public static int part1(int y) {
        Set<Coord> viewsAtY = new HashSet<>();
        for (String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int sx = Integer.parseInt(m.group(1));
            int sy = Integer.parseInt(m.group(2));
            int bx = Integer.parseInt(m.group(3));
            int by = Integer.parseInt(m.group(4));
            int dist = Math.abs(sx - bx) + Math.abs(sy - by);
            if (sy + dist >= y) {
                int maxWidth = dist * 2 + 1;
                int gap = Math.abs(sy - y);
                int rowCount = maxWidth - (2 * gap);
                int startX = sx - (dist - gap);
                for (int i = startX; i < startX + rowCount; i++) {
                    Coord temp = new Coord(i, y);
                    if (!beacons.contains(temp))
                        viewsAtY.add(temp);
                }
            }
        }
        return viewsAtY.size();
    }
    public static void part2(int upper) {
        List<Integer[]>[] ranges = new List[upper + 1];
        for (String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int sx = Integer.parseInt(m.group(1));
            int sy = Integer.parseInt(m.group(2));
            int bx = Integer.parseInt(m.group(3));
            int by = Integer.parseInt(m.group(4));
            int dist = Math.abs(sx - bx) + Math.abs(sy - by);

            if (sy >= 0 && sy <= upper) {
                List<Integer[]> curr = ranges[sy];
                if (curr == null) {
                    curr = new ArrayList<>();
                }
                int min = Math.max(sx - dist, 0);
                int max = Math.min(sx + dist, upper);
                curr.add(new Integer[]{min, max});
                ranges[sy] = curr;
            }

            int maxWidth = dist * 2 + 1;
            for (int y = 1; y < dist; y++) {
                List<Integer[]> north, south;
                int min = Math.max(sx - dist + y, 0);
                int rowCount = maxWidth - (2 * y);
                int max = Math.min(min + rowCount, upper);
                if (sy + y >= 0 && sy + y <= upper) {
                    north = ranges[sy + y];
                    if (north == null)
                        north = new ArrayList<>();
                    north.add(new Integer[]{min, max});
                    ranges[sy + y] = north;
                }
                if (sy - y >= 0 && sy - y <= upper) {
                    south = ranges[sy - y];
                    if (south == null)
                        south = new ArrayList<>();
                    south.add(new Integer[]{min, max});
                    ranges[sy - y] = south;
                }
            }
        }
        for (int i = 0; i <= upper; i++) {
            List<Integer[]> result = rangeMerge(ranges[i], upper);
            if (result.size() > 1) {
                System.out.println(calcTuning(result.get(0)[0] - 1, i));
                return;
            }
        }
    }

    public static List<Integer[]> rangeMerge(List<Integer[]> ranges, int upper) {
        List<Integer[]> result = new ArrayList<>();
        Stack<Integer[]> stack = new Stack<>();
        ranges.sort(Comparator.comparingInt(o -> o[0]));
        stack.push(ranges.get(0));

        for (int i = 1; i < ranges.size(); i++) {
            Integer[] top = stack.peek();
            if (ranges.get(i)[0] > top[1]) {
                stack.push(ranges.get(i));
            }
            else if (top[1] < ranges.get(i)[1]) {
                top[1] = ranges.get(i)[1];
                stack.pop();
                stack.push(top);
            }
        }

        while (stack.size() > 0) {
            result.add(stack.pop());
        }
        return result;
    }

    public static BigInteger calcTuning(int x, int y) {
        BigInteger newY = BigInteger.valueOf(y);
        BigInteger newX = BigInteger.valueOf(x).multiply(BigInteger.valueOf(4000000));
        return newX.add(newY);
    }

    public static void getBeacons() {
        Pattern reg = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
        for (String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int bx = Integer.parseInt(m.group(3));
            int by = Integer.parseInt(m.group(4));
            beacons.add(new Coord(bx, by));
        }
    }
}



