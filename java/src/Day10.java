import java.util.*;

public class Day10 {
    public static void main(String[] args){
        List<String> lines = Main.readInputLines("10");
        System.out.println("Part 1:");
        part1(lines);
        System.out.println("Part 2:");
        part2(lines);
    }
    public static void part1(List<String> lines) {
        int cycle = 1, x = 1, total = 0, curr = 20;
        for (String line : lines) {
            String[] split = line.split(" ");
            if (split.length == 1)
                cycle++;
            else {
                if (cycle + 2 > curr) {
                    total += curr * x;
                    curr += 40;
                }
                x += Integer.parseInt(split[1]);
                cycle += 2;
            }
            if (cycle >= curr) {
                total += curr * x;
                curr += 40;
            }
            if (curr == 260)
                break;
        }
        System.out.println(total);
    }
    public static void part2(List<String> lines) {
        int cycle = 1, x = 1, pos = 0;
        for (String line : lines) {
            String[] split = line.split(" ");
            if (split.length == 1) {
                cycle++;
                display(x, pos);
                pos++;
            }
            else {
                display(x, pos);
                pos++;
                cycle++;
                if (cycle >= 41) {
                    cycle = 1;
                    System.out.println();
                }
                display(x, pos);
                pos++;
                cycle++;
                x += Integer.parseInt(split[1]);
            }
            if (cycle >= 41) {
                cycle = 1;
                System.out.println();
            }
            pos = pos % 40;
        }
    }

    public static void display(int x, int pos) {
        if (pos == x || pos == x + 1 || pos == x - 1)
            System.out.print("#");
        else
            System.out.print(".");
    }
}
