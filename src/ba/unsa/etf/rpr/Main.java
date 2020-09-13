package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    private static Stage guiStage;
    public static ResourceBundle bundle;

    public static Stage getGuiStage() {
        return guiStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        bundle=ResourceBundle.getBundle("Translation");
        guiStage=primaryStage;
        returnHome();
    }

    public static void returnHome(){
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/login.fxml"), bundle);
        HomeController homeCtrl = new HomeController();
        loader.setController(homeCtrl);
        try {
            Parent root  = loader.load();
            Main.getGuiStage().setTitle("Login");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().centerOnScreen();
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void load(FXMLLoader loader){
        try {
            Parent root = loader.load();
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().centerOnScreen();
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
