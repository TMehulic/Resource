package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CourseListCell extends javafx.scene.control.ListCell<Course> {

    @FXML
    private Label imePredmeta;
    @FXML
    private Label opisPredmeta;
    @FXML
    private VBox vbox;

    private FXMLLoader mLLoader;

    public CourseListCell() {
        {
            setStyle("-fx-padding: 0 0 0.5 0 ");
        }

    }

    @Override
    protected void updateItem(Course course, boolean b) {
        super.updateItem(course, b);

        if(b || course == null) {

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

            imePredmeta.setText(course.getName());
            opisPredmeta.setText(course.getDescription());

            setText(null);
            setGraphic(vbox);
        }

    }
}
