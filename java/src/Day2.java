import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Day2 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<String> lines = Main.readInputLines(2);
        Map<String,Integer> roll = new HashMap<>() {{
            this.put("A", 1);
            this.put("B", 2);
            this.put("C", 3);
            this.put("X", 1);
            this.put("Y", 2);
            this.put("Z", 3);
        }};
        int totalScore = 0;
        for (String line : lines) {
            int op = roll.get(line.substring(0, 1));
            int you = roll.get(line.substring(2));
            totalScore += you;
            if (you == op)
                totalScore += 3;
            else {
                switch (you) {
                    case 1:
                        totalScore += (op == 2 ? 0 : 6);
                        break;
                    case 2:
                        totalScore += (op == 3 ? 0 : 6);
                        break;
                    case 3:
                        totalScore += (op == 1 ? 0 : 6);
                }
            }
        }
        System.out.println(totalScore);
    }
    public static void part2() {
        List<String> lines = Main.readInputLines(2);
        Map<String,Integer> roll = new HashMap<>() {{
            this.put("A", 1);
            this.put("B", 2);
            this.put("C", 3);
            this.put("X", 0);
            this.put("Y", 3);
            this.put("Z", 6);
        }};
        int totalScore = 0;
        for (String line : lines) {
            int op = roll.get(line.substring(0, 1));
            int outcome = roll.get(line.substring(2));
            totalScore += outcome;
            if (outcome == 3)
                totalScore += op;
            else {
                switch (op) {
                    case 1:
                        totalScore += (outcome == 6 ? 2 : 3);
                        break;
                    case 2:
                        totalScore += (outcome == 6 ? 3 : 1);
                        break;
                    case 3:
                        totalScore += (outcome == 6 ? 1: 2);
                }
            }
        }
        System.out.println(totalScore);
    }
}
