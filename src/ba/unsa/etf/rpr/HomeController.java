package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.Node.getClassCssMetaData;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    static int userID;

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
        // 1. Check inputs

        // 2. Get person

        // 3. Redirect to dashboard

        // https://stackoverflow.com/questions/14370183/passing-parameters-to-a-controller-when-loading-an-fxml

        //Ako je student

        //poslati student id

//        StudentController ctrl = new StudentController();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student.fxml"));
//        loader.setController(ctrl);
//
//        Parent root = null;
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //Ako je admin

//        AdminController ctrl = new AdminController();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
//        loader.setController(ctrl);
//
//        Parent root = null;
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        //Ako je profesor
//        ProfessorController ctrl = new ProfessorController();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/professor.fxml"));
//        loader.setController(ctrl);
//
//        Parent root = null;
//        try {
//            root = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(fldUsername.textProperty().getValue());
        System.out.println(fldPassword.textProperty().getValue());
        if(fldUsername.textProperty().getValue().equals("Adis") && fldPassword.textProperty().getValue().equals("123")){
            StudentController studentController= new StudentController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student.fxml"));

            loader.setController(studentController);

            Parent root = null;
            try {
                root=loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getGuiStage().setTitle("Student");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        }
    };

}
