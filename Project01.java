import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Project01 {

    public static void main(String[] args) {

        try (Scanner input = new Scanner(new File("C:\\Users\\stavp\\Desktop\\dummy-in.txt"));
             PrintStream ps = new PrintStream("C:\\Users\\stavp\\Desktop\\dummy-out.txt", StandardCharsets.UTF_8)) {

            // Read numbers from the input file
            int[] inputNumbers = new int[49];
            int pivot = 0;

            while (input.hasNextInt()) {
                int num = input.nextInt();
                // Only include numbers between 1 and 49
                if (num >= 1 && num <= 49) {
                    inputNumbers[pivot++] = num;
                }
            }

            // Ensure the input file contains 6-49 numbers
            if (pivot < 6 || pivot > 49 ) {
                System.out.println("The file must contain between 6 and 49 numbers!");
                return;
            }

            // Copy the valid input numbers to a new array and sort them
            int[] outputNumbers = Arrays.copyOfRange(inputNumbers, 0, pivot);
            Arrays.sort(outputNumbers);

            // Generate and filter combinations, writing only valid ones to the output file
            generateCombinations(outputNumbers, 0, new int[6], 0, ps);

            System.out.println("Process completed! Valid combinations have been saved to the file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recursive method to generate combinations of 6 numbers.
     *
     * @param outputNumbers The sorted array of input numbers.
     * @param start         The starting index for the current combination.
     * @param combination   The current combination being generated.
     * @param index         The index of the next number in the combination.
     * @param ps            The PrintStream to write valid combinations.
     */
    public static void generateCombinations(int[] outputNumbers, int start, int[] combination, int index, PrintStream ps) {
        // If the combination is complete (contains 6 numbers)
        if (index == 6) {
            if (validateCombination(combination)) {
                // Write valid combination to the output file
                ps.println(Arrays.toString(combination));
            }
            return;
        }

        // Recursively generate combinations
        for (int i = start; i < outputNumbers.length; i++) {
            combination[index] = outputNumbers[i];
            generateCombinations(outputNumbers, i + 1, combination, index + 1, ps);
        }
    }

    /**
     * Validates the given combination of numbers against the defined criteria.
     *
     * @param combination The array containing the current combination of 6 numbers.
     * @return true if the combination is valid; false otherwise.
     */
    public static boolean validateCombination(int[] combination) {
        if (hasTooManyEvens(combination)) return false;
        if (hasTooManyOdds(combination)) return false;
        if (hasTooManyContiguous(combination)) return false;
        if (hasTooManySameEnding(combination)) return false;
        if (hasTooManyInSameDecade(combination)) return false;
        return true; // Return true if the combination is valid
    }

    /**
     * Checks if the combination has more than 4 even numbers.
     *
     * @param combination The array containing the current combination.
     * @return true if invalid, false otherwise.
     */
    public static boolean hasTooManyEvens(int[] combination) {
        int evenCount = 0;
        for (int num : combination) {
            if (num % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount > 4;
    }

    /**
     * Checks if the combination has more than 4 odd numbers.
     *
     * @param combination The array containing the current combination.
     * @return true if invalid, false otherwise.
     */
    public static boolean hasTooManyOdds(int[] combination) {
        int oddCount = 0;
        for (int num : combination) {
            if (num % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount > 4;
    }

    /**
     * Checks if the combination has more than 2 consecutive numbers.
     *
     * @param combination The array containing the current combination.
     * @return true if invalid, false otherwise.
     */
    public static boolean hasTooManyContiguous(int[] combination) {
        int contiguousCount = 1;
        for (int i = 1; i < combination.length; i++) {
            if (combination[i] == combination[i - 1] + 1) {
                contiguousCount++;
                if (contiguousCount > 2) {
                    return true;
                }
            } else {
                contiguousCount = 1;
            }
        }
        return false;
    }

    /**
     * Checks if the combination has more than 3 numbers with the same ending digit.
     *
     * @param combination The array containing the current combination.
     * @return true if invalid, false otherwise.
     */
    public static boolean hasTooManySameEnding(int[] combination) {
        int[] endingCount = new int[10];
        for (int num : combination) {
            endingCount[num % 10]++;
        }
        for (int count : endingCount) {
            if (count > 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the combination has more than 3 numbers in the same decade.
     *
     * @param combination The array containing the current combination.
     * @return true if invalid, false otherwise.
     */
    public static boolean hasTooManyInSameDecade(int[] combination) {
        int[] decadeCount = new int[5];
        for (int num : combination) {
            decadeCount[num / 10]++;
        }
        for (int count : decadeCount) {
            if (count > 3) {
                return true;
            }
        }
        return false;
    }
}
