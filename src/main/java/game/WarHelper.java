package game;

import java.util.ArrayList;

import static game.War.*;
import static java.lang.System.out;

public class WarHelper {
    public static Hand initiateRoundSamePile(Hand currentRoundHand) {
        int playerIndex = 0;
        while (playerIndex < numberOfPlayers) {
            if (players[playerIndex].getNumberOfCards() == cardsInDeck) {
                output = output + "Winner is " + playerNames[playerIndex] + "\n";
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
                output = output + "Winner is " + playerNames[winner] + "\n";
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
            output = output + "It's a Tie!" + "\n";
            return;
        } else if (players.length == 3 && players[0].getNumberOfCards() == 17) {
            output = output + "It's a Tie!" + "\n";
            return;
        }
        int playerIndex = 0;
        while (playerIndex < numberOfPlayers) {
            if (players[playerIndex].getNumberOfCards() > maxVal) {
                maxVal = players[playerIndex].getNumberOfCards();
                maxInd = playerIndex;
            }
            output = output + players[playerIndex].getNumberOfCards() + "\n";
            playerIndex++;
        }
        output = output + "Winner is " + playerNames[maxInd] + "!\n";

    }

    public static void getWinnerNewPile() {
        int winner = getWinner(playerPointsPileHands);
        if (winner == -1) output = output + "It's a tie!\n";
        else output = output + "Winner is " + playerNames[winner] + "!\n";
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
        output = output + playerNames[playerIndex] + " plays " + toBeAdded.toString() + "\n";
        currentRoundHand.getCards().add(toBeAdded);
        winnerPile.getCards().add(toBeAdded);
    }

    public static void playCards(int playerIndex, Hand hand) {
        Card toBeAdded = playCard(players[playerIndex]);
        hand.getCards().add(playerIndex, toBeAdded);
        output = output + playerNames[playerIndex] + " plays " + hand.getCards().get(playerIndex).toString() + "\n";
    }

    public static void declareOpponentWinner(int playerIndex) {
        if (playerIndex == 0) output = output + "Winner is" + playerNames[1] + "!\n";
        else output = output + "Winner is" + playerNames[0] + "!\n";
    }

    public static void declareWinner() {
        int winner = getWinner(playerPointsPileHands);
        if(winner == -1) output = output + "It's a tie!\n";
        else output = output + "Winner is" + playerNames[winner] + "!\n";
    }

    public static Card playCard(Hand player) {
        return player.give(player.getCards().get(0), playedCards);
    }
}
