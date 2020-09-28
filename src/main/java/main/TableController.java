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


    public TableController() {
    }

    @FXML
    void playWar(ActionEvent event) {
        War war = new War();
        war.beginGame();
    }

    @FXML
    public void initialize() {

    }
}