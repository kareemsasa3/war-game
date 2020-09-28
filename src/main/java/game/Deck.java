//Deck.java
package game;

import java.util.Random;

/**
 * @author Kareem Sasa
 * UTSA CS 4773 - Assignment 2
 * Fall 2020
*/

public class Deck extends Hand {
    Random random = new Random();

    public void populate() {
        for (Suit suit: Suit.values()) {
            for (Rank rank: Rank.values()) {
                Card card = new Card(rank, suit);
                this.add(card);
            }
        }
    }

    public void shuffle() {
        for (int i = this.getNumberOfCards() - 1; i > 0; i--) {
            // condense to 3 LOC if possible
            int pick = random.nextInt(i);
            Card randomCard = cards.get(pick);
            Card lastCard = cards.get(i);
            cards.set(i, randomCard);
            cards.set(pick, lastCard);
        }
    }

    public void deal(Hand[] hands, int perHand) {
        for (int i = 0; i < perHand; i++)
            for (int j = 0; j < hands.length; j++) 
                this.give(cards.get(0), hands[j]);
    }

    public void deal(Hand hand, int perHand) {
        for (int i = 0; i < perHand; i++) this.give(cards.get(0), hand);
    }

    public void flipCard(Card card) { card.flipCard(); }
} //end class Deck