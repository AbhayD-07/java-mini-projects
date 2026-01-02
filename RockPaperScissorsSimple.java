import java.util.Scanner;

public class RockPaperScissorsSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose: Rock, Paper or Scissors");
        String user = sc.nextLine();

        int compChoice = (int)(Math.random() * 3); 
        String computer = "";

        if (compChoice == 0) {
            computer = "Rock";
        } else if (compChoice == 1) {
            computer = "Paper";
        } else {
            computer = "Scissors";
        }

        System.out.println("Computer chose: " + computer);

        // Result
        if (user.equalsIgnoreCase(computer)) {
            System.out.println("It's a Tie!");
        } else if (user.equalsIgnoreCase("Rock") && computer.equals("Scissors") ||
                   user.equalsIgnoreCase("Paper") && computer.equals("Rock") ||
                   user.equalsIgnoreCase("Scissors") && computer.equals("Paper")) {
            System.out.println("You Win!");
        } else {
            System.out.println("Computer Wins!");
        }

        sc.close();
    }
}
