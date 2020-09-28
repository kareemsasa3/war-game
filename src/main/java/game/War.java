package game;

public class War {
    public static final int cardsInDeck = 52;
    public static final int numberOfPlayers = 2;
	public static final String[] playerNames = {"Sally", "Zorbo", "Kareem"};
    public static final int cardsPerPlayer = cardsInDeck / numberOfPlayers;
    public static final int maxRounds = 20;
   
    public Deck deck;
    public static Hand[] players;
    public static Hand playedCards;
    public boolean war;

    public War() {
        this.setDeck();
        this.setPlayers();
        this.setPlayedCards();
        this.dealDeck();
        this.setWar(false);
    }

    public void beginGame() {

        for(int x = 0; x < maxRounds; x++ ){
            printPlayersCards();
            if (!this.war) {

            }


        }



            for (int i = 0; i < numberOfPlayers; i++) {
                if (players[i].getNumberOfCards() > 0) {
                    playCard(players[i], i);
                    System.out.println(playerNames[i] + " plays " + playedCards.getCards().get(i).toString());
                } else {
                    System.out.println("Winner is " + playerNames[i]);
                    break;
                }
            }

        // insert comparison here
        for (int i = 0; i < numberOfPlayers-1; i++) {
            if (playedCards.getCards().get(i).compareTo(playedCards.getCards().get(i+1)) == 1) {
                giveWinnerPlayedCards(players[i]);
                System.out.println(playerNames[i] + " wins the round!");
            } else if (playedCards.getCards().get(i).compareTo(playedCards.getCards().get(i+1)) == -1) { 
                giveWinnerPlayedCards(players[i+1]);
                System.out.println(playerNames[i+1] + " wins the round!");
            } else {
                System.out.println("*** WAR!!! ***");
                this.setWar(true);
            }
        }

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println(playerNames[i] + ":");
            System.out.println(players[i].getCards().toString());
        }
    }

    public void giveWinnerPlayedCards(Hand player) {
        for (int i = playedCards.getNumberOfCards() - 1; i >= 0; i--)
            playedCards.give(playedCards.getCards().get(i), player);
    }

    public static void playCard(Hand player, int i) {
        player.give(player.getCards().get(0), playedCards);
    }

    public void dealDeck() { 
        for (int i = 0; i < numberOfPlayers; i++) 
            this.deck.deal(players[i], War.cardsPerPlayer);
    }

    public void printPlayersCards() {
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println(playerNames[i] + ":");
            System.out.println(players[i].getCards().toString());
        }
    }

    /* accessors */
    public Deck getDeck() { return deck; }
    
    public void setDeck() {
        deck = new Deck();
        deck.populate();
        deck.shuffle();
    }

    public Hand[] getPlayers() { return players; }
    
    public void setPlayers() { 
        players = new Hand[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) players[i] = new Hand();
    }

    public Hand getPlayedCards() { return playedCards; }

    public void setPlayedCards() { playedCards = new Hand(); }

    public boolean isWar() { return this.war; }

    public void setWar(boolean war) { this.war = war; }
}