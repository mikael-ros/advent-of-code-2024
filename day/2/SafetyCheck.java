import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SafetyCheck {
    public static void main(String[] args) {
        if (args.length > 0)
            System.out.println(CountSafes(Read(args[0])));
        else
            System.out.println("Invalid arguments");
    }

    public static Set<List<Integer>> Read(String filePath) {
        Set<List<Integer>> lists = new HashSet<>();
        
        try (Scanner inputScanner = new Scanner(new File(filePath))) {
            while (inputScanner.hasNext()) {
                List<Integer> row = new ArrayList<>();
                for (String number : inputScanner.next().split(" "))
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
        return list.size() <= 1 ? true : AnalyzeListHelper(list, 0, list.size() - 1, list.getFirst() > list.getLast());
    }

    public static boolean AnalyzeListHelper(List<Integer> list, int currHeadIndex, int currTailIndex, boolean trend){
        if (currTailIndex != Math.round(list.size()/2)) {
            return list.get(currHeadIndex) > list.get(currTailIndex) == trend
                ? AnalyzeListHelper(list, currHeadIndex+1, currTailIndex-1, trend)
                : false;
        } else {
            return true;
        }
    }

}