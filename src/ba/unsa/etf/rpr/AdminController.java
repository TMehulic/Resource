package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminController {

    private static FXMLLoader loader;

    public Button btnAddStudent;
    public Button btnAddProfessor;
    public Button btnAddCourse;
    public Button btnStudents;
    public Button btnProfessors;
    public Button btnCourses;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;

    public AdminController(){
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){
        btnAddStudent.setOnAction(addStudent);
        btnAddProfessor.setOnAction(addProfessor);
        btnAddCourse.setOnAction(addCourse);
        btnStudents.setOnAction(viewStudents);
        btnProfessors.setOnAction(viewProfessors);
        btnCourses.setOnAction(viewCourses);

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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/admin.fxml"),Main.bundle);
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
            CreateStudentController ctrl = new CreateStudentController();
            loader=new FXMLLoader(getClass().getResource("/fxml/createStudent.fxml"),Main.bundle);
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
            loader = new FXMLLoader(getClass().getResource("/fxml/createProfessor.fxml"),Main.bundle);
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
            loader = new FXMLLoader(getClass().getResource("/fxml/createCourse.fxml"),Main.bundle);
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
            loader = new FXMLLoader(getClass().getResource("/fxml/studentsList.fxml"),Main.bundle);
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
            loader = new FXMLLoader(getClass().getResource("/fxml/professorsList.fxml"),Main.bundle);
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
            ListCoursesController ctrl = new ListCoursesController();
            loader = new FXMLLoader(getClass().getResource("/fxml/coursesList.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Courses");
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

    public static void returnToDashboard(){
        AdminController ctrl = new AdminController();
        loader=new FXMLLoader(AdminController.class.getResource("/fxml/admin.fxml"),Main.bundle);
        loader.setController(ctrl);
        Main.getGuiStage().setTitle("Admin");
        try {
            Parent root = loader.load();
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
