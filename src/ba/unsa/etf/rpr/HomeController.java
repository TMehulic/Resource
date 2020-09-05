package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    static int userID;

    private FXMLLoader loader;
    private Parent root;

    public TextField fldEmail;
    public PasswordField fldPassword;
    public Button btnLogin;
    public Label exceptionLabel;


    public HomeController() {
        userID=-1;
    }

    @FXML
    public void initialize(){

        exceptionLabel.setText("");
        btnLogin.setOnAction(loginAction);

    }

    public EventHandler<ActionEvent> loginAction = actionEvent -> {

        Person user = DAOClass.getInstance().getUser(fldEmail.textProperty().getValue(),fldPassword.textProperty().getValue());
        if(user==null){
            try {
                throw new InvalidLoginException("Pogrešni pristupni podaci.");
            } catch (InvalidLoginException e) {
                exceptionLabel.setText(e.getMessage());
            }
        }else{
            userID=user.getId();
            if(user instanceof Student){
                StudentController ctrl = new StudentController();
                loader=new FXMLLoader(getClass().getResource("/fxml/student.fxml"));
                loader.setController(ctrl);
                Main.getGuiStage().setTitle("Student");
                redirectToDashboard();
            }else if(user instanceof Professor){
                ProfessorController ctrl = new ProfessorController();
                loader=new FXMLLoader(getClass().getResource("/fxml/professor.fxml"));
                loader.setController(ctrl);
                Main.getGuiStage().setTitle("Professor");
                redirectToDashboard();
            }else {
                try {
                    throw new InvalidLoginException("Pogrešni pristupni podaci.");
                } catch (InvalidLoginException e) {
                    userID=-1;
                    exceptionLabel.setText(e.getMessage());
                }
            }
        }
    };

    public void redirectToDashboard(){
        try {
            root = loader.load();
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
