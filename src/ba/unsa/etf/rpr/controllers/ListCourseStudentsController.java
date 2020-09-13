package ba.unsa.etf.rpr.controllers;


import ba.unsa.etf.rpr.*;
import ba.unsa.etf.rpr.models.Course;
import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.models.EducationInfo;
import ba.unsa.etf.rpr.models.Student;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ListCourseStudentsController {

    private DAOClass dao;
    private int courseId;

    public TableView<Student> tableViewStudents;
    public TableColumn<EducationInfo,Integer> colIndex;
    public TableColumn<Student,String> colName;
    public TableColumn<Student,String> colLastName;
    public Label labelName;
    public Button btnAddStudent;
    public Button btnRemoveStudent;
    public Button btnDashboard;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;



    public ListCourseStudentsController(int courseId) {
        this.courseId=courseId;
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){
        dao=DAOClass.getInstance();
        colIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewStudents.setItems(dao.getStudentsOnCourse(courseId));

        Course course = dao.getCourse(courseId);
        labelName.setText(course.name);

        btnAddStudent.setOnAction(addStudent);
        btnRemoveStudent.setOnAction(removeStudent);
        btnDashboard.setOnAction(returnToCourse);

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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/courseStudentsList.fxml"), Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EventHandler<ActionEvent> addStudent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AddCourseStudentController ctrl = new AddCourseStudentController(courseId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/addCourseStudent.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Add student");
            Main.load(loader);
        }
    };

    private EventHandler<ActionEvent> removeStudent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewStudents.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ispiši studenta");
                alert.setHeaderText(null);
                alert.setContentText("Molimo potvrdite vaš izbor.");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                    Student removed=tableViewStudents.getSelectionModel().getSelectedItem();
                    dao.removeStudentFromCourse(removed,courseId);
                    tableViewStudents.getItems().remove(removed);
                    tableViewStudents.getSelectionModel().selectFirst();
                }
                alert.close();
            }
        }
    };

    private EventHandler<ActionEvent> returnToCourse = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            CourseController ctrl = new CourseController(courseId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/course.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Course");
            Main.load(loader);
        }
    };



}
