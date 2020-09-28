//Rank.java
package game;

/**
 * @author Kareem Sasa
 * UTSA CS 4773 - Assignment 2
 * Fall 2020
*/

public enum Rank {
    ACE(14, "ACE"), TWO(2, "TWO"), THREE(3, "THREE"),
    FOUR(4, "FOUR"), FIVE(5, "FIVE"), SIX(6, "SIX"),
    SEVEN(7, "SEVEN"), EIGHT(8, "EIGHT"), NINE(9, "NINE"),
    TEN(10, "TEN"), JACK(11, "JACK"), QUEEN(12, "QUEEN"),
    KING(13, "KING");

    private int value;
    private String string;

    /* constructor */
    private Rank(int value, String string) {
        this.setValue(value);
        this.setString(string);
    }

    /* accessors */
    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public String getString() { return string; }

    public void setString(String string) { this.string = string; }
} // end class Rank