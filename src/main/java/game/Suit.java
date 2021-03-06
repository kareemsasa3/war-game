//Suit.java
package game;

/**
 * @author Kareem Sasa
 * @author Tiffany Tabourne
 * UTSA CS 4773 - Assignment 2
 * Fall 2020
*/

public enum Suit {
    HEARTS("HEARTS"),
    SPADES("SPADES"),
    DIAMONDS("DIAMONDS"),
    CLUBS("CLUBS");

    private String suitText;

    Suit(String suitText) { this.setSuit(suitText); }

    /* accessors */
    public String getSuit() { return suitText; }

    public void setSuit(String suitText) { this.suitText = suitText; }
} // end class Suit