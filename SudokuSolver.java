import java.util.Scanner;

public class SudokuSolver {
    private static final int SIZE = 9;
    private static final int EMPTY = 0; 

   
    private static boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    
    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    
    private static void printBoard(int[][] board) {
        System.out.println("  -----------------------");
        for (int row = 0; row < SIZE; row++) {
            System.out.print(" | ");
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] == 0 ? " " : board[row][col]);
                System.out.print(" ");
                if ((col + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((row + 1) % 3 == 0) {
                System.out.println("  -----------------------");
            }
        }
    }

    
    private static void displaySampleInput() {
        System.out.println("Sample Sudoku Grid (use 0 for empty cells):");
        System.out.println("5 3 0 | 0 7 0 | 0 0 0");
        System.out.println("6 0 0 | 1 9 5 | 0 0 0");
        System.out.println("0 9 8 | 0 0 0 | 0 6 0");
        System.out.println("------+-------+------");
        System.out.println("8 0 0 | 0 6 0 | 0 0 3");
        System.out.println("4 0 0 | 8 0 3 | 0 0 1");
        System.out.println("7 0 0 | 0 2 0 | 0 0 6");
        System.out.println("------+-------+------");
        System.out.println("0 6 0 | 0 0 0 | 2 8 0");
        System.out.println("0 0 0 | 4 1 9 | 0 0 5");
        System.out.println("0 0 0 | 0 8 0 | 0 7 9");
        System.out.println();
        System.out.println("Enter your Sudoku puzzle in the same format:");
    }

   
    private static void takeInput(int[][] board) {
        Scanner scanner = new Scanner(System.in);
        displaySampleInput();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = scanner.nextInt();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];
        takeInput(board);

        System.out.println("Initial Sudoku puzzle:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("Solved Sudoku puzzle:");
            printBoard(board);
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
        }
    }
}
