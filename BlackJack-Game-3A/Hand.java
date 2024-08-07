import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;

public class Hand{
	LinkedList<Card> cardList;


	public Hand(){
		cardList = new LinkedList<Card>();
	}

	public String toString(){
		return cardList + "";
	}

	public void add(Card card){
		cardList.add(card);
	}

	public void clear(){
		cardList.clear();
	}

	public List<Integer> count(){
		LinkedList<Integer> result = new LinkedList<Integer>();
		result.add(0);

        for(Card c: cardList){
        	for ( int i = 0 ; i<result.size() ; i++ ) {
				result.set(i, result.get(i) + c.getPoints());
				if(c.getPoints() == 1){
					result.add(result.get(i) + 11);
				}
			}
        }
		return result;
	}

	public int best(){
		List<Integer> list = count();
		int best = 0;
		for(Integer currentVal: list){
			if(currentVal <= 21 && currentVal > best){
				best = currentVal;
			}
		}
		return best;
	}

	public List<Card> getCardList(){
		LinkedList<Card> list = new LinkedList<Card>();

		for(Card c: cardList){
			list.add(c);
		}
		return list;
	}

}
