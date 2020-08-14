package group3;
/*
Card class holding enum Rank and Suit
*/

public class Card {

	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank){
		this.suit = suit;
		this.rank = rank;
	}
	
	public String toString(){
		return this.suit.toString() + "-" + this.rank.toString();
	}
        
        public Rank getRank() {
            return rank;
        }


	
}
