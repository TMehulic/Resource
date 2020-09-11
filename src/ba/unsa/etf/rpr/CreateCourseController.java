package ba.unsa.etf.rpr;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateCourseController {

    public TextField fldCourseName;
    public TextField fldEcts;
    public TextArea fldDesc;

    public Button btnConfirm;
    public Button btnCancel;


    public CreateCourseController() {
    }

    @FXML
    public void initialize(){
        btnConfirm.setOnAction(addCourse);
        btnCancel.setOnAction(cancelAction);
        fldDesc.setWrapText(true);
    }

    private EventHandler<ActionEvent> addCourse = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            //todo validate inputs
            String name = fldCourseName.getText();
            String desc = fldDesc.getText();
            int ects = Integer.parseInt(fldEcts.getText());
            Course course = new Course(name,desc,ects);
            DAOClass.getInstance().createCourse(course);
        }
    };

    private EventHandler<ActionEvent> cancelAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AdminController.returnToDashboard();
        }
    };

}
