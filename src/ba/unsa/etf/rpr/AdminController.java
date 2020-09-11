package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminController {

    private FXMLLoader loader;

    public Button btnAddStudent;
    public Button btnAddProfessor;
    public Button btnAddCourse;
    public Button btnStudents;
    public Button btnProfessors;
    public Button btnCourses;

    @FXML
    public void initialize(){
        btnAddStudent.setOnAction(addStudent);
        btnAddProfessor.setOnAction(addProfessor);
        btnAddCourse.setOnAction(addCourse);
        btnStudents.setOnAction(viewStudents);
        btnProfessors.setOnAction(viewProfessors);
        btnCourses.setOnAction(viewCourses);
    }

    private EventHandler<ActionEvent> addStudent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            CreateStudentController ctrl = new CreateStudentController();
            loader=new FXMLLoader(getClass().getResource("/fxml/createStudent.fxml"));
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Create student");
            try {
                Parent root = loader.load();
                Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                Main.getGuiStage().show();
                Main.getGuiStage().setResizable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> addProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            CreateProfessorController ctrl = new CreateProfessorController();
            loader = new FXMLLoader(getClass().getResource("/fxml/createProfessor.fxml"));
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Create professor");
            try {
                Parent root = loader.load();
                Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                Main.getGuiStage().show();
                Main.getGuiStage().setResizable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> addCourse = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            CreateCourseController ctrl = new CreateCourseController();
            loader = new FXMLLoader(getClass().getResource("/fxml/createCourse.fxml"));
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Create course");
            try {
                Parent root = loader.load();
                Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                Main.getGuiStage().show();
                Main.getGuiStage().setResizable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> viewStudents = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            ListStudentsController ctrl = new ListStudentsController();
            loader = new FXMLLoader(getClass().getResource("/fxml/studentsList.fxml"));
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Students");
            try {
                Parent root = loader.load();
                Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                Main.getGuiStage().show();
                Main.getGuiStage().setResizable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> viewProfessors = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            ListProfessorsController ctrl = new ListProfessorsController();
            loader = new FXMLLoader(getClass().getResource("/fxml/professorsList.fxml"));
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Professors");
            try {
                Parent root = loader.load();
                Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                Main.getGuiStage().show();
                Main.getGuiStage().setResizable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> viewCourses = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

        }
    };




}
