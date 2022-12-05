import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
}
