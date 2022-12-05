import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    final static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines("3");
        int totalPriority = 0;
        for (String line : lines) {
            int breakpoint = line.length() / 2;
            String first = line.substring(0, breakpoint);
            String second = line.substring(breakpoint);
            String common  = "";
            for (char c : first.toCharArray()) {
                if (second.indexOf(c) >= 0 && common.indexOf(c) < 0) {
                    common += c;
                    int value = alphabet.indexOf(c) + 1;
                    totalPriority += value;
                }
            }
        }
        System.out.println(totalPriority);

    }
    public static void part2() {
        List<String> lines = Main.readInputLines("3");
        int totalPriority = 0;
        for (int i = 0; i < lines.size(); i += 3) {
            totalPriority += findCommon(lines, i);
        }
        System.out.println(totalPriority);
    }

    private static int findCommon(List<String> lines, int i) {
        String first = lines.get(i);
        String second = lines.get(i + 1);
        String third = lines.get(i + 2);
        for (char c : first.toCharArray()) {
            if (second.indexOf(c) >= 0 && third.indexOf(c) >= 0) {
                return alphabet.indexOf(c) + 1;
            }
        }
        for (char c : second.toCharArray()) {
            if (first.indexOf(c) >= 0 && third.indexOf(c) >= 0) {
                return alphabet.indexOf(c) + 1;
            }
        }
        for (char c : third.toCharArray()) {
            if (second.indexOf(c) >= 0 && first.indexOf(c) >= 0) {
                return alphabet.indexOf(c) + 1;
            }
        }
        return 0;
    }
}
