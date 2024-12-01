
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class DistanceCalculation {
    public static void main(String[] args) {
        if (args.length > 0)
            System.out.println(CalculateDistance(args[0]));
        else
            System.out.println("Invalid arguments");
    }

    public static int CalculateDistance(String filePath) {
        List<Integer> A = new ArrayList<>();
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
        N /= 2; // Divide the resulting number of lines in two

        Comparator<Integer> sortByNumber = (n1, n2) -> n2 - n1;
        A.sort(sortByNumber);
        B.sort(sortByNumber);

        int totalDistance = 0;

        for (int i = 0; i < N - 1; i++)
            totalDistance += Math.abs(A.get(i) - B.get(i));

        return totalDistance;
    }
}