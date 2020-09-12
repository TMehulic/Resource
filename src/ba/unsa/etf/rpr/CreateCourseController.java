package ba.unsa.etf.rpr;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateCourseController implements IValidateInputs {

    public TextField fldCourseName;
    public TextField fldEcts;
    public TextArea fldDesc;

    public Button btnConfirm;
    public Button btnCancel;

    public Label errorLabel;


    public CreateCourseController() {
    }

    @FXML
    public void initialize(){
        btnConfirm.setOnAction(addCourse);
        btnCancel.setOnAction(cancelAction);
        fldDesc.setWrapText(true);
        setListeners();
    }

    public void setListeners(){
        fldCourseName.textProperty().addListener((obs,oldValue,newValue)->{
            if(isCorrectName(newValue)){
                fldCourseName.getStyleClass().removeAll("invalid");
                fldCourseName.getStyleClass().add("valid");
            }else{
                fldCourseName.getStyleClass().removeAll("valid");
                fldCourseName.getStyleClass().add("invalid");
            }
        });

        fldEcts.textProperty().addListener((obs,oldValue,newValue)->{
            if(isCorrectEcts(newValue)){
                fldEcts.getStyleClass().removeAll("invalid");
                fldEcts.getStyleClass().add("valid");
            }else{
                fldEcts.getStyleClass().removeAll("valid");
                fldEcts.getStyleClass().add("invalid");
            }
        });

        fldDesc.textProperty().addListener((obs,oldValue,newValue)->{
            if(newValue.length()>0){
                fldDesc.getStyleClass().removeAll("invalid");
                fldDesc.getStyleClass().add("valid");
            }else{
                fldDesc.getStyleClass().removeAll("valid");
                fldDesc.getStyleClass().add("invalid");
            }
        });
    }

    private boolean isCorrectName(String newValue) {
        return newValue.length() >= 3;
    }

    private boolean isCorrectEcts(String ects){
        try{
            int value = Integer.parseInt(ects);
            return value>0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private EventHandler<ActionEvent> addCourse = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try{
                checkInputs();
                String name = fldCourseName.getText();
                String desc = fldDesc.getText();
                int ects = Integer.parseInt(fldEcts.getText());
                Course course = new Course(name,desc,ects);
                DAOClass.getInstance().createCourse(course);
                AdminController.returnToDashboard();
            }catch (Exception e){
                errorLabel.setText(e.getMessage());
            }
        }
    };

    public void checkInputs() throws InvalidInputException{
        if(!isCorrectName(fldCourseName.getText())) throw new InvalidInputException("Naziv kursa mora imati barem 3 znaka.");
        if(!isCorrectEcts(fldEcts.getText())) throw new InvalidInputException("ECTS mora biti pozitivan broj.");
    }

    private EventHandler<ActionEvent> cancelAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AdminController.returnToDashboard();
        }
    };

}
