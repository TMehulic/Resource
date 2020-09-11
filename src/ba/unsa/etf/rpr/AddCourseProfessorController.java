package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AddCourseProfessorController {

    private int courseId;
    private DAOClass dao;

    public TableView<Professor> tableViewProfessors;
    public TableColumn<Professor,String> colTitle;
    public TableColumn<Professor,String> colFirstName;
    public TableColumn<Professor,String> colLastName;

    public Button btnAddProfessor;
    public Button btnCancel;

    public AddCourseProfessorController(int courseId) {
        this.courseId = courseId;
    }

    @FXML
    public void initialize(){
        dao=DAOClass.getInstance();
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewProfessors.setItems(dao.getProfessorsNotOnCourse(courseId));


        btnAddProfessor.setOnAction(addProfessor);
        btnCancel.setOnAction(backToList);
    }

    private EventHandler<ActionEvent> addProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewProfessors.getSelectionModel().isEmpty()){
                int profId = tableViewProfessors.getSelectionModel().getSelectedItem().getId();
                dao.addProfessorToCourse(profId,courseId);
                CourseController ctrl = new CourseController(courseId);
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/courseProfessorsList.fxml"));
                loader.setController(ctrl);
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Main.getGuiStage().setTitle("Professors");
                Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                Main.getGuiStage().show();
                Main.getGuiStage().setResizable(true);
            }
        }
    };

    private EventHandler<ActionEvent> backToList = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            ListCourseProfessorsController ctrl = new ListCourseProfessorsController(courseId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/courseProfessorsList.fxml"));
            loader.setController(ctrl);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getGuiStage().setTitle("Professors");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        }
    };
}
