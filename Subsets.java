import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Subsets {
    
    /**
     * The function getSubSets() computes and returns all subsets of a given set A.
     * - Receive the input set A with at most 4 elements
     * - Convert the set to a list to allow indexed access
     * - Calculate the total number of subsets as 2^n using size() method
     * - Use bit manipulation to generate all possible combinations
     * - Iterate from 0 to (2^n - 1) where each number represents a subset
     * - For each number, check which bits are set to determine elements to include
     * - Use add() method to add elements to each subset
     * - Use add() method to add each subset to the power set
     * - Return the power set containing all subsets
     */
    public static <T> Set<Set<T>> getSubSets(Set<T> A) {
        Set<Set<T>> powerSet = new HashSet<>();
        List<T> elements = new ArrayList<>(A);
        int n = A.size();
        int totalSubsets = (int) Math.pow(2, n);
        
        for (int i = 0; i < totalSubsets; i++) {
            Set<T> subset = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(elements.get(j));
                }
            }
            powerSet.add(subset);
        }
        
        return powerSet;
    }
    
    /**
     * Auxiliary function to print the power set in the required format.
     * - Use iterator() method to iterate over all subsets
     * - Format output as [[1,2,3],[1,2],[1,3],[2,3],[1],[2],[3],[]]
     * - Print each subset with proper comma separation
     */
    public static void printPowerSet(Set<Set<Integer>> powerSet) {
        List<List<Integer>> list = new ArrayList<>();
        
        for (Set<Integer> s : powerSet) {
            List<Integer> sub = new ArrayList<>(s);
            for (int i = 0; i < sub.size(); i++)
                for (int j = i + 1; j < sub.size(); j++)
                    if (sub.get(i) > sub.get(j)) {
                        int t = sub.get(i); sub.set(i, sub.get(j)); sub.set(j, t);
                    }
            list.add(sub);
        }
        
        for (int i = 0; i < list.size(); i++)
            for (int j = i + 1; j < list.size(); j++) {
                List<Integer> a = list.get(i), b = list.get(j);
                boolean swap = a.size() < b.size();
                if (a.size() == b.size())
                    for (int k = 0; k < a.size(); k++)
                        if (a.get(k) != b.get(k)) { swap = a.get(k) > b.get(k); break; }
                if (swap) { list.set(i, b); list.set(j, a); }
            }
        
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) System.out.print(",");
            System.out.print("[");
            for (int j = 0; j < list.get(i).size(); j++) {
                if (j > 0) System.out.print(",");
                System.out.print(list.get(i).get(j));
            }
            System.out.print("]");
        }
        System.out.println("]");
    }
    
    /**
     * The main function below will:
     * - Obtain the input from the user (number of elements and their values)
     * - Validate that the set has at most 4 elements
     * - Create a Set and use add() method to add each element
     * - Call getSubSets() function to compute all subsets
     * - Print the output in the required format
     * - Return the power set
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> A = new HashSet<>();
        
        System.out.println("Enter the number of elements (max 4):");
        int n = scanner.nextInt();
        
        if (n > 4) {
            System.out.println("Error: Maximum 4 elements allowed.");
            scanner.close();
            return;
        }
        
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            A.add(scanner.nextInt());
        }
        
        scanner.close();
        
        System.out.println("\nInput: A=" + A);
        System.out.print("Output: ");
        
        Set<Set<Integer>> result = getSubSets(A);
        printPowerSet(result);
    }
}
