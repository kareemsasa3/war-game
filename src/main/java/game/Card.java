//Card.java
package game;

/**
 * @author Kareem Sasa
 * UTSA CS 4773 - Assignment 2
 * Fall 2020
*/

public class Card {
    private Rank rank;
    private Suit suit;
    public boolean isFaceUp;

    /* constructor */
    public Card(Rank rank, Suit suit) {
        this.setRank(rank);
        this.setSuit(suit);
        this.isFaceUp = false;
    }

    public int compareTo(Card card) {
        if (this.getRank() > card.getRank()) return 1;
        else if (this.getRank() < card.getRank()) return -1;
        else return 0;
    }

    public void flipCard() { isFaceUp = !isFaceUp; }

    public String toString() { return rank.getString() +  " of " + suit.getSuit(); }

    /* accessors */
    public int getRank() { return rank.getValue(); }

    public void setRank(Rank rank) { this.rank = rank; }

    public String getSuit() { return suit.getSuit(); }

    public void setSuit(Suit suit) { this.suit = suit; }
} //end class Card