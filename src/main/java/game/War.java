package game;

import java.util.ArrayList;

public class War {
    public static final String[] playerNames = {"Sally", "Zorro", "Kareem"};
    public Deck deck;
    public static Hand[] players;
    public static Hand playedCards;
    public boolean war;
    public ArrayList<Integer> playerScores;

    public War(int numberOfPlayers, int cardsPerPlayer) {
        this.setDeck();
        this.setPlayers(numberOfPlayers);
        this.setPlayedCards();
        this.setPlayerScores();
        this.dealDeck(cardsPerPlayer);
        this.setWar(false);
    }

    public void beginGame() {
        // TO-DO: NEED A WAY TO CHOOSE VARIATION 1 OR 2
        if (players.length == 2) simulateVariationOne();
        else simulateVariationThree();
        // based on number of players, simulate specific version of game
    }

    public void simulateVariationOne() {
        int count = 0;
        while (count < 20) {
            printPlayerCards();
            System.out.println("COUNT: " + count);
            for (int i = 0; i < players.length; i++) {
                if (getPlayerCardCount(i) > 0) {
                    playCard(players[i]);
                    System.out.println(playerNames[i] + " plays " + playedCards.getCards().get(i).toString());
                } else {
                    return;
                }
            }
            comparePlayedCards();
            Card playerOne = playedCards.getCards().get(playedCards.getNumberOfCards()-2);
            Card playerTwo = playedCards.getCards().get(playedCards.getNumberOfCards()-1);
            getRoundWinnerVariationOne(playerOne, playerTwo);
            count = count + 1;
        }
        if (players[0].getNumberOfCards() > players[1].getNumberOfCards())
            System.out.println("Winner is " + playerNames[0]);
        else System.out.println("Winner is " + playerNames[1]);
    }

    public void simulateVariationTwo() {

    }

    public void simulateVariationThree() {

    }

    public void getRoundWinnerVariationOne(Card playerOne, Card playerTwo) {
        System.out.println("SECOND LAST: " + playerOne.toString());
        System.out.println("LAST: " + playerTwo.toString());
        if (playerOne.compareTo(playerTwo) == 1) {
            giveWinnerPlayedCards(players[0]);
            System.out.println(playerNames[0] + " wins the round!");
        } else if (playerOne.compareTo(playerTwo) == -1) {
            giveWinnerPlayedCards(players[1]);
            System.out.println(playerNames[1] + " wins the round!");
        } else {
            System.out.println("*** WAR!!! ***");
            this.setWar(true);
        }
        if (isWar()) {
            simulateWarVariationOne();
        }
    }

    public void comparePlayedCards() {
        for (int i = playedCards.getNumberOfCards()-1; i > 0; i--) {
            if (playedCards.getCards().get(i) == null) {
                System.out.println("Winner is " + playerNames[0]);
                return;
            } else if (playedCards.getCards().get(i-1) == null) {
                System.out.println("Winner is " + playerNames[1]);
                return;
            }
        }
    }

    public void giveWinnerPlayedCards(Hand player) {
        for (int i = playedCards.getNumberOfCards() - 1; i >= 0; i--)
            playedCards.give(playedCards.getCards().get(i), player);
    }

    public void simulateWarVariationOne() {
        for (int i = 0; i < players.length; i++) {
            if (getPlayerCardCount(i) > 1) {
                playTwoCards(players[i]);
                Card lastPlayedCard = playedCards.getCards().get(playedCards.getNumberOfCards()-1);
                System.out.println("PLAYED CARDS:\n" + playedCards);
                System.out.println(playerNames[i] + " plays " + lastPlayedCard.toString());
            } else break;
        }
        comparePlayedCards();
        System.out.println("PLAYED CARDS:");
        System.out.println(playedCards);
        Card playerOne = playedCards.getCards().get(playedCards.getNumberOfCards()-3);
        Card playerTwo = playedCards.getCards().get(playedCards.getNumberOfCards()-1);
        getRoundWinnerVariationOne(playerOne, playerTwo);
        this.setWar(false);
    }

    public static void playCard(Hand player) {
        player.give(player.getCards().get(0), playedCards);
    }

    public static void playTwoCards(Hand player) {
        player.give(player.getCards().get(0), playedCards);
        player.give(player.getCards().get(1), playedCards);
    }

    public void dealDeck(int cardsPerPlayer) {
        for (Hand player : players) this.deck.deal(player, cardsPerPlayer);
    }

    public void printPlayerCards() {
        for (Hand player : players) System.out.println("PLAYER CARDS: " + "\n" + player);
    }

    /* accessors */
    public void setDeck() {
        deck = new Deck();
        deck.populate();
        deck.shuffle();
    }

    public void setPlayerScores() {
        playerScores = new ArrayList<>();
        for(int i = 0; i < players.length; i++) playerScores.add(0);
    }

    public void setPlayers(int numberOfPlayers) {
        players = new Hand[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) players[i] = new Hand();
    }

    public void setPlayedCards() { playedCards = new Hand(); }

    public int getPlayerCardCount(int i) { return players[i].getNumberOfCards(); }

    public boolean isWar() { return this.war; }

    public void setWar(boolean war) { this.war = war; }
}
