package main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import game.War;

public class TableController {
    @FXML
    private TextArea gameOutput;

    @FXML
    private Button playWar;

    private War war;

    public TableController() {
    }

    @FXML
    void playWar(ActionEvent event) {
        int numberOfCards = 20;
        int numberOfPlayers = 2;
        int cardsPerPlayer = numberOfCards / numberOfPlayers;
        war = new War(numberOfPlayers, cardsPerPlayer);
        war.beginGame();
    }

    @FXML
    public void initialize() {

    }
}