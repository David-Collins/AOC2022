import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines(1);
        int curr = 0;
        List<Integer> calories = new ArrayList<>();
        for(String line : lines) {
            if (line.length() == 0) {
                calories.add(curr);
                curr = 0;
            }
            else {
                curr += Integer.parseInt(line);
            }
        }
        calories = calories.stream().sorted().collect(Collectors.toList());
        Collections.reverse(calories);
        System.out.println(calories.get(0));
    }
    public static void part2() {
        List<String> lines = Main.readInputLines(1);
        int curr = 0;
        List<Integer> calories = new ArrayList<>();
        for(String line : lines) {
            if (line.length() == 0) {
                calories.add(curr);
                curr = 0;
            }
            else {
                curr += Integer.parseInt(line);
            }
        }
        calories = calories.stream().sorted().collect(Collectors.toList());
        Collections.reverse(calories);
        System.out.println(calories.get(0) + calories.get(1) + calories.get(2));
    }
}
