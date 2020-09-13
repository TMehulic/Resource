package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AboutController {

    public Button btnClose;

    public AboutController() {

    }

    @FXML
    public void initialize(){
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) btnClose.getScene().getWindow();
                stage.close();
            }
        });
    }
}
