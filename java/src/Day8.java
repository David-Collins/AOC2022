import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 {
    public static void main(String[] args){
        List<String> lines = Main.readInputLines("8");
        System.out.println("Part 1:");
        part1(lines);
        System.out.println("Part 2:");
        part2(lines);
    }
    public static void part1(List<String> lines) {
        List<List<Integer>> cols = new ArrayList<>();
        Set<Coord> visible = new HashSet<>();
        for (int y = 0; y < lines.size(); y++) {
            List<String> split = Arrays.asList(lines.get(y).split(""));
            int tallestLeft = 0;
            int tallestRight = 0;
            for (int x = 0; x < split.size(); x++) {
                int currLeft = Integer.parseInt(split.get(x));
                int currRight = Integer.parseInt(split.get(split.size() - 1 - x));
                if (y == 0)
                    cols.add(new ArrayList<>());
                cols.get(x).add(currLeft);
                if(x == 0 || y == 0 || x == split.size() - 1 || y == lines.size() - 1) {
                    visible.add(new Coord(x, y));
                }
                else {
                    if (currLeft > tallestLeft) {
                        visible.add(new Coord(x, y));
                    }
                    if (currRight > tallestRight) {
                        visible.add(new Coord(split.size() - 1 - x, y));
                    }
                }
                tallestLeft = Math.max(currLeft, tallestLeft);
                tallestRight = Math.max(currRight, tallestRight);
            }
        }
        for (int x = 0; x < cols.size(); x++) {
            int tallestTop = 0;
            int tallestBottom = 0;
            List<Integer> col = cols.get(x);
            for (int y = 0; y < col.size(); y++) {
                int currTop = col.get(y), currBottom = col.get(col.size() - 1 - y);
                if (currTop > tallestTop)
                    visible.add(new Coord(x, y));
                if (currBottom > tallestBottom)
                    visible.add(new Coord(x, col.size() - 1 - y));
                tallestTop = Math.max(currTop, tallestTop);
                tallestBottom = Math.max(currBottom, tallestBottom);
            }
        }
        System.out.println(visible.size());
    }
    public static void part2(List<String> lines) {
        Set<Integer> scores = new HashSet<>();
        List<List<Integer>> grid = buildGrid(lines);
        for (int y = 0; y < grid.size(); y++) {
            for (int x = 0; x < grid.get(y).size(); x++) {
                int up = 0, down = 0, left = 0, right = 0;
                int curr = grid.get(y).get(x);
                while (y - up > 0) {
                    if (grid.get(y - up).get(x) >= curr && up != 0)
                        break;
                    up++;
                }
                while (y + down < grid.get(x).size() - 1) {
                    if (grid.get(y + down).get(x) >= curr && down != 0)
                        break;
                    down++;
                }
                while (x - left > 0) {
                    if (grid.get(y).get(x - left) >= curr && left != 0)
                        break;
                    left++;
                }
                while (x + right < grid.size() - 1) {
                    if (grid.get(y).get(x + right) >= curr && right != 0)
                        break;
                    right++;
                }
                scores.add(up * down * left * right);
            }
        }
        List<Integer> finalList = new ArrayList<>(scores);
        finalList.sort(Collections.reverseOrder());
        System.out.println(finalList.get(0));
    }

    public static List<List<Integer>> buildGrid(List<String> lines) {
        List<List<Integer>> grid = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            grid.add(new ArrayList<>());
            for (int c = 0; c < lines.get(i).length(); c++) {
                grid.get(i).add(Integer.parseInt(lines.get(i).substring(c, c + 1)));
            }
        }
        return grid;
    }

}
