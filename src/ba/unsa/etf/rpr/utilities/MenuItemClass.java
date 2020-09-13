package ba.unsa.etf.rpr.utilities;

import ba.unsa.etf.rpr.Main;
import ba.unsa.etf.rpr.controllers.AboutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MenuItemClass {

    public void setEnglish(){
        Locale.setDefault(new Locale("en_US"));
        Main.bundle= ResourceBundle.getBundle("Translation");
    }

    public void setBosnian(){
        Locale.setDefault(new Locale("bs"));
        Main.bundle= ResourceBundle.getBundle("Translation");
    }

    public void showAbout(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/about.fxml"),Main.bundle);
        AboutController ctrl = new AboutController();
        loader.setController(ctrl);
        Parent root = null;
        try {
            root = loader.load();
            Stage stage=new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut(){
        Main.returnHome();
    }

}
