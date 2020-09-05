package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    private static Stage guiStage;

    public static Stage getGuiStage() {
        return guiStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        guiStage=primaryStage;
        returnHome();
    }

    public static void returnHome(){
        HomeController homeCtrl = new HomeController();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/login.fxml"));
        loader.setController(homeCtrl);
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.getGuiStage().setTitle("Login");
        Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        Main.getGuiStage().show();
        Main.getGuiStage().setResizable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
