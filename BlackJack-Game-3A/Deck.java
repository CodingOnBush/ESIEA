import java.util.LinkedList;
import java.util.Collections;

public class Deck {
	public LinkedList<Card> cardList;


	public Deck(int nbBox){
		cardList = new LinkedList<Card>();

		if(nbBox > 0){
			for ( int i = 0; i<nbBox ; i++ ) {
				for ( Color c: Color.values() ) {
					for ( Value v: Value.values() ){
						cardList.add(new Card(v,c));
					}
				}
			}
			Collections.shuffle(cardList);
		}
	}

	public String toString(){
		return cardList + "";
	}

	public Card draw() throws EmptyDeckException {
		if(cardList != null){
			return cardList.pollFirst();
		} else {
			throw new EmptyDeckException("Empty Deck!");
		}
	}

}
