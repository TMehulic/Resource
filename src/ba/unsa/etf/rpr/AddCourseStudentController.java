package ba.unsa.etf.rpr;

import com.mysql.cj.xdevapi.Table;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddCourseStudentController {

    private int courseId;

    public TableView<Student> tableViewStudents;
    public TableColumn<Student,Integer> colIndex;
    public TableColumn<Student,String> colFirstName;
    public TableColumn<Student,String> colLastName;
    public TableColumn<Student,String> colDegree;

    public AddCourseStudentController(int courseId) {
        this.courseId = courseId;
    }

    @FXML
    public void initialize(){

    }
}
