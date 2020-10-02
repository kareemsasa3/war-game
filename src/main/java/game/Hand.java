//Hand.java
package game;

import java.util.ArrayList;

/**
 * @author Kareem Sasa
 * UTSA CS 4773 - Assignment 2
 * Fall 2020
*/

public class Hand {
    public ArrayList<Card> cards;
    public int numberOfCards;

    /* constructor */
    public Hand() {
        this.setCards();
        this.setNumberOfCards();
    }

    public void give(Card card, Hand currentlyPlayed) {
        if (!cards.contains(card)) return;
        else {
            cards.remove(card);
            currentlyPlayed.add(card);
        }
    }

    public String toString() {
        String str = "";
        for (Card c: cards) str += c.toString() + "\n";
        return str;
    }

    public void clear() { cards.clear(); }

    public void add(Card card) { cards.add(card); }

    /* accessors */
    public int getNumberOfCards() { return cards.size(); }

    public void setNumberOfCards() { this.numberOfCards = 0; }
    
    public ArrayList<Card> getCards() { return cards; }

    public void setCards() { this.cards = new ArrayList<Card>(); }
} //end class Hand