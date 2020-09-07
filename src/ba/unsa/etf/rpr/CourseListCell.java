package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CourseListCell extends javafx.scene.control.ListCell<Course> {

    @FXML
    private Label courseName;
    @FXML
    private Label courseDesc;
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
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/courseLV.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            courseName.setText(course.getName());
            courseDesc.setWrapText(true);
            courseDesc.setText(course.getDescription());

            setText(null);
            setGraphic(vbox);
        }

    }
}
