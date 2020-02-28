// Jace Laquerre


import java.io.*;
import java.util.*;

public class SortCount {

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to the Sort-And-Count program.");

        // Get user input
        Scanner input = new Scanner(System.in);
        System.out.println("Enter File Name: ");
        String fileName = input.nextLine();

        // Open file and create scanner
        FileInputStream file = new FileInputStream(fileName);
        Scanner scanner = new Scanner(file);

        // Create output file
        PrintWriter writer = new PrintWriter("outputFile.txt", "UTF-8");

        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            String[] splitText = text.split(" ");
            int[] array = new int[splitText.length];

            int i = 0;
            for (String token : splitText) {
                // Minus 1 in array to account for zero
                array[++i - 1] = Integer.parseInt(token);
            }

            // Get data from methods
            // get number of inversions from SortAndCount
            int sortInv = SortAndCount(array);
        // Write to File
        writer.println("Inversion count: " + sortInv + ". Sorted array: " + Arrays.toString(array));
    }
    // Close scanner and writer
        scanner.close();
        writer.close();
        System.out.println("Output data successfully sent to outputFile.txt");
    }

    private static int SortAndCount(int[] array) {
        // If array is length 1, then it is already sorted and has no inversions
        if (array.length == 1) {
            return 0;
        }
        // Otherwise, split into left and right array's
        else {
            int[] left = Arrays.copyOfRange(array, 0, array.length/2);
            int[] right = Arrays.copyOfRange(array, array.length/2, array.length);

            return SortAndCount(left) + SortAndCount(right) + MergeAndCount(array, left, right);
        }
    }

    private static int MergeAndCount(int[] array, int[] left, int[] right) {
        int inversions = 0;
        int i = 0, n = 0;
        // Merge Sort to sort array
        while (i < left.length || n < right.length) {
            if (i == left.length) {
                array[i + n] = right[n];
                ++n;
            }
            else if (n == right.length) {
                array[i + n] = left[i];
                ++i;
            }
            else if (left[i] <= right[n]) {
                array[i + n] = left[i];
                ++i;
            }
            else {
                array[i + n] = right[n];
                inversions += left.length - i;
                ++n;
            }
        }
        return inversions;
    }
}
