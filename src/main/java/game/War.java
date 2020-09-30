package game;

import java.util.ArrayList;

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
        //This part is temporary, just for testing until we figure out where to put the player scores
        ArrayList<Integer> playerScores = new ArrayList<Integer>();
        for(int i = 0; i < numberOfPlayers; i++){
            playerScores.add(0);
        }
        ////^^This part is temporary, just for testing until we figure out where to put the player scores^^



        for (int x = 0; x < maxRounds; x++) {
            //printPlayersCards();
            ArrayList<Card> currentRoundCards = new ArrayList<Card>();
                for (int i = 0; i < numberOfPlayers; i++) {
                    if (players[i].getNumberOfCards() > 0) {
                        Card toBeAdded = playCard(players[i], i);
                        currentRoundCards.add(i, toBeAdded);
                        System.out.println(playerNames[i] + " plays " + playedCards.getCards().get(i).toString());
                    } else {
                        for (int j = 0; j < numberOfPlayers; j++) {
                            if (players[j].getNumberOfCards() == 52) {
                                System.out.println("Winner is " + playerNames[j]);
                                int currentScore = playerScores.get(j);
                                currentScore++;
                                playerScores.add(j, currentScore);
                                return;
                            }
                        }
                    }
                }

                // insert comparison here
                int winnerIndex = getRoundWinner(currentRoundCards);
                if (winnerIndex != -1) {
                    giveWinnerPlayedCards(players[winnerIndex]);
                    int currentScore = playerScores.get(winnerIndex);
                    currentScore++;
                    playerScores.add(winnerIndex, currentScore);
                    System.out.println(playerNames[winnerIndex] + " wins the round!");

                } else {
                    System.out.println("*** WAR!!! ***");
                    //this.setWar(true);
                }



        }
        int maxInd = -1;
        int maxVal = -1;
        for ( int i = 0; i < numberOfPlayers; i++){
            if(playerScores.get(i) > maxVal){
                maxVal = playerScores.get(i);
                maxInd = i;
            }
            System.out.println(playerScores.get(i));
        }

        System.out.println("Winner is " + playerNames[maxInd]);




/*
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println(playerNames[i] + ":");
            System.out.println(players[i].getCards().toString());
        }
        */

    }

    public void giveWinnerPlayedCards(Hand player) {
        for (int i = playedCards.getNumberOfCards() - 1; i >= 0; i--)
            playedCards.give(playedCards.getCards().get(i), player);
    }

    public static Card playCard(Hand player, int i) {
        return player.give(player.getCards().get(0), playedCards);
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
    public Deck getDeck() {
        return deck;
    }

    public void setDeck() {
        deck = new Deck();
        deck.populate();
        deck.shuffle();
    }

    public Hand[] getPlayers() {
        return players;
    }

    public void setPlayers() {
        players = new Hand[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) players[i] = new Hand();
    }

    public Hand getPlayedCards() {
        return playedCards;
    }

    public int getRoundWinner(ArrayList<Card> currentRoundCards) {
        int winner = 0;
        int tieCount = 0;
            for(int j = 1; j < currentRoundCards.size(); j++){
                if(currentRoundCards.get(winner).compareTo(currentRoundCards.get(j)) == 0){
                    tieCount++;
                }
                else if(currentRoundCards.get(winner).compareTo(currentRoundCards.get(j)) == -1){
                    winner = j;
                }
            }
            if( currentRoundCards.size() == 2 && tieCount == 1 ) {
                return -1;
            }else if( currentRoundCards.size() == 3 && tieCount == 2 ){
                return -1;
            }else{
                return winner;
            }
    }




    public void setPlayedCards() { playedCards = new Hand(); }

    public boolean isWar() { return this.war; }

    public void setWar(boolean war) { this.war = war; }
}