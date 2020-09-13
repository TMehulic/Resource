package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.*;
import ba.unsa.etf.rpr.models.Course;
import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.models.Professor;
import ba.unsa.etf.rpr.utilities.CourseProfessorListCell;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ListCourseProfessorsController {

    private DAOClass dao;
    private int courseId;


    public ListView<Professor> listViewProfessors;
    public Button btnRemoveProfessor;
    public Button btnAddProfessor;
    public Button btnDashboard;

    public Label labelName;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;

    public ListCourseProfessorsController(int courseId) {
        this.courseId = courseId;
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){

        dao=DAOClass.getInstance();

        btnAddProfessor.setOnAction(addProfessor);
        btnRemoveProfessor.setOnAction(removeProfessor);
        btnDashboard.setOnAction(returnToCourses);

        Course course = dao.getCourse(courseId);
        labelName.setText(course.getName());

        listViewProfessors.setItems(FXCollections.observableArrayList(dao.getProfessorsOnCourse(courseId)));
        listViewProfessors.setCellFactory(courseProfessorsListView -> new CourseProfessorListCell());
        setMenuListeners();
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/courseProfessorsList.fxml"),Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EventHandler<ActionEvent> addProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AddCourseProfessorController ctrl = new AddCourseProfessorController(courseId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/addCourseProfessor.fxml"),Main.bundle);
            loader.setController(ctrl);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Main.getGuiStage().setTitle("Add professor");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().centerOnScreen();
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(false);
        }
    };

    private EventHandler<ActionEvent> removeProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!listViewProfessors.getSelectionModel().isEmpty()){
                Professor professor = listViewProfessors.getSelectionModel().getSelectedItem();
                dao.removeProfessorFromCourse(listViewProfessors.getSelectionModel().getSelectedItem(),courseId);
                listViewProfessors.getItems().remove(professor);
                listViewProfessors.getSelectionModel().selectFirst();
            }
        }
    };

    private EventHandler<ActionEvent> returnToCourses = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            ListCoursesController ctrl = new ListCoursesController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/coursesList.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Courses");
            Main.load(loader);
        }
    };


}
