package ba.unsa.etf.rpr;

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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class CreateStudentControllerTest {

    CreateStudentController ctrl;
    @Start
    public void start (Stage stage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createStudent.fxml"),resourceBundle);
        ctrl = new CreateStudentController();
        loader.setController(ctrl);
        Parent mainNode = loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    void validacija(FxRobot robot) {

        TextField polje = robot.lookup("#fldPhone").queryAs(TextField.class);
        robot.clickOn("#fldPhone").write("123");
        assertTrue(CreateProfessorControllerTest.sadrziStil(polje, "valid"));
        robot.clickOn("#fldPhone").write("a");
        assertTrue(CreateProfessorControllerTest.sadrziStil(polje, "invalid"));
        robot.clickOn("#fldPhone");
        robot.clickOn("#fldPhone").write("aaaa");
        assertTrue(CreateProfessorControllerTest.sadrziStil(polje, "invalid"));

    }

}