import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {}
    public static List<String> readInputLines(int day) {
        try{
            File text = new File(System.getProperty("user.dir") + "/input/day" + day +".txt");
            Scanner sc = new Scanner(text);
            List<String> lines = new ArrayList<>();
            while(sc.hasNextLine()) {
                String currLine = sc.nextLine();
                lines.add(currLine);
            }
            return lines;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    public static List<String> readInputLines(String day) {
        try{
            File text = new File(System.getProperty("user.dir") + "/input/day" + day +".txt");
            Scanner sc = new Scanner(text);
            int acc = 0;
            List<String> lines = new ArrayList<>();
            while(sc.hasNextLine()) {
                String currLine = sc.nextLine();
                lines.add(currLine);
            }
            return lines;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static Matcher applyRegex(String regex, String toTest) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(toTest);
        m.find();
        return m;
    }

    @FunctionalInterface
    public interface Function2<One, Two, Three> {
        public Three apply(One one, Two two);
    }
}
