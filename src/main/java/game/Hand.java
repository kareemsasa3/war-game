//Hand.java
package game;

import java.util.ArrayList;

/**
 * @author Kareem Sasa
 * @author Tiffany Tabourne
 * UTSA CS 4773 - Assignment 2
 * Fall 2020
*/

public class Hand {
    public ArrayList<Card> cards;
    public int numberOfCards;

    public Hand() {
        this.setCards();
        this.setNumberOfCards();
    }

    public Card give(Card card, Hand currentlyPlayed) {
        if (!cards.contains(card)) return null;
        else {
            cards.remove(card);
            currentlyPlayed.add(card);
            return card;
        }
    }

    public String toString() {
        String str = "";
        for (Card c: cards) str += c.toString() + "\n";
        return str;
    }

    public void add(Card card) { cards.add(card); }

    /* accessors */
    public int getNumberOfCards() { return cards.size(); }

    public void setNumberOfCards() { this.numberOfCards = 0; }
    
    public ArrayList<Card> getCards() { return cards; }

    public void setCards() { this.cards = new ArrayList<>(); }
} //end class Hand
