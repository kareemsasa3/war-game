//War.java
package game;

import java.util.ArrayList;

import static game.WarHelper.*;
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

    public Hand initiateWar(Hand currentRoundHand) {
        out.println("*** WAR!!! ***");
        this.setWar(true);
        Hand winnerPile = new Hand();
        for (int z = 0; z < currentRoundHand.getNumberOfCards(); z++) {
            Card toBeAdded = currentRoundHand.getCards().get(z);
            winnerPile.getCards().add(toBeAdded);
        }
        return winnerPile;
    }

    public void giveWinnerToPointsPile(Hand pointsPile, Hand winnerCards){
        for(int i = 0; i < winnerCards.getCards().size(); i++)
            winnerCards.give(winnerCards.getCards().get(i), pointsPile);
    }

    public void giveWinnerPlayedCards(Hand player) {
        for (int i = playedCards.getNumberOfCards() - 1; i >= 0; i--)
            playedCards.give(playedCards.getCards().get(i), player);
    }
    
    public void dealDeck() {
        for (int i = 0; i < numberOfPlayers; i++)
            this.deck.deal(players[i], War.cardsPerPlayer);
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

    public void setPlayedCards() { playedCards = new Hand(); }

    public void setWar(boolean war) { this.war = war; }
} // end class War