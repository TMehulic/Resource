package ba.unsa.etf.rpr;

import static org.junit.jupiter.api.Assertions.*;

import ba.unsa.etf.rpr.controllers.CreateCourseController;
import ba.unsa.etf.rpr.utilities.InvalidInputException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ResourceBundle;

@ExtendWith(ApplicationExtension.class)
class CreateCourseControllerTest {

    CreateCourseController ctrl;

    public static boolean sadrziStil(TextField polje, String stil) {
        for (String s : polje.getStyleClass())
            if (s.equals(stil)) return true;
        return false;
    }

    @Start
    public void start (Stage stage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createCourse.fxml"),resourceBundle);
        ctrl = new CreateCourseController();
        loader.setController(ctrl);
        Parent mainNode = loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void testInputs(FxRobot robot){
        Label errorLabel = robot.lookup("#errorLabel").queryAs(Label.class);
        assertThrows(InvalidInputException.class,()->{
            ctrl.checkInputs();
        });
        robot.clickOn("#btnConfirm");
        assertEquals("Naziv kursa mora imati barem 3 znaka.",errorLabel.getText());
        robot.clickOn("#fldCourseName");
        robot.write("Test");
        robot.clickOn("#btnConfirm");
        assertEquals("ECTS mora biti pozitivan broj.",errorLabel.getText());
    }

    @Test
    void validacija(FxRobot robot) {

        TextField polje = robot.lookup("#fldCourseName").queryAs(TextField.class);
        TextField polje2= robot.lookup("#fldEcts").queryAs(TextField.class);
        robot.clickOn("#fldCourseName").write("a");
        assertTrue(sadrziStil(polje, "invalid"));
        robot.clickOn("#fldCourseName");
        robot.clickOn("#fldCourseName").write("aaaa");
        assertTrue(sadrziStil(polje, "valid"));
        robot.clickOn("#fldEcts").write("a");
        assertTrue(sadrziStil(polje2, "invalid"));

    }

}