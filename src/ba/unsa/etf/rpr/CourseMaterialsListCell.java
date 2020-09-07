package ba.unsa.etf.rpr;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CourseMaterialsListCell extends ListCell<CourseMaterial> {

    public HBox hbox;
    private FXMLLoader mLLoader;
    public Label materialName;



    public CourseMaterialsListCell() {
        {
            setStyle("-fx-padding: 0 0 0.5 0 ");
        }
    }

    @Override
    protected void updateItem(CourseMaterial material, boolean b) {
        super.updateItem(material, b);
        if(b || material == null) {

            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/courseMaterialsLV.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            materialName.setWrapText(true);
            materialName.setText(material.getTitle());

            setText(null);
            setGraphic(hbox);
        }
    }
}
