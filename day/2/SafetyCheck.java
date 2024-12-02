import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SafetyCheck {
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
        return lists.stream().map(list -> AnalyzeList(list)).mapToInt(res -> res ? 1 : 0).sum();
    }

    public static boolean AnalyzeList(List<Integer> list) {
        if (list.size() <= 1) 
            return true;
        else {
            float sign = Math.signum(list.getFirst() - list.getLast());
            return sign != 0 ? AnalyzeListHelper(list, sign, 1) : false;
        }
    }

    public static boolean AnalyzeListHelper(List<Integer> list, float trend, int currentIndex){
        if (currentIndex < list.size()) {
            int diff = list.get(currentIndex - 1) - list.get(currentIndex);
            if (Math.signum(diff) == trend && Math.abs(diff) <= upperThreshold && Math.abs(diff) >= lowerThreshold)
                return AnalyzeListHelper(list, trend, currentIndex + 1);
            else 
                return false;
        } else 
            return true;
    }

}