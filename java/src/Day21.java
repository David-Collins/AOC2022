import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {
    public static Map<Character, Main.Function2<Long, Long, Long>> ops = getOps();

    public static void main(String[] args){
        List<String> lines = Main.readInputLines("21");
        System.out.println("Part 1:");
        part1(lines);
        System.out.println("Part 2:");
        part2(lines);
    }
    public static void part1(List<String> lines) {
        System.out.println(mapNums(lines, false).get("root"));
    }
    public static void part2(List<String> lines) {
        Map<String, Long> nums = mapNums(lines, true);
        Map<String, String> parsed = parseLines(lines);
        String[] split = parsed.get("root").split(" ");
        int index = nums.containsKey(split[0]) ? 2 : 0;
        String start = split[index];
        long match = nums.get(split[index == 2 ? 0 : 2]);
        System.out.println(parseDown(match, start, parsed, nums));
    }

    public static long parseDown(long start, String curr, Map<String, String> parsed, Map<String, Long> nums) {
        if (curr.equals("humn"))
            return start;
        else {
            String[] split = parsed.get(curr).split(" ");
            String left = split[0], right = split[2];
            char op = split[1].charAt(0);
            if (nums.containsKey(left)) {
                return parseDown(getOpsInverseB().get(op).apply(nums.get(left), start), right, parsed, nums);
            }
            else if (nums.containsKey(right)) {
                return parseDown(getOpsInverse().get(op).apply(start, nums.get(right)), left, parsed, nums);
            }
            else {
                return 0;
            }
        }
    }

    public static Map<String, String> parseLines(List<String> lines) {
        Map<String, String> parsed = new HashMap<>();
        for (String line : lines) {
            parsed.put(line.substring(0, 4), line.substring(6));
        }
        return parsed;
    }

    public static Map<String, Long> mapNums(List<String> lines, boolean skip) {
        Map<String, Long> nums = new HashMap<>();
        List<String> remaining = new ArrayList<>(lines);
        List<Integer> toRemove = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split(" ");
            if (split.length == 2) {
                String name = split[0].substring(0, 4);
                if (!(skip && name.equals("humn")))
                    nums.put(name, Long.parseLong(split[1]));
                toRemove.add(i);
            }
        }
        for (int i : toRemove)
            remaining.remove(lines.get(i));

        while (toRemove.size() > 0) {
            toRemove = new ArrayList<>();
            for (int i = 0; i < remaining.size(); i++) {
                String[] split = remaining.get(i).split(" ");
                String first = split[1], second = split[3];
                if (nums.containsKey(first) && nums.containsKey(second)) {
                    long a = nums.get(first), b = nums.get(second);
                    nums.put(split[0].substring(0, 4), ops.get(split[2].charAt(0)).apply(a, b));
                    toRemove.add(i);
                }
            }
            List<String> temp = new ArrayList<>(remaining);
            for (int i : toRemove)
                temp.remove(remaining.get(i));
            remaining = temp;
        }
        return nums;
    }

    public static Map<Character, Main.Function2<Long, Long, Long>> getOps() {
        Map<Character, Main.Function2<Long, Long, Long>> ret = new HashMap<>();
        ret.put('/', (Long a, Long b) -> a / b);
        ret.put('*', (Long a, Long b) -> a * b);
        ret.put('-', (Long a, Long b) -> a - b);
        ret.put('+', Long::sum);
        return ret;
    }

    public static Map<Character, Main.Function2<Long, Long, Long>> getOpsInverse() {
        Map<Character, Main.Function2<Long, Long, Long>> ret = new HashMap<>();
        ret.put('/', (Long a, Long b) -> a * b);
        ret.put('*', (Long a, Long b) -> a / b);
        ret.put('-', Long::sum);
        ret.put('+', (Long a, Long b) -> a - b);
        return ret;
    }

    public static Map<Character, Main.Function2<Long, Long, Long>> getOpsInverseB() {
        Map<Character, Main.Function2<Long, Long, Long>> ret = new HashMap<>();
        ret.put('/', (Long a, Long b) -> a / b);
        ret.put('*', (Long a, Long b) -> b / a);
        ret.put('-', (Long a, Long b) -> a - b);
        ret.put('+', (Long a, Long b) -> b - a);
        return ret;
    }
}
