package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ListCourseProfessorsController {

    private DAOClass dao;
    private int courseId;


    public ListView<Professor> listViewProfessors;
    public Button btnRemoveProfessor;
    public Button btnAddProfessor;

    public ListCourseProfessorsController(int courseId) {
        this.courseId = courseId;
    }

    @FXML
    public void initialize(){

        dao=DAOClass.getInstance();

        btnAddProfessor.setOnAction(addProfessor);
        btnRemoveProfessor.setOnAction(removeProfessor);

        listViewProfessors.setItems(FXCollections.observableArrayList(dao.getProfessorsOnCourse(courseId)));
        listViewProfessors.setCellFactory(courseProfessorsListView -> new CourseProfessorListCell());
    }

    private EventHandler<ActionEvent> addProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AddCourseProfessorController ctrl = new AddCourseProfessorController(courseId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/addCourseProfessor.fxml"));
            loader.setController(ctrl);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getGuiStage().setTitle("Add professor");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        }
    };

    private EventHandler<ActionEvent> removeProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!listViewProfessors.getSelectionModel().isEmpty()){
                Professor professor = listViewProfessors.getSelectionModel().getSelectedItem();
                dao.removeProfessorFromCourse(listViewProfessors.getSelectionModel().getSelectedItem(),courseId);
                listViewProfessors.getItems().remove(professor);
                listViewProfessors.getSelectionModel().selectFirst();
            }
        }
    };


}