package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    private static Stage guiStage;

    public static Stage getGuiStage() {
        return guiStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        guiStage=primaryStage;

        CreateStudentController ctrl = new CreateStudentController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createStudent.fxml"));
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
