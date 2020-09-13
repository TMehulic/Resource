package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class StudentController {

    public ListView<Course> listView;
    public Label labelDegree;
    public Label labelYearOfStudy;
    public Label labelWelcome;

    public Label labelIndex;
    public Label labelTrivia;

    public Circle circle;
    private DAOClass dao;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;


    private TriviaAPI triviaAPI;

    public StudentController() {
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){

        labelTrivia.setWrapText(true);
        triviaAPI=new TriviaAPI(labelTrivia);


        dao = DAOClass.getInstance();
        Student student = dao.getStudent(HomeController.userID);

        listView.setItems(FXCollections.observableArrayList(dao.getCoursesFromStudent(student.getId())));
        listView.setCellFactory(courseListView -> new CourseListCell());
        listView.setOnMouseClicked(courseClicked);
        Image im = new Image("/images/test.png");
        circle.setFill(new ImagePattern(im));

        labelDegree.setText(student.getEducationInfo().getDegree());
        labelYearOfStudy.setText(String.valueOf(student.getEducationInfo().getYear()));
        labelIndex.setText(String.valueOf(student.getEducationInfo().getIndex()));
        labelWelcome.setText(labelWelcome.getText()+" "+student.getLastName()+" "+student.getFirstName());

        setMenuListeners();
        setTrivia();

    }

    public void setTrivia(){
        triviaAPI.start();
    }

    public void setMenuListeners(){
        itemBosnian.setOnAction(actionEvent -> {
            menuClass.setBosnian();
            restart();
        });

        itemEnglish.setOnAction(actionEvent -> {
            menuClass.setEnglish();
            restart();
        });

        itemLogout.setOnAction(actionEvent -> menuClass.logOut());

        itemAbout.setOnAction(actionEvent -> menuClass.showAbout());
    }

    public void restart(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/student.fxml"),Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EventHandler<? super MouseEvent> courseClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            CourseController ctrl = new CourseController(listView.getSelectionModel().getSelectedItem().getId());
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/course.fxml"),Main.bundle);
            loader.setController(ctrl);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getGuiStage().setTitle("Course");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().centerOnScreen();
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        }
    };


}
