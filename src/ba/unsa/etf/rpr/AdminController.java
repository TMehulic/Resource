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

    @FXML
    public void initialize(){
        btnAddStudent.setOnAction(addStudent);
        btnAddProfessor.setOnAction(addProfessor);
        btnAddCourse.setOnAction(addCourse);
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
                Main.getGuiStage().setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    private EventHandler<ActionEvent> addProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

        }
    };

    private EventHandler<ActionEvent> addCourse = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

        }
    };



}
