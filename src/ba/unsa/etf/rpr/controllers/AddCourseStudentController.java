package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.Main;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import ba.unsa.etf.rpr.models.Student;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddCourseStudentController {

    private int courseId;
    private DAOClass dao;
    private FXMLLoader loader;

    public TableView<Student> tableViewStudents;
    public TableColumn<Student,Integer> colIndex;
    public TableColumn<Student,String> colFirstName;
    public TableColumn<Student,String> colLastName;
    public TableColumn<Student,String> colDegree;
    public Button btnAddStudent;
    public Button btnCancel;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;

    public AddCourseStudentController(int courseId) {
        this.courseId = courseId;
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){
        dao=DAOClass.getInstance();
        colIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDegree.setCellValueFactory(new PropertyValueFactory<>("degree"));
        tableViewStudents.setItems(dao.getStudentsNotOnCourse(courseId));

        btnAddStudent.setOnAction(addStudent);
        btnCancel.setOnAction(backToList);

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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/addCourseStudent.fxml"), Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EventHandler<ActionEvent> backToList = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            ListCourseStudentsController ctrl = new ListCourseStudentsController(courseId);
            loader=new FXMLLoader(getClass().getResource("/fxml/courseStudentsList.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Students");
            Main.load(loader);
        }
    };

    private EventHandler<ActionEvent> addStudent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewStudents.getSelectionModel().isEmpty()){
                int studentId = tableViewStudents.getSelectionModel().getSelectedItem().getId();
                dao.addStudentToCourse(studentId,courseId);
                btnCancel.fire();
            }
        }
    };

}
