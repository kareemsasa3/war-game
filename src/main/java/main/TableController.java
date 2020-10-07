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
        war = new War();
        // variation 1
        //war.beginGameSamePile();
        // variation 2
        war.beginGameNewPile();
    }

    @FXML
    public void initialize() {

    }
}
