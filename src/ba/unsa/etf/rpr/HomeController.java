package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomeController {

    public TextField fldUsername;
    public PasswordField fldPassword;
    public Button btnLogin;


    public HomeController() { }

    @FXML
    public void initialize(){
        fldUsername.textProperty().addListener((obs,oldName,newName) ->{

        });

        fldPassword.textProperty().addListener((obs, oldPass, newPass) -> {

        });

        btnLogin.setOnAction(loginAction);

    }

    public EventHandler<ActionEvent> loginAction = actionEvent -> {
        //Check inputs
    };


}
