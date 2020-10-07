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

    private String output;

    public TableController() {
    }

    @FXML
    void playWar(ActionEvent event) {
        war = new War();
        // variation 1
        output = war.beginGameSamePile();
        // variation 2
        //output = war.beginGameNewPile();
        gameOutput.appendText(output);
    }

    @FXML
    public void initialize() {

    }
}
