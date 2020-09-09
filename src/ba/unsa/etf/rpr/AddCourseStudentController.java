package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
                dao.addStudentToCourse(studentId,courseId);
            }
        }
    };

}
