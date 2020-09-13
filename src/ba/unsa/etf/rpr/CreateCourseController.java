package ba.unsa.etf.rpr;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

public class CreateCourseController implements IValidateInputs {

    public TextField fldCourseName;
    public TextField fldEcts;
    public TextArea fldDesc;

    public Button btnConfirm;
    public Button btnCancel;

    public Label errorLabel;

    private InputValidator validator;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;


    public CreateCourseController() {
        validator=new InputValidator();
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){
        btnConfirm.setOnAction(addCourse);
        btnCancel.setOnAction(cancelAction);
        fldDesc.setWrapText(true);
        setMenuListeners();
        setListeners();
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/createCourse.fxml"),Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setListeners(){
        fldCourseName.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectName(newValue)){
                fldCourseName.getStyleClass().removeAll("invalid");
                fldCourseName.getStyleClass().add("valid");
            }else{
                fldCourseName.getStyleClass().removeAll("valid");
                fldCourseName.getStyleClass().add("invalid");
            }
        });

        fldEcts.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectEcts(newValue)){
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
        if(!validator.isCorrectName(fldCourseName.getText())) throw new InvalidInputException("Naziv kursa mora imati barem 3 znaka.");
        if(!validator.isCorrectEcts(fldEcts.getText())) throw new InvalidInputException("ECTS mora biti pozitivan broj.");
    }

    private EventHandler<ActionEvent> cancelAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AdminController.returnToDashboard();
        }
    };

}
