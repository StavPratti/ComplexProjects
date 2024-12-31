import java.io.*;

public class Project03 {

    public static void main(String[] args) {
        int[][] charTable = new int[128][2];

        for (int i = 0; i < 128; i++) {
            charTable[i][0] = i; // ASCII character
            charTable[i][1] = 0; // Frequency
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\stavp\\Desktop\\dummy-in.txt"))) {
            int c;
            while ((c = reader.read()) != -1) {
                if (Character.isLetter(c)) {
                    c = Character.toLowerCase(c); // Ενιαία μικρογράμματη μορφή
                    charTable[c][1]++; // Αύξηση συχνότητας
                }
            }
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά την ανάγνωση του αρχείου: " + e.getMessage());
            return;
        }

        System.out.println("Στατιστικά ανά χαρακτήρα:");
        sortByCharacter(charTable);
        printTable(charTable);

        System.out.println("\nΣτατιστικά ανά συχνότητα:");
        sortByFrequency(charTable);
        printTable(charTable);
    }

    private static void sortByCharacter(int[][] table) {
        for (int i = 0; i < table.length - 1; i++) {
            for (int j = i + 1; j < table.length; j++) {
                if (table[i][0] > table[j][0]) {
                    swapRows(table, i, j);
                }
            }
        }
    }

    private static void sortByFrequency(int[][] table) {
        for (int i = 0; i < table.length - 1; i++) {
            for (int j = i + 1; j < table.length; j++) {
                if (table[i][1] < table[j][1]) {
                    swapRows(table, i, j);
                }
            }
        }
    }

    private static void printTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            if (table[i][1] > 0) {
                System.out.printf("Χαρακτήρας '%c': %d φορές%n", table[i][0], table[i][1]);
            }
        }
    }

    private static void swapRows(int[][] table, int i, int j) {
        int tempChar = table[i][0];
        int tempFreq = table[i][1];
        table[i][0] = table[j][0];
        table[i][1] = table[j][1];
        table[j][0] = tempChar;
        table[j][1] = tempFreq;
    }
}
