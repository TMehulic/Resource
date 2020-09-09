package ba.unsa.etf.rpr;


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

public class StudentsListController {

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



    public StudentsListController(int courseId) {
        this.courseId=courseId;
    }

    @FXML
    public void initialize(){
        dao=DAOClass.getInstance();
        colIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewStudents.setItems(dao.getStudentsOnCourse(courseId));


        btnAddStudent.setOnAction(addStudent);
        btnRemoveStudent.setOnAction(removeStudent);
        btnDashboard.setOnAction(returnToCourse);
    }

    private EventHandler<ActionEvent> addStudent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AddCourseStudentController ctrl = new AddCourseStudentController(courseId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/addCourseStudent.fxml"));
            loader.setController(ctrl);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getGuiStage().setTitle("Add student");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
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



}
