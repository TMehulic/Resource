package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {

    static int userID;

    private FXMLLoader loader;
    private Parent root;

    public TextField fldEmail;
    public PasswordField fldPassword;
    private SimpleStringProperty password;
    private SimpleStringProperty email;
    public Button btnLogin;
    public Label exceptionLabel;

    public MenuItemClass menuClass;
//    public MenuItem itemAbout;
//    public MenuItem itemBosnian;
//    public MenuItem itemEnglish;
//    public MenuItem itemLogout;


    public HomeController() {
        userID=-1;
        menuClass=new MenuItemClass();
        password=new SimpleStringProperty("");
        email=new SimpleStringProperty("");
    }

    @FXML
    public void initialize(){

        fldPassword.textProperty().bindBidirectional(password);
        fldEmail.textProperty().bindBidirectional(email);
        exceptionLabel.setText("");
        btnLogin.setOnAction(loginAction);
//        setMenuListeners();

    }

//    public void setMenuListeners(){
//        itemBosnian.setOnAction(actionEvent -> {
//            menuClass.setBosnian();
//            restart();
//        });
//
//        itemEnglish.setOnAction(actionEvent -> {
//            menuClass.setEnglish();
//            restart();
//        });
//
//        itemLogout.setOnAction(actionEvent -> menuClass.logOut());
//
//        itemAbout.setOnAction(actionEvent -> menuClass.showAbout());
//    }
//
//    public void restart(){
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/login.fxml"),Main.bundle);
//        loader.setController(this);
//        try {
//            Main.getGuiStage().setScene(new Scene(loader.load()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public EventHandler<ActionEvent> loginAction = actionEvent -> {

        if(email.get().equals("admin") && password.get().equals("admin")){
            AdminController ctrl = new AdminController();
            loader=new FXMLLoader(getClass().getResource("/fxml/admin.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Admin");
            redirectToDashboard();
        }

        Person user = DAOClass.getInstance().getUser(email.get(),password.get());
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
                loader=new FXMLLoader(getClass().getResource("/fxml/student.fxml"),Main.bundle);
                loader.setController(ctrl);
                Main.getGuiStage().setTitle("Student");
                redirectToDashboard();
            }else if(user instanceof Professor){
                ProfessorController ctrl = new ProfessorController();
                loader=new FXMLLoader(getClass().getResource("/fxml/professor.fxml"),Main.bundle);
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
            Main.getGuiStage().centerOnScreen();
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
