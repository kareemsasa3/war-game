//War.java
package game;

import java.util.ArrayList;
import static java.lang.System.*;

/**
 * @author Kareem Sasa
 * @author Tiffany Tabourne
 * UTSA CS 4773 - Assignment 2
 * Fall 2020
 */

public class War {
    public static final int cardsInDeck = 52;
    public static final int numberOfPlayers = 2;
    public static final String[] playerNames = {"Sally", "Zorbo", "Kareem"};
    public static final int cardsPerPlayer = cardsInDeck / numberOfPlayers;
    public static final int maxRounds = 20;

    public Deck deck;
    public static Hand[] players;
    public static Hand playedCards;
    public static ArrayList<Hand> playerPointsPileHands;
    public boolean war;

    public War() {
        this.setDeck();
        this.setPlayers();
        this.setPlayedCards();
        this.dealDeck();
        this.setWar(false);
    }

    public void beginGameSamePile() {
        int currentRound = 0;
        while (currentRound < maxRounds) {
            Hand currentRoundHand = new Hand();
            currentRoundHand = initiateRoundSamePile(currentRoundHand);
            int winnerIndex = getRoundWinner(currentRoundHand);
            if (winnerIndex == -1) simulateWar(currentRoundHand, 1);
            else {
                giveWinnerPlayedCards(players[winnerIndex]);
                out.println(playerNames[winnerIndex] + " wins the round!");
            }
            currentRound++;
        }
        getWinnerSamePile();
    }

    public void beginGameNewPile() {
        playerPointsPileHands = new ArrayList<>();
        for (int x = 0; x < numberOfPlayers; x++) playerPointsPileHands.add(new Hand());
        while(players[0].getNumberOfCards() != 0){
            Hand currentRoundHand = new Hand();
            currentRoundHand = initiateRoundNewPile(currentRoundHand);
            int winnerIndex = getRoundWinner(currentRoundHand);
            if (winnerIndex == -1) simulateWar(currentRoundHand, 2);
            else {
                out.println(playerPointsPileHands.get(winnerIndex).getNumberOfCards());
                giveWinnerToPointsPile(playerPointsPileHands.get(winnerIndex),currentRoundHand);
                out.println(playerPointsPileHands.get(winnerIndex).getNumberOfCards());
                out.println(playerNames[winnerIndex] + " wins the round!");
            }
        }
        getWinnerNewPile();
    }

    public Hand initiateRoundSamePile(Hand currentRoundHand) {
        int playerIndex = 0;
        while (playerIndex < numberOfPlayers) {
            if (players[playerIndex].getNumberOfCards() == cardsInDeck) {
                out.println("Winner is " + playerNames[playerIndex]);
                return null;
            }
            if (players[playerIndex].getNumberOfCards() > 0) playCards(playerIndex, currentRoundHand);
            playerIndex++;
        }
        return currentRoundHand;
    }

    public Hand initiateRoundNewPile(Hand currentRoundHand) {
        int playerIndex = 0;
        while (playerIndex < numberOfPlayers) {
            if (players[playerIndex].getNumberOfCards() > 0) playCards(playerIndex, currentRoundHand);
            else {
                int winner = getWinner(playerPointsPileHands);
                out.println("Winner is " + playerNames[winner]);
                return null;
            }
            playerIndex++;
        }
        return currentRoundHand;
    }

    public void simulateWar(Hand currentRoundHand, int variation) {
        Hand winnerPile = initiateWar(currentRoundHand);
        int cardsToDraw = 2;

        while (war) {
            currentRoundHand = new Hand();
            int playerIndex = 0;
            while (playerIndex < numberOfPlayers) {
                if (players[playerIndex].getNumberOfCards() < cardsToDraw) {
                    if (variation == 1) declareOpponentWinner(playerIndex);
                    if (variation == 2) declareWinner();
                }
                givePile(playerIndex, cardsToDraw, currentRoundHand, winnerPile);
                playerIndex++;
            }
            int winner = getRoundWinner(currentRoundHand);
            cardsToDraw = checkDraw(winner, winnerPile, cardsToDraw);
        }
    }

    public void declareOpponentWinner(int playerIndex) {
        if (playerIndex == 0) out.println("Winner is" + playerNames[1] + "!");
        else out.println("Winner is" + playerNames[0] + "!");
    }

    public void declareWinner() {
        int winner = getWinner(playerPointsPileHands);
        if(winner == -1) out.println("It's a tie!");
        else out.println("Winner is" + playerNames[winner] + "!");
    }

    public int checkDraw(int winner, Hand winnerPile, int cardsToDraw) {
        if (winner != -1) {
            out.println(playerNames[winner] + " wins the round!");
            for (int i = winnerPile.getNumberOfCards() - 1; i >= 0; i--)
                winnerPile.give(winnerPile.getCards().get(i), players[winner]);
            this.setWar(false);
        } else {
            cardsToDraw = cardsToDraw * 2;
        }
        return cardsToDraw;
    }

    public void givePile(int playerIndex, int cardsToDraw, Hand currentRoundHand, Hand winnerPile) {
        for (int i = 0; i < cardsToDraw - 1; i++) playCards(playerIndex, winnerPile);
        Card toBeAdded = playCard(players[playerIndex]);
        out.println(playerNames[playerIndex] + " plays " + toBeAdded.toString());
        currentRoundHand.getCards().add(toBeAdded);
        winnerPile.getCards().add(toBeAdded);
    }

    public void playCards(int playerIndex, Hand hand) {
        Card toBeAdded = playCard(players[playerIndex]);
        hand.getCards().add(playerIndex, toBeAdded);
        out.println(playerNames[playerIndex] + " plays " + hand.getCards().get(playerIndex).toString());
    }

    public Hand initiateWar(Hand currentRoundHand) {
        out.println("*** WAR!!! ***");
        this.setWar(true);
        Hand winnerPile = new Hand();
        for(int z = 0; z < currentRoundHand.getNumberOfCards(); z++){
            Card toBeAdded = currentRoundHand.getCards().get(z);
            winnerPile.getCards().add(toBeAdded);
        }
        return winnerPile;
    }

    public void getWinnerSamePile() {
        int maxInd = -1;
        int maxVal = -1;
        if (players.length == 2 && players[0].getNumberOfCards() == 26) {
            out.println("It's a Tie!");
            return;
        } else if (players.length == 3 && players[0].getNumberOfCards() == 17) {
            out.println("It's a Tie!");
            return;
        }
        int playerIndex = 0;
        while (playerIndex < numberOfPlayers) {
            if (players[playerIndex].getNumberOfCards() > maxVal) {
                maxVal = players[playerIndex].getNumberOfCards();
                maxInd = playerIndex;
            }
            out.println(players[playerIndex].getNumberOfCards());
            playerIndex++;
        }
        out.println("Winner is " + playerNames[maxInd] + "!");
        out.println(players[0].getCards().toString());
        out.println(players[1].getCards().toString());
    }

    public void getWinnerNewPile() {
        int winner = getWinner(playerPointsPileHands);
        if (winner == -1) out.println("It's a tie!");
        else out.println("Winner is " + playerNames[winner] + "!");
    }

    public void giveWinnerToPointsPile(Hand pointsPile, Hand winnerCards){
        for(int i = 0; i < winnerCards.getCards().size(); i++)
            winnerCards.give(winnerCards.getCards().get(i), pointsPile);
    }

    public void giveWinnerPlayedCards(Hand player) {
        for (int i = playedCards.getNumberOfCards() - 1; i >= 0; i--)
            playedCards.give(playedCards.getCards().get(i), player);
    }

    public static Card playCard(Hand player) {
        return player.give(player.getCards().get(0), playedCards);
    }

    public void dealDeck() {
        for (int i = 0; i < numberOfPlayers; i++)
            this.deck.deal(players[i], War.cardsPerPlayer);
    }

    public int getWinner(ArrayList<Hand> playerPointsPlies){
        int maxInt = -1;
        int maxVal = -1;
        int countMaxValues = 0;
        for(int x = 0; x < playerPointsPlies.size(); x++){
            if(playerPointsPlies.get(x).getCards().size() >= maxVal ){
                maxVal = playerPointsPlies.get(x).getCards().size();
                maxInt = x;
                countMaxValues++;
            }
        }
        if(countMaxValues == 1){
            return maxInt;
        }
        return -1;
    }

    public void setDeck() {
        deck = new Deck();
        deck.populate();
        deck.shuffle();
    }

    public void setPlayers() {
        players = new Hand[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) players[i] = new Hand();
    }

    public int getRoundWinner(Hand currentRoundCards) {
        int winner = 0;
        int tieCount = 0;
        for (int j = 1; j < currentRoundCards.getNumberOfCards(); j++)
            if (currentRoundCards.getCards().get(winner).compareTo(currentRoundCards.getCards().get(j)) == 0)
                tieCount++;
            else if (currentRoundCards.getCards().get(winner).compareTo(currentRoundCards.getCards().get(j)) == -1)
                winner = j;
        if (currentRoundCards.getNumberOfCards() == 2 && tieCount == 1) return -1;
        else if( currentRoundCards.getNumberOfCards() == 3 && tieCount == 2 ) return -1;
        return winner;
    }

    public void setPlayedCards() { playedCards = new Hand(); }

    public void setWar(boolean war) { this.war = war; }
} // end class War
