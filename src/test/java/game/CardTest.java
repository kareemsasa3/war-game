package game;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

public class CardTest extends TestCase {

    private static Card card;

    @BeforeClass
    public void setUp() throws Exception {
        card = new Card(Rank.KING, Suit.CLUBS);
        assertTrue(card != null);
    }

    @Test
    public void testGetRank() throws Exception {
        assertEquals(card.getRank(), Rank.KING.getValue());
    }

    @Test
    public void testGetSuit() throws Exception {
        assertEquals(card.getSuit(), Suit.CLUBS.toString());
    }

    @Test
    public void testEquals() throws Exception {
        Card card2 = new Card(Rank.KING, Suit.CLUBS);
        Card card3 = new Card(Rank.QUEEN, Suit.CLUBS);
        String foo = "B4DB33F";
        Integer bar = null;
        assertEquals(card2.toString(), card.toString());
        assertFalse(card3.equals(card));
        assertFalse(card3.equals(foo));
        assertFalse(card3.equals(bar));
    }

    @Test
    public void testToString() throws Exception {
        assertTrue(card.toString().equals("KING of CLUBS"));
    }
}