3
import java.util.Scanner;

public class SimpleTicTacToe {
    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char currentPlayer = 'X';
        int moves = 0;

        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter position (1-9): ");
            int pos = sc.nextInt() - 1;

            if (pos < 0 || pos > 8 || board[pos] != ' ') {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[pos] = currentPlayer;
            moves++;

            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (moves == 9) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            // Switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        sc.close();
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i += 3) {
            System.out.println(" " + board[i] + " | " + board[i + 1] + " | " + board[i + 2]);
            if (i < 6) System.out.println("---|---|---");
        }
        System.out.println();
    }

    static boolean checkWin(char p) {
        return (board[0] == p && board[1] == p && board[2] == p) || 
               (board[3] == p && board[4] == p && board[5] == p) || 
               (board[6] == p && board[7] == p && board[8] == p) || 
               (board[0] == p && board[3] == p && board[6] == p) || 
               (board[1] == p && board[4] == p && board[7] == p) || 
               (board[2] == p && board[5] == p && board[8] == p) || 
               (board[0] == p && board[4] == p && board[8] == p) || 
               (board[2] == p && board[4] == p && board[6] == p);   
    }
}
