package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TestnaListCell extends javafx.scene.control.ListCell<Testna> {

    @FXML
    private Label imePredmeta;
    @FXML
    private Label opisPredmeta;
    @FXML
    private VBox vbox;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Testna testna, boolean b) {
        super.updateItem(testna, b);

        if(b || testna == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/listView.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            imePredmeta.setText(testna.getImePredmeta());
            opisPredmeta.setText(testna.getOpisPredmeta());

            setText(null);
            setGraphic(vbox);
        }

    }
}
