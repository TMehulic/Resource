package ba.unsa.etf.rpr.utilities;

import ba.unsa.etf.rpr.models.CourseNews;
import com.sun.prism.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CourseNewsListCell extends ListCell<CourseNews> {

    private FXMLLoader mLLoader;

    public Label courseNewsData;
    public HBox hbox;
    public Image materialImage;

    public CourseNewsListCell() {
        {
            setStyle("-fx-padding: 0 0 0.5 0 ");
        }
    }

    @Override
    protected void updateItem(CourseNews courseNews, boolean b) {
        super.updateItem(courseNews, b);
        if(b || courseNews == null) {

            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/courseNewsLV.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            courseNewsData.setWrapText(true);
            courseNewsData.setText(String.valueOf(courseNews));

            setText(null);
            setGraphic(hbox);
        }
    }
}
