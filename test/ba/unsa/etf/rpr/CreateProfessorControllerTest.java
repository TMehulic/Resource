package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

import ba.unsa.etf.rpr.controllers.CreateProfessorController;
import ba.unsa.etf.rpr.utilities.InvalidInputException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ResourceBundle;

@ExtendWith(ApplicationExtension.class)
class CreateProfessorControllerTest {

    CreateProfessorController ctrl;

    public static boolean sadrziStil(TextField polje, String stil) {
        for (String s : polje.getStyleClass())
            if (s.equals(stil)) return true;
        return false;
    }

    @Start
    public void start (Stage stage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createProfessor.fxml"),resourceBundle);
        ctrl = new CreateProfessorController();
        loader.setController(ctrl);
        Parent mainNode = loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void checkInputs(FxRobot robot){
        assertThrows(InvalidInputException.class, ()->ctrl.checkInputs());
        robot.clickOn("#fldLastName");
        robot.write("Prezime");
        robot.clickOn("#fldFathersName");
        robot.write("Otac");
        robot.clickOn("#fldName");
        robot.write("Ime");
        robot.clickOn("#fldBirthPlace");
        robot.write("Travnik");
        assertThrows(InvalidInputException.class, ()->ctrl.checkInputs());
    }

    @Test
    void validacija(FxRobot robot) {

        TextField polje = robot.lookup("#fldLastName").queryAs(TextField.class);
        robot.clickOn("#fldLastName").write("a");
        assertTrue(sadrziStil(polje, "invalid"));
        robot.clickOn("#fldLastName");
        robot.clickOn("#fldLastName").write("aaaa");
        assertTrue(sadrziStil(polje, "valid"));

    }

}