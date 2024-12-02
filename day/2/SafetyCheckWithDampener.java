import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SafetyCheckWithDampener {
    private static int upperThreshold = 3;
    private static int lowerThreshold = 1;
    public static void main(String[] args) {
        if (args.length > 0)
            System.out.println(CountSafes(Read(args[0])));
        else
            System.out.println("Invalid arguments");
    }

    public static Set<List<Integer>> Read(String filePath) {
        Set<List<Integer>> lists = new HashSet<>();
        
        try (Scanner inputScanner = new Scanner(new File(filePath))) {
            while (inputScanner.hasNextLine()) {
                List<Integer> row = new ArrayList<>();
                for (String number : inputScanner.nextLine().split(" "))
                    row.add(Integer.valueOf(number));
                lists.add(row);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + filePath + "\" not found");
        }
        return lists;
    }

    public static int CountSafes(Set<List<Integer>> lists) {
        return (int) lists.stream().map(list -> AnalyzeList(list)).filter(Boolean::booleanValue).count();
    }

    public static boolean AnalyzeList(List<Integer> list) {
        if (list.size() <= 1) 
            return true;
        else {
            float sign = Math.signum(list.getFirst() - list.getLast());
            return sign != 0 ? AnalyzeListHelper(list, sign, 0, false) : false; 
        }
    }

    public static boolean AnalyzeListHelper(List<Integer> list, float trend, int currentIndex, boolean dampened){
        if (currentIndex < list.size() - 1) {
            int diff = list.get(currentIndex) - list.get(currentIndex + 1);
            if (Math.signum(diff) == trend && Math.abs(diff) <= upperThreshold && Math.abs(diff) >= lowerThreshold)
                return AnalyzeListHelper(list, trend, currentIndex + 1, dampened);
            else if (!dampened) {
                list.remove(currentIndex + 1);
                return AnalyzeListHelper(list, trend, currentIndex, true);
            } else 
                return false;
        } else 
            return true;
    }

}