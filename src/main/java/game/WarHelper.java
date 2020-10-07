package game;

import java.util.ArrayList;

import static game.War.*;
import static java.lang.System.out;

public class WarHelper {
    public static Hand initiateRoundSamePile(Hand currentRoundHand) {
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

    public static Hand initiateRoundNewPile(Hand currentRoundHand) {
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

    public static void getWinnerSamePile() {
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

    public static void getWinnerNewPile() {
        int winner = getWinner(playerPointsPileHands);
        if (winner == -1) out.println("It's a tie!");
        else out.println("Winner is " + playerNames[winner] + "!");
    }

    public static int getWinner(ArrayList<Hand> playerPointsPlies){
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

    public static int getRoundWinner(Hand currentRoundCards) {
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

    public static void givePile(int playerIndex, int cardsToDraw, Hand currentRoundHand, Hand winnerPile) {
        for (int i = 0; i < cardsToDraw - 1; i++) playCards(playerIndex, winnerPile);
        Card toBeAdded = playCard(players[playerIndex]);
        out.println(playerNames[playerIndex] + " plays " + toBeAdded.toString());
        currentRoundHand.getCards().add(toBeAdded);
        winnerPile.getCards().add(toBeAdded);
    }

    public static void playCards(int playerIndex, Hand hand) {
        Card toBeAdded = playCard(players[playerIndex]);
        hand.getCards().add(playerIndex, toBeAdded);
        out.println(playerNames[playerIndex] + " plays " + hand.getCards().get(playerIndex).toString());
    }

    public static void declareOpponentWinner(int playerIndex) {
        if (playerIndex == 0) out.println("Winner is" + playerNames[1] + "!");
        else out.println("Winner is" + playerNames[0] + "!");
    }

    public static void declareWinner() {
        int winner = getWinner(playerPointsPileHands);
        if(winner == -1) out.println("It's a tie!");
        else out.println("Winner is" + playerNames[winner] + "!");
    }

    public static Card playCard(Hand player) {
        return player.give(player.getCards().get(0), playedCards);
    }
}
