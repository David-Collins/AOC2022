import java.io.CharArrayReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 {
    public static void main(String[] args){
        System.out.println("Part 1:");
        part1();
        System.out.println("Part 2:");
        part2();
    }
    public static void part1() {
        List<Stack<Character>> crates = processStack();
        List<String> lines = Main.readInputLines("5");
        Pattern reg = Pattern.compile("move\\s([\\d]+)\\sfrom\\s([\\d]+)\\sto\\s([\\d]+)");
        for(String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int count = Integer.parseInt(m.group(1));
            Stack<Character> src = crates.get(Integer.parseInt(m.group(2)) - 1);
            Stack<Character> dest = crates.get(Integer.parseInt(m.group(3)) - 1);
            for (int i = 0; i < count; i++) {
                dest.push(src.pop());
            }
        }
        String result = "";
        for (Stack<Character> crate: crates) {
            result += crate.peek();
        }
        System.out.println(result);
    }
    public static void part2() {
        List<Stack<Character>> crates = processStack();
        List<String> lines = Main.readInputLines("5");
        Pattern reg = Pattern.compile("move\\s([\\d]+)\\sfrom\\s([\\d]+)\\sto\\s([\\d]+)");
        for(String line : lines) {
            Matcher m = reg.matcher(line);
            m.find();
            int count = Integer.parseInt(m.group(1));
            Stack<Character> src = crates.get(Integer.parseInt(m.group(2)) - 1);
            Stack<Character> dest = crates.get(Integer.parseInt(m.group(3)) - 1);
            Stack<Character> temp = new Stack<>();
            for (int i = 0; i < count; i++) {
                temp.push(src.pop());
            }
            for (int i = 0; i < count; i++) {
                dest.push(temp.pop());
            }
        }
        String result = "";
        for (Stack<Character> crate: crates) {
            result += crate.peek();
        }
        System.out.println(result);
    }

    public static List<Stack<Character>> processStack() {
        List<String> lines = Main.readInputLines("5-stack");
        List<Stack<Character>> crates = new ArrayList<>();
        for (String line : lines) {
            Stack<Character> stack = new Stack<>();
            for (char c : line.toCharArray()) {
                stack.push(c);
            }
            crates.add(stack);
        }
        return crates;
    }
}
