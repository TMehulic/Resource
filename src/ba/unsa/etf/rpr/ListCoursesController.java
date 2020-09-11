package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ListCoursesController {

    private DAOClass dao;

    public TableView<Course> tableViewCourses;
    public TableColumn<Course,String> colName;
    public TableColumn<Course,String> colDesc;
    public TableColumn<Course,Integer> colEcts;
    public Label labelName;
    public Button btnRemoveCourse;
    public Button btnCourseProfessors;
    public Button btnDashboard;



    public ListCoursesController() {
    }

    @FXML
    public void initialize(){
        dao=DAOClass.getInstance();
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colEcts.setCellValueFactory(new PropertyValueFactory<>("ects"));
        tableViewCourses.setItems(dao.getCourses());


        btnRemoveCourse.setOnAction(removeCourse);
        btnCourseProfessors.setOnAction(viewCourseProfessors);
        btnDashboard.setOnAction(returnToDashboard);
    }


    private EventHandler<ActionEvent> removeCourse = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewCourses.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Obriši kurs");
                alert.setHeaderText(null);
                alert.setContentText("Molimo potvrdite vaš izbor.");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                    Course removed=tableViewCourses.getSelectionModel().getSelectedItem();
                    dao.removeCourse(removed);
                    tableViewCourses.getItems().remove(removed);
                    tableViewCourses.getSelectionModel().selectFirst();
                }
                alert.close();
            }
        }
    };

    private EventHandler<ActionEvent> returnToDashboard = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AdminController ctrl = new AdminController();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/admin.fxml"));
            loader.setController(ctrl);
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Main.getGuiStage().setTitle("Admin");
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        }
    };

    private EventHandler<ActionEvent> viewCourseProfessors = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewCourses.getSelectionModel().isEmpty()){
                ListCourseProfessorsController ctrl = new ListCourseProfessorsController(tableViewCourses.getSelectionModel().getSelectedItem().getId());
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
                Main.getGuiStage().setResizable(false);
            }
        }
    };



}
