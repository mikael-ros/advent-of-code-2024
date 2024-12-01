import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SimilarityScore {
    public static void main(String[] args) {
        if (args.length > 0)
            System.out.println(CalculateSimScore(args[0]));
        else
            System.out.println("Invalid arguments");
    }

    public static int CalculateSimScore(String filePath) {
        Set<Integer> A = new HashSet<>();
        List<Integer> B = new ArrayList<>();

        int N = 0;
        
        try (Scanner inputScanner = new Scanner(new File(filePath))) {
            while (inputScanner.hasNext()) {
                N++;
                int number = Integer.parseInt(inputScanner.next());
                
                if (N % 2 == 0)
                    B.add(number);
                else
                    A.add(number);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + filePath + "\" not found");
        }
        N /= 2;
        B.sort((n1, n2) -> n1 - n2);

        int simScore = 0;

        for (int a : A) {
            int beginIndex = B.indexOf(a);
            int distance = 0;

            if (beginIndex != -1) {
                int nextIndex = beginIndex + distance;
                
                while ((nextIndex = beginIndex + distance) != N 
                        && B.get(beginIndex + distance) == a)
                    distance++;
            }
            simScore += distance * a;
        }

        return simScore;
    }
}