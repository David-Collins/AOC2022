import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Day23 {
    public static List<Main.Function2<List<Coord>, Coord, Boolean>> checkDirections;
    public static List<Function<Coord, Coord>> movements;

    public static void main(String[] args){
        List<String> lines = Main.readInputLines("23");
        System.out.println("Part 1:");
        part1(lines);
        System.out.println("Part 2:");
        part2(lines);
    }
    public static void part1(List<String> lines) {
        setMovements();
        List<Coord> elves = findElves(lines);
        for (int i = 0; i < 10; i++) {
            elves = performMovements(elves);
        }
        elves.sort(Comparator.comparing(Coord::getX));
        int xDiff = Math.abs(elves.get(0).x - elves.get(elves.size() - 1).x) + 1;
        elves.sort(Comparator.comparing(Coord::getY));
        int yDiff = Math.abs(elves.get(0).y - elves.get(elves.size() - 1).y) + 1;
        System.out.println(xDiff * yDiff - elves.size());
    }
    public static void part2(List<String> lines) {
        setMovements();
        List<Coord> elves = findElves(lines);
        int round = 1;
        List<Coord> results = performMovements(elves);
        while (!results.equals(elves)) {
            round++;
            elves = results;
            results = performMovements(elves);
        }
        System.out.println(round);
    }

    public static List<Coord> findElves(List<String> lines) {
        List<Coord> elves = new ArrayList<>();
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                if (lines.get(y).charAt(x) == '#') {
                    elves.add(new Coord(x, y));
                }
            }
        }
        return elves;
    }

    public static List<Coord> performMovements(List<Coord> elves) {
        List<Coord> moves = new ArrayList<>();
        for (Coord elf : elves) {
            move(elf, elves, moves);
        }
        List<Coord> newLocs = new ArrayList<>();
        for (int j = 0; j < moves.size(); j++) {
            if (moves.lastIndexOf(moves.get(j)) != moves.indexOf(moves.get(j))) {
                newLocs.add(elves.get(j));
            }
            else {
                newLocs.add(moves.get(j));
            }
        }
        checkDirections.add(checkDirections.remove(0));
        movements.add(movements.remove(0));
        return newLocs;
    }

    public static void move(Coord curr, List<Coord> elves, List<Coord> moves) {
        Coord temp;
        if (!elves.contains(new Coord(curr.x, curr.y - 1)) &&
            !elves.contains(new Coord(curr.x, curr.y + 1)) &&
            !elves.contains(new Coord(curr.x - 1, curr.y)) &&
            !elves.contains(new Coord(curr.x + 1, curr.y)) &&
            !elves.contains(new Coord(curr.x + 1, curr.y - 1)) &&
            !elves.contains(new Coord(curr.x - 1, curr.y - 1)) &&
            !elves.contains(new Coord(curr.x + 1, curr.y + 1)) &&
            !elves.contains(new Coord(curr.x - 1, curr.y + 1))
        ) {
            temp = curr;
        }
        else if (checkDirections.get(0).apply(elves, curr)) {
            temp = movements.get(0).apply(curr);
        }
        else if (checkDirections.get(1).apply(elves, curr)) {
            temp = movements.get(1).apply(curr);
        }
        else if (checkDirections.get(2).apply(elves, curr)) {
            temp = movements.get(2).apply(curr);
        }
        else if (checkDirections.get(3).apply(elves, curr)) {
            temp = movements.get(3).apply(curr);
        }
        else {
            temp = curr;
        }
        moves.add(temp);
    }

    public static void setMovements() {
        checkDirections = new ArrayList<>();
        movements = new ArrayList<>();
        checkDirections.add((elves, curr) -> !elves.contains(new Coord(curr.x, curr.y - 1)) &&
                !elves.contains(new Coord(curr.x + 1, curr.y - 1)) &&
                !elves.contains(new Coord(curr.x - 1, curr.y - 1)));
        checkDirections.add((elves, curr) -> !elves.contains(new Coord(curr.x, curr.y + 1)) &&
                !elves.contains(new Coord(curr.x + 1, curr.y + 1)) &&
                !elves.contains(new Coord(curr.x - 1, curr.y + 1)));
        checkDirections.add((elves, curr) -> !elves.contains(new Coord(curr.x - 1, curr.y)) &&
                !elves.contains(new Coord(curr.x - 1, curr.y - 1)) &&
                !elves.contains(new Coord(curr.x - 1, curr.y + 1)));
        checkDirections.add((elves, curr) -> !elves.contains(new Coord(curr.x + 1, curr.y)) &&
                !elves.contains(new Coord(curr.x + 1, curr.y - 1)) &&
                !elves.contains(new Coord(curr.x + 1, curr.y + 1)));
        movements.add((Coord curr) -> new Coord(curr.x, curr.y - 1));
        movements.add((Coord curr) -> new Coord(curr.x, curr.y + 1));
        movements.add((Coord curr) -> new Coord(curr.x - 1, curr.y));
        movements.add((Coord curr) -> new Coord(curr.x + 1, curr.y));
    }
}
