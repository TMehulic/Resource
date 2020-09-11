package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddCourseStudentController {

    private int courseId;
    private DAOClass dao;

    public TableView<Student> tableViewStudents;
    public TableColumn<Student,Integer> colIndex;
    public TableColumn<Student,String> colFirstName;
    public TableColumn<Student,String> colLastName;
    public TableColumn<Student,String> colDegree;
    public Button btnAddStudent;

    public AddCourseStudentController(int courseId) {
        this.courseId = courseId;
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

    }

    private EventHandler<ActionEvent> addStudent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewStudents.getSelectionModel().isEmpty()){
                int studentId = tableViewStudents.getSelectionModel().getSelectedItem().getId();
                System.out.println(studentId);
                dao.addStudentToCourse(studentId,courseId);
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
        }
    };

}
