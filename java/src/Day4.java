import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("4");
        Pattern reg = Pattern.compile("([\\d]+)-([\\d]+),([\\d]+)-([\\d]+)");
        int overlapCount = 0;
        for(String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int lower1 = Integer.parseInt(m.group(1));
            int upper1 = Integer.parseInt(m.group(2));
            int lower2 = Integer.parseInt(m.group(3));
            int upper2 = Integer.parseInt(m.group(4));
            if ((lower1 <= lower2 && upper1 >= upper2) || (lower2 <= lower1 && upper2 >= upper1))
                overlapCount++;
        }
        System.out.println(overlapCount);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines("4");
        Pattern reg = Pattern.compile("([\\d]+)-([\\d]+),([\\d]+)-([\\d]+)");
        int overlapCount = 0;
        for(String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int lower1 = Integer.parseInt(m.group(1));
            int upper1 = Integer.parseInt(m.group(2));
            int lower2 = Integer.parseInt(m.group(3));
            int upper2 = Integer.parseInt(m.group(4));
            if ((upper1 >= upper2 && lower1 <= upper2) || (upper2 >= upper1 && lower2 <= upper1))
                overlapCount++;
        }
        System.out.println(overlapCount);
    }
}
