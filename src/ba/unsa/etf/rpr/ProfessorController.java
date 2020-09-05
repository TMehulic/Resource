package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;



public class ProfessorController {


    @FXML
    public ListView<Course> listView;
    @FXML
    public Circle circle;
    @FXML
    public Label labelName;
    @FXML
    public Button btnLogout;
    private DAOClass dao;


    public ProfessorController() {

    }

    @FXML
    public void initialize(){
        dao = DAOClass.getInstance();
        Professor professor = dao.getProfessor(HomeController.userID);

        listView.setItems(FXCollections.observableArrayList(dao.getCoursesFromProfessor(professor.getId())));
        listView.setCellFactory(courseListView -> new CourseListCell());
        Image im = new Image("/images/test.png");
        circle.setFill(new ImagePattern(im));
        labelName.setText("Welcome, "+ professor.getLastName()+" "+professor.getFirstName());

        btnLogout.setOnAction(logoutAction);

    }

    private EventHandler<ActionEvent> logoutAction = actionEvent -> Main.returnHome();
}
