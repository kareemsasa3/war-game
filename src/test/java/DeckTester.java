import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import game.Deck;

public class DeckTester {
    private static Deck deck;
    private static String expectedDeck;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
        deck.populate();
        StringBuffer fileContents = new StringBuffer();
        Scanner fileInput = new Scanner(new File("expectedDeck.txt"));
        while (fileInput.hasNextLine())
            fileContents.append(fileInput.nextLine() + "\n");
        expectedDeck = fileContents.toString();
        fileInput.close();
    }

    @Test
    public void testPopulate() throws Exception {
        assertEquals(expectedDeck, deck.toString());
    }
}
