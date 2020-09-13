package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.*;
import ba.unsa.etf.rpr.models.Course;
import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.models.Professor;
import ba.unsa.etf.rpr.utilities.CourseListCell;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import ba.unsa.etf.rpr.utilities.TriviaAPI;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
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
    private DAOClass dao;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;

    public Label labelTrivia;
    private TriviaAPI triviaAPI;

    public ProfessorController() {
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){

        labelTrivia.setWrapText(true);
        triviaAPI=new TriviaAPI(labelTrivia);

        dao = DAOClass.getInstance();
        Professor professor = dao.getProfessor(HomeController.userID);

        listView.setItems(FXCollections.observableArrayList(dao.getCoursesFromProfessor(professor.getId())));
        listView.setCellFactory(courseListView -> new CourseListCell());
        listView.setOnMouseClicked(courseClicked);
        Image im = new Image(professor.getImage());
        circle.setFill(new ImagePattern(im));
        labelName.setText(labelName.getText()+" "+ professor.getLastName()+" "+professor.getFirstName());

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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/professor.fxml"),Main.bundle);
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
            Main.getGuiStage().setTitle("Course");
            Main.load(loader);
        }
    };


}
