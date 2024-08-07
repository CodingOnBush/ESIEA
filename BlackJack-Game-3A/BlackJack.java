import java.util.*;

public class BlackJack{
	private Deck deck;
	private Hand playerHand;
	private Hand bankHand;
	public boolean gameFinished = false;


	public BlackJack(){
		this.deck = new Deck(4);
		this.playerHand = new Hand();
		this.bankHand = new Hand();
		this.gameFinished = false;
		try {
			reset();
		} catch (EmptyDeckException ex) {
			System.err.println(ex.getMessage()+" | Error, Reset");
			System.exit(-1);
		}
	}

	public void reset() throws EmptyDeckException{
		playerHand.clear();
		bankHand.clear();
		this.gameFinished = false;
		bankHand.add(deck.draw());
		playerHand.add(deck.draw());
		playerHand.add(deck.draw());
	}

	public String getPlayerHandString(){
		return playerHand + " : " + playerHand.count();
	}

	public String getBankHandString(){
		return bankHand + " : " + bankHand.count();
	}

	public int getPlayerBest(){
		return playerHand.best();
	}

	public int getBankBest(){
		return bankHand.best();
	}

	public boolean isPlayerWinner(){
		return (getPlayerBest() > 0 ) && (getPlayerBest() >= getBankBest());
	}

	public boolean isBankWinner(){
		return (getBankBest() > 0 ) && ( getBankBest() > getPlayerBest() );
	}

	public boolean isGameFinished(){
		return gameFinished;
	}

	public void playerDrawAnotherCard() throws EmptyDeckException{
		if( !isGameFinished() ){
			playerHand.add(deck.draw());
			if( getPlayerBest() == 0 || getPlayerBest() == 21){
				this.gameFinished = true;
			}
		}
	}

	public void bankLastTurn() throws EmptyDeckException{
		while( !isGameFinished() ){
			bankHand.add(deck.draw());
			if(getBankBest() == 0 || getBankBest() == 21){
				this.gameFinished = true;
				break;
			}
		}
	}

	public List<Card> getPlayerCardList(){
		List<Card> originalList = playerHand.getCardList();
		List<Card> copyList = new ArrayList<Card>(originalList);
		return copyList;
	}

	public List<Card> getBankCardList(){
		List<Card> originalList = bankHand.getCardList();
		List<Card> copyList = new ArrayList<Card>(originalList);
		return copyList;
	}

}
