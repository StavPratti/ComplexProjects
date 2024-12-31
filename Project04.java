import java.util.Scanner;

public class Project04 {
    private static final int SIZE = 3;
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        char[][] board = initializeBoard();
        char currentPlayer = PLAYER_X;
        boolean gameWon = false;
        int moves = 0;

        Scanner scanner = new Scanner(System.in);

        while (!gameWon && moves < SIZE * SIZE) {
            printBoard(board);
            System.out.printf("Παίκτης %c, εισάγετε τη γραμμή και τη στήλη (0-%d) διαχωρισμένα με κενό: ", currentPlayer, SIZE - 1);
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!isValidMove(board, row, col)) {
                System.out.println("Η θέση είναι κατειλημμένη ή εκτός ορίων. Δοκιμάστε ξανά.");
                continue;
            }

            // Move
            board[row][col] = currentPlayer;
            moves++;

            // Check for win
            if (checkWin(board, currentPlayer)) {
                printBoard(board);
                System.out.printf("Ο Παίκτης %c κέρδισε το παιχνίδι!%n", currentPlayer);
                gameWon = true;
            } else if (moves == SIZE * SIZE) {
                printBoard(board);
                System.out.println("Το παιχνίδι έληξε ισόπαλο!");
            } else {
                // Εναλλαγή παίκτη
                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
            }
        }

        scanner.close();
    }

    //
    private static char[][] initializeBoard() {
        char[][] board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = EMPTY;
            }
        }
        return board;
    }

    // check for valid move
    private static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    // Print array
    private static void printBoard(char[][] board) {
        System.out.println("  0 1 2");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean checkWin(char[][] board, char player) {
        // Έλεγχος γραμμών
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
}

