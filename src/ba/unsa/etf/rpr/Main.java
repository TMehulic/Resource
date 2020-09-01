package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        StudentController ctrl = new StudentController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student.fxml"));
        loader.setController(ctrl);

        Parent root = loader.load();

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
        primaryStage.setResizable(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
