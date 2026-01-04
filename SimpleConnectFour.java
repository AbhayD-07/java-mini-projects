import java.util.Scanner;

public class SimpleConnectFour {
    static char[][] board = new char[6][7];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char currentPlayer = 'X';
        initBoard();

        while (true) {
            printBoard();
            System.out.print("Player " + currentPlayer + ", choose column (0-6): ");
            int col = sc.nextInt();

            if (col < 0 || col > 6) {
                System.out.println("Invalid column. Try again.");
                continue;
            }

            if (!placeDisc(currentPlayer, col)) {
                System.out.println("Column full. Try another.");
                continue;
            }

            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        sc.close();
    }

    static void initBoard() {
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                board[i][j] = '.';
    }

    static void printBoard() {
        for (char[] row : board) {
            for (char cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }

    static boolean placeDisc(char player, int col) {
        for (int i = 5; i >= 0; i--) {
            if (board[i][col] == '.') {
                board[i][col] = player;
                return true;
            }
        }
        return false;
    }

    static boolean checkWin(char p) {

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 4; j++)
                if (board[i][j] == p && board[i][j+1] == p && board[i][j+2] == p && board[i][j+3] == p)
                    return true;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 7; j++)
                if (board[i][j] == p && board[i+1][j] == p && board[i+2][j] == p && board[i+3][j] == p)
                    return true;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                if (board[i][j] == p && board[i+1][j+1] == p && board[i+2][j+2] == p && board[i+3][j+3] == p)
                    return true;

        for (int i = 3; i < 6; i++)
            for (int j = 0; j < 4; j++)
                if (board[i][j] == p && board[i-1][j+1] == p && board[i-2][j+2] == p && board[i-3][j+3] == p)
                    return true;

        return false;
    }

    static boolean isFull() {
        for (int j = 0; j < 7; j++)
            if (board[0][j] == '.')
                return false;
        return true;
    }
}
