import java.util.Scanner;

public class Project05 {
    private static final int ROWS = 30;
    private static final int COLUMNS = 12;
    private boolean[][] seats = new boolean[ROWS][COLUMNS];

    public Project05() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                seats[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
        Project05 theater = new Project05();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nΔιαχείριση Θεάτρου:");
            System.out.println("1. Κράτηση θέσης");
            System.out.println("2. Ακύρωση κράτησης");
            System.out.println("3. Εμφάνιση θέσεων");
            System.out.println("4. Έξοδος");
            System.out.print("Επιλέξτε μία επιλογή: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Εισάγετε τη στήλη (A-L): ");
                    char column = scanner.next().charAt(0);
                    System.out.print("Εισάγετε τη σειρά (1-30): ");
                    int row = scanner.nextInt();
                    theater.book(column, row);
                }
                case 2 -> {
                    System.out.print("Εισάγετε τη στήλη (A-L): ");
                    char column = scanner.next().charAt(0);
                    System.out.print("Εισάγετε τη σειρά (1-30): ");
                    int row = scanner.nextInt();
                    theater.cancel(column, row);
                }
                case 3 -> theater.displaySeats();
                case 4 -> exit = true;
                default -> System.out.println("Μη έγκυρη επιλογή. Δοκιμάστε ξανά.");
            }
        }

        scanner.close();
    }


    // Booking
    public void book(char column, int row) {
        int colIndex = column - 'A';
        int rowIndex = row - 1;

        if (isValidSeat(rowIndex, colIndex)) {
            if (!seats[rowIndex][colIndex]) {
                seats[rowIndex][colIndex] = true;
                System.out.printf("Η θέση %c%d κρατήθηκε με επιτυχία.%n", column, row);
            } else {
                System.out.printf("Η θέση %c%d είναι ήδη κρατημένη.%n", column, row);
            }
        } else {
            System.out.printf("Η θέση %c%d δεν υπάρχει στο θέατρο.%n", column, row);
        }
    }

    // Cancellation
    public void cancel(char column, int row) {
        int colIndex = column - 'A'; // Μετατροπή χαρακτήρα στήλης σε δείκτη
        int rowIndex = row - 1;     // Μετατροπή αριθμού σειράς σε δείκτη (0-based)

        if (isValidSeat(rowIndex, colIndex)) {
            if (seats[rowIndex][colIndex]) {
                seats[rowIndex][colIndex] = false;
                System.out.printf("Η κράτηση της θέσης %c%d ακυρώθηκε με επιτυχία.%n", column, row);
            } else {
                System.out.printf("Η θέση %c%d δεν ήταν κρατημένη.%n", column, row);
            }
        } else {
            System.out.printf("Η θέση %c%d δεν υπάρχει στο θέατρο.%n", column, row);
        }
    }

    // Validation
    private boolean isValidSeat(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    // Print
    public void displaySeats() {
        System.out.print("  ");
        for (char c = 'A'; c < 'A' + COLUMNS; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < ROWS; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print((seats[i][j] ? "X" : "O") + " ");
            }
            System.out.println();
        }
    }

}
