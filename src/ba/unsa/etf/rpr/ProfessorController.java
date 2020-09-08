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
        listView.setOnMouseClicked(courseClicked);
        Image im = new Image("/images/test.png");
        circle.setFill(new ImagePattern(im));
        labelName.setText("Welcome, "+ professor.getLastName()+" "+professor.getFirstName());

        btnLogout.setOnAction(logoutAction);

    }

    private EventHandler<? super MouseEvent> courseClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            CourseController ctrl = new CourseController(listView.getSelectionModel().getSelectedItem().getId());
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/course.fxml"));
            loader.setController(ctrl);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getGuiStage().setTitle("Course");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        }
    };

    private EventHandler<ActionEvent> logoutAction = actionEvent -> Main.returnHome();
}
