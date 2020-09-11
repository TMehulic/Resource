package ba.unsa.etf.rpr;

import com.sun.prism.Image;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CourseProfessorListCell extends ListCell<Professor> {

    private FXMLLoader mLLoader;

    public Label labelProfessor;
    public HBox hbox;

    public CourseProfessorListCell() {
        {
            setStyle("-fx-padding: 0 0 0.5 0 ");
        }
    }

    @Override
    protected void updateItem(Professor professor, boolean b) {
        super.updateItem(professor, b);
        if(b || professor == null) {

            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/courseProfessorsLV.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            labelProfessor.setText(professor.getTitle()+" " +professor.getLastName()+" "+professor.getFirstName());

            setText(null);
            setGraphic(hbox);
        }
    }

}
