/* Low level simulation of Rock Paper Scissors */
import java.util.Scanner;				// Scanner
import java.util.Random;				// Rand

public class rps{
	public static int rand(int min, int max){
		Random r = new Random();
		int num = r.nextInt((max-min)+1);   // Generates number between min, max
		return num;
	}
	public static void game(){
		Scanner inp = new Scanner(System.in);
		System.out.println(
            "Rock, Paper, Scissors:\n" +
            "Winner of 5 games wins");
		gameLoop(inp);
	}
	public static void gameLoop(Scanner in){
        int userScore = 0;       // user initial score
	    int comp = 0;       // comp initial score
	    int FINISH = 5;     // Final score comparison
	    int min_ = 0; 	    // Val for getting rock
	    int max_ = 2;	    // Val for getting scis (paper is value of 1)
		while(userScore < FINISH && comp < FINISH){
			System.out.println("Current scores\n"+
                                "Comp: " + comp + 
                                "\nUser: " + userScore);
            int user = -1;
            do{
                System.out.print("Please enter a number (0 'rock', 1 'paper', 2 'scissors'): ");
                while(!in.hasNextInt()){
                    System.out.print("Please enter a number (0 'rock', 1 'paper', 2 'scissors'): ");
                    in.next();
                }
                user = in.nextInt();
                if (user < 0 || user > 2){
                    user = -1;
                }
            }while(user < 0);
			int computer = rand(min_, max_);
			System.out.println("You chose: " + user + " Comp chose: " + computer);
			// Paper beats Rock, Rock beats Scis, Scis beats Paper
			if (user == 0 && computer == 1){
				System.out.println("Comp schose paper. You lose this round.");
				comp++;
			}
			else if (user == 0 && computer == 2) {
				System.out.println("Computer chose scissors. You win.");
				userScore++;
			}
			else if (user == 1 && computer == 0) {
				System.out.println("Computer chose rock. You win.");
				userScore++;
			}
			else if (user == 1 && computer == 2) {
				System.out.println("Computer chose scissors. You lose.");
				comp++;
			}
			else if (user == 2 && computer == 0) {
				System.out.println("Computer chose rock. You lose.");
				comp++;
			}
			else if (user == 2 && computer == 1) {
				System.out.println("Computer chose paper. You win.");
				userScore++;
			}
			else if (user == computer){
				System.out.println("You and computer chose the same. Draw.");
			}
		}
		if (userScore > comp){
			System.out.println("You won five total rounds!");
		}
		else{
			System.out.println("You lost five total rounds. Sorry");
		}
	}
	public static void main(String... args){
		game();
	}
}