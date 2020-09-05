package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class StudentController {

    @FXML
    public ListView<Course> listView;
    @FXML
    public Label labelDegree;
    @FXML
    public Label labelYearOfStudy;
    @FXML
    public Label labelWelcome;
    @FXML
    public Button btnLogout;

    @FXML
    public Circle circle;
    private DAOClass dao;


    public StudentController() {

    }

    @FXML
    public void initialize(){

        dao = DAOClass.getInstance();
        Student student = dao.getStudent(HomeController.userID);

        listView.setItems(FXCollections.observableArrayList(dao.getCoursesFromStudent(student.getId())));
        listView.setCellFactory(courseListView -> new CourseListCell());
        listView.setOnMouseClicked(courseClicked);
        Image im = new Image("/images/test.png");
        circle.setFill(new ImagePattern(im));

        btnLogout.setOnAction(logoutAction);
        labelDegree.setText(student.getEducationInfo().getDegree());
        labelYearOfStudy.setText(String.valueOf(student.getEducationInfo().getYear()));
        labelWelcome.setText("Welcome, "+student.getLastName()+" "+student.getFirstName());

    }

    private EventHandler<? super MouseEvent> courseClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // todo : otvoriti novi panel sa course stuffom
        }
    };

    private EventHandler<ActionEvent> logoutAction = actionEvent -> Main.returnHome();

}
