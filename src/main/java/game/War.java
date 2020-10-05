package game;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
        //This part is temporary, just for testing until we figure out where to put the player scores
        ////^^This part is temporary, just for testing until we figure out where to put the player scores^^
        war = false;
        for (int x = 0; x < maxRounds; x++) {
            Hand currentRoundHand = new Hand();
                for (int i = 0; i < numberOfPlayers; i++) {
                    if (players[i].getNumberOfCards() > 0) {
                        Card toBeAdded = playCard(players[i], i);
                        currentRoundHand.getCards().add(i, toBeAdded);
                        System.out.println(playerNames[i] + " plays " + playedCards.getCards().get(i).toString());
                    } else {
                        for (int j = 0; j < numberOfPlayers; j++) {
                            if (players[j].getNumberOfCards() == 52) {
                                System.out.println("Winner is " + playerNames[j]);
                                return;
                            }
                        }
                    }
                }

                // insert comparison here
                int winnerIndex = getRoundWinner(currentRoundHand);
                if (winnerIndex != -1) {
                    giveWinnerPlayedCards(players[winnerIndex]);
                    System.out.println(playerNames[winnerIndex] + " wins the round!");

                } else {
                    System.out.println("*** WAR!!! ***");
                    this.setWar(true);
                    int cardsToDraw = 2;
                    Hand winnerPile = new Hand();
                    for(int z = 0; z < currentRoundHand.getNumberOfCards(); z++){
                        Card toBeAdded = currentRoundHand.getCards().get(z);
                        winnerPile.getCards().add(toBeAdded);
                    }
                    while (war == true) {
                        LinkedHashMap<Card, Integer> warComparison = new LinkedHashMap<Card, Integer>();
                        Hand cardWarComparision = new Hand();
                        int notEnoughCardsCount = 0;
                        for (int z = 0; z < players.length; z++) {
                            if (players[z].getNumberOfCards() < cardsToDraw) {
                                if (players.length == 2) {
                                    if (z == 0) {
                                        System.out.println("Winner is" + playerNames[1] + "!");
                                        return;
                                    } else {
                                        System.out.println("Winner is" + playerNames[0] + "!");
                                        return;
                                    }
                                } else {
                                    notEnoughCardsCount++;
                                    if (notEnoughCardsCount == 2) {
                                        for (int t = 0; t < players.length; t++) {
                                            if (players[t].getNumberOfCards() >= cardsToDraw) {
                                                System.out.println("Winner is" + playerNames[t] + "!");
                                                return;
                                            }
                                        }
                                    }

                                }
                            } else {
                                for (int i = 0; i < cardsToDraw - 1; i++) {
                                    Card toBeAdded = playCard(players[z], z);
                                    winnerPile.getCards().add(toBeAdded);
                                    System.out.println(playerNames[z] + " plays " + toBeAdded.toString());
                                }
                                Card toBeAdded = playCard(players[z], z);
                                System.out.println(playerNames[z] + " plays " + toBeAdded.toString());
                                winnerPile.getCards().add(toBeAdded);
                                warComparison.put(toBeAdded, z);
                                cardWarComparision.add(toBeAdded);

                            }
                        }
                            int winner = getRoundWinner(cardWarComparision);
                            if (winner == -1) {
                                cardsToDraw = cardsToDraw * 2;

                            } else {
                                int winnerOfRound = warComparison.get(cardWarComparision.getCards().get(winner));
                                System.out.print(winnerOfRound);
                                System.out.println(playerNames[winnerOfRound] + " wins the round!");
                                for (int i = winnerPile.getNumberOfCards() - 1; i >= 0; i--) {
                                    winnerPile.give(winnerPile.getCards().get(i), players[winnerOfRound]);
                                }
                                this.setWar(false);
                            }

                    }
                }



        }


        int maxInd = -1;
        int maxVal = -1;
        if(players.length == 2 && players[0].getNumberOfCards() == 26){
            System.out.println("It's a Tie!");
            return;
        }else if(players.length == 3 && players[0].getNumberOfCards() == 17){
            System.out.println("It's a Tie!");
            return;
        }
        for ( int i = 0; i < numberOfPlayers; i++){
            if(players[i].getNumberOfCards() > maxVal){
                maxVal = players[i].getNumberOfCards();
                maxInd = i;
            }
            System.out.println(players[i].getNumberOfCards());
        }

        System.out.println("Winner is " + playerNames[maxInd] + "!");
        System.out.println(players[0].getCards().toString());
        System.out.println(players[1].getCards().toString());
        return;
    }
    public void beginGameNewPile() {
        war = false;
        playerPointsPileHands = new ArrayList<Hand>();
        for(int x = 0; x < players.length; x++){
            playerPointsPileHands.add(new Hand());
        }
        while(players[0].getNumberOfCards() != 0){
            Hand currentRoundHand = new Hand();
            for (int i = 0; i < numberOfPlayers; i++) {
                if (players[i].getNumberOfCards() > 0) {
                    Card toBeAdded = playCard(players[i], i);
                    currentRoundHand.getCards().add(i,toBeAdded);
                    System.out.println(playerNames[i] + " plays " + playedCards.getCards().get(i).toString());
                }else{
                    int winner = getWinner(playerPointsPileHands);
                    System.out.println("Winner is " + playerNames[winner]);
                    return;
                }
        }
            // insert comparison here
            int winnerIndex = getRoundWinner(currentRoundHand);
            if (winnerIndex != -1) {
                System.out.println(playerPointsPileHands.get(winnerIndex).getNumberOfCards());
                giveWinnerToPointsPile(playerPointsPileHands.get(winnerIndex),currentRoundHand);
                System.out.println(playerPointsPileHands.get(winnerIndex).getNumberOfCards());
                System.out.println(playerNames[winnerIndex] + " wins the round!");
            } else {
                System.out.println("*** WAR!!! ***");
                this.setWar(true);
                int cardsToDraw = 2;
                Hand winnerPile = new Hand();
                for(int z = 0; z < currentRoundHand.getNumberOfCards(); z++){
                    Card toBeAdded = currentRoundHand.getCards().get(z);
                    winnerPile.getCards().add(toBeAdded);
                }
                while (war == true) {
                    Hand cardWarComparision = new Hand();
                    int notEnoughCardsCount = 0;
                    for (int z = 0; z < players.length; z++) {
                        if (players[z].getNumberOfCards() < cardsToDraw) {
                            int winner = getWinner(playerPointsPileHands);
                            if(winner == -1) {
                                System.out.println("It's a tie!");
                                return;
                            }else {
                                System.out.println("Winner is" + playerNames[winner] + "!");
                                return;
                            }

                        }else {
                            for (int i = 0; i < cardsToDraw - 1; i++) {
                                Card toBeAdded = playCard(players[z], z);
                                winnerPile.getCards().add(toBeAdded);
                                System.out.println(playerNames[z] + " plays " + toBeAdded.toString());
                            }
                            Card toBeAdded = playCard(players[z], z);
                            System.out.println(playerNames[z] + " plays " + toBeAdded.toString());
                            winnerPile.getCards().add(toBeAdded);
                            cardWarComparision.add(toBeAdded);
                        }
                    }
                    int winner = getRoundWinner(cardWarComparision);
                    if (winner == -1) {
                        cardsToDraw = cardsToDraw * 2;
                    }else {
                        System.out.println(winner);
                        System.out.println(playerNames[winner] + " wins the round!");
                        for (int i = winnerPile.getNumberOfCards() - 1; i >= 0; i--) {
                            winnerPile.give(winnerPile.getCards().get(i),playerPointsPileHands.get(winner));
                        }
                        this.setWar(false);
                    }
        }

                }
            }
        int winner = getWinner(playerPointsPileHands);
        if (winner == -1){
            System.out.println("It's a tie!");
        }else{
            System.out.println("Winner is " + playerNames[winner] + "!");
        }
        return;


    }




    public void giveWinnerToPointsPile(Hand pointsPile, Hand winnerCards){
        for(int i = 0; i < winnerCards.getCards().size(); i++){
            winnerCards.give(winnerCards.getCards().get(i),pointsPile);
        }
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
    public int getWinner(ArrayList<Hand> playerPointsPlies){
        int maxInt = -1;
        int maxVal = -1;
        int countMaxVals = 0;
        for(int x = 0; x < playerPointsPlies.size(); x++){
            if(playerPointsPlies.get(x).getCards().size() >= maxVal ){
                maxVal = playerPointsPlies.get(x).getCards().size();
                maxInt = x;
                countMaxVals++;
            }
        }
        if(countMaxVals == 1){
            return maxInt;
        }
        return -1;
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

    public int getRoundWinner(Hand currentRoundCards) {
        int winner = 0;
        int tieCount = 0;
            for(int j = 1; j < currentRoundCards.getNumberOfCards(); j++){
                if(currentRoundCards.getCards().get(winner).compareTo(currentRoundCards.getCards().get(j)) == 0){
                    tieCount++;
                }
                else if(currentRoundCards.getCards().get(winner).compareTo(currentRoundCards.getCards().get(j)) == -1){
                    winner = j;
                }
            }
            if( currentRoundCards.getNumberOfCards() == 2 && tieCount == 1 ) {
                return -1;
            }else if( currentRoundCards.getNumberOfCards() == 3 && tieCount == 2 ){
                return -1;
            }else{
                return winner;
            }
    }




    public void setPlayedCards() { playedCards = new Hand(); }

    public boolean isWar() { return this.war; }

    public void setWar(boolean war) { this.war = war; }
}