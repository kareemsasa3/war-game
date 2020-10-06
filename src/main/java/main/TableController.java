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
        //Game variation 1
        war.beginGameSamePile();
        //Game variation 2
        //war.beginGameNewPile();
    }

    @FXML
    public void initialize() {

    }
}