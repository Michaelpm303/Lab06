import java.util.Scanner;

public class ConnectFour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean roundOn = true;
        System.out.print("What would you like the height of the board to be? ");
        // Rows
        int height = scanner.nextInt();
        System.out.print("\nWhat would you like the length of the board to be? ");
        // Columns
        int length = scanner.nextInt();
        // Number of possible moves
        int spaces = length*height;
        int moves = 0;
        // 2d Array representing the board
        char[][] board = new char[height][length];
        initializeBoard(board);
        printBoard(board);
        System.out.println("\nPlayer 1: x\nPlayer 2: o");
        while (roundOn) {
            // Player 1's turn
            System.out.println("\nPlayer 1: Which column would you like to choose? ");
            int column = scanner.nextInt();
            int row = insertChip(board, column, 'x');

            printBoard(board);

            // If Player 1 wins
            if (checkIfWinner(board, column, row, 'x')) {
                System.out.println("Player 1 won the game!");
                break;
            }

            moves++;
            if (moves == spaces) {
                System.out.println("Draw. Nobody wins.");
                break;
            }

            // Player 2's turn
            System.out.println("\nPlayer 2: Which column would you like to choose? ");
            column = scanner.nextInt();
            row = insertChip(board, column, 'o');
            printBoard(board);

            // If Player 2 wins
            if (checkIfWinner(board, column, row, 'o')) {
                System.out.println("Player 2 won the game!");
                break;
            }

            moves++;
            if (moves == spaces) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }
    }
    public static void initializeBoard(char[][] array) {
        // Setting each position in 2D array to '-'
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = '-';
            }
        }
    }
    public static void printBoard(char[][] array) {
        // Printing out the 2D array with spaces in between columns and rows on new lines
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }
    public static int insertChip(char[][] array, int col, char chipType) {
        // Setting bottom-most row in the column to the player's chip
        int row = 0;
        for (int i = 0; i < array.length; i++) {
            // If space is empty (contains '-')
            if (array[i][col] == '-') {
                array[i][col] = chipType;
                row = i;
                break;
            }
        }
        return row;
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int numInARow = 1;
        boolean winner = false;
        // Checking row from top to bottom (only condition)
        if (row >= 3 && array[row][col] == array[row-1][col] && array[row-1][col] == array[row-2][col] && array[row-2][col] == array[row-3][col]) {
            winner = true;
        }
        // Checking columns
        numInARow = 1;
        // Counting number in a row from last move to the right
        for (int i = col; i < array[row].length - 1; i++) {
            if (array[row][i] == chipType) {
                if (array[row][i] == array[row][i+1]) {
                    numInARow++;
                    if (numInARow == 4) {
                        winner = true;
                    }
                }
            }

        }
        // Counting number in a row from last move to the left
        for (int i = col; i > 0; i--) {
            if (array[row][i] == chipType) {
                if (array[row][i] == array[row][i - 1]) {
                    numInARow++;
                    if (numInARow == 4) {
                        winner = true;
                    }
                }
            }
        }
        return winner;
    }
}
