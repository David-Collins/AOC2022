import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args){
        List<String> lines = Main.readInputLines("6");
        System.out.println("Part 1:");
        System.out.println(part1(lines));
        System.out.println("Part 2:");
        System.out.println(part2(lines));
    }
    public static int part1(List<String> lines) {
        String line = lines.get(0);
        return findMarker(line, 4);
    }
    public static int part2(List<String> lines) {
        String line = lines.get(0);
        return findMarker(line, 14);
    }

    public static int findMarker(String chars, int sequenceSize) {
        for (int i = 0; i < chars.length() - sequenceSize; i++) {
            List<Character> sequence = new ArrayList<>();
            for (int j = 0; j < sequenceSize; j++) {
                char c = chars.charAt(i + j);
                if (!sequence.contains(c))
                    sequence.add(c);
                else
                    break;
            }
            if (sequence.size() == sequenceSize)
                return i + sequenceSize;
        }
        return 0;
    }
}
