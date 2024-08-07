import java.util.Scanner;

public class BlackJackConsole {

	public BlackJackConsole() {
		BlackJack blackJack = new BlackJack();
		Scanner scan = new Scanner(System.in);
		String choice = "";
		char lettre = ' ';

		System.out.println("-------------START-------------");
		System.out.println("The bank draw : " + blackJack.getBankHandString() );
		System.out.println("The player draw : " + blackJack.getPlayerHandString() );

		while( !(blackJack.isGameFinished()) ){
			System.out.println("Do you want another card ? [y/n] : ");

			choice = scan.next();
			lettre = choice.charAt(0);

			if(lettre == 'y'){
				try {
					blackJack.playerDrawAnotherCard();
				} catch (EmptyDeckException ex) {
					System.err.println(ex.getMessage());
					System.exit(-1);
				}
				System.out.println("Your new hand : " + blackJack.getPlayerHandString() );
			}else if(lettre == 'n'){
				try {
					blackJack.bankLastTurn();
				} catch (EmptyDeckException ex) {
					System.err.println(ex.getMessage());
					System.exit(-1);
				}
				System.out.println("The bank draw cards. New hand: " + blackJack.getBankHandString() );
			}else{
				System.out.println("Make a choice! [y/n]");
			}
		}

		System.out.println("------------RESULTS------------");
		System.out.println("\nPLAYER\n" + blackJack.getPlayerHandString() + " | Player best : " + blackJack.getPlayerBest() );
		System.out.println("\nBANK\n" + blackJack.getBankHandString() + " | Bank best : " + blackJack.getBankBest() );

		if(blackJack.isPlayerWinner()){
			System.out.println("You are the winner with " + blackJack.getPlayerBest() + " points.");
		}else if(blackJack.isBankWinner()){
			System.out.println("The bank win this game with " + blackJack.getBankBest() + " points.");
		}

		if(blackJack.getPlayerBest() == blackJack.getBankBest() ){
			System.out.println("Draw");
		}

		System.out.println("\n--------------END--------------\n");


	}
	public static void main(String[] args) {
		new BlackJackConsole();
	}
}
