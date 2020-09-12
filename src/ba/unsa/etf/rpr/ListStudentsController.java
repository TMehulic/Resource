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

public class ListStudentsController {

    private DAOClass dao;

    public TableView<Student> tableViewStudents;
    public TableColumn<EducationInfo,Integer> colIndex;
    public TableColumn<Student,String> colName;
    public TableColumn<Student,String> colLastName;
    public TableColumn<EducationInfo,String> colDegree;
    public TableColumn<EducationInfo,Integer> colCycle;
    public TableColumn<EducationInfo,Integer> colYear;
    public Label labelName;
    public Button btnRemoveStudent;
    public Button btnDashboard;



    public ListStudentsController() {
    }

    @FXML
    public void initialize(){
        dao=DAOClass.getInstance();
        colIndex.setCellValueFactory(new PropertyValueFactory<>("index"));
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDegree.setCellValueFactory(new PropertyValueFactory<>("degree"));
        colCycle.setCellValueFactory(new PropertyValueFactory<>("cycle"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        tableViewStudents.setItems(dao.getStudents());


        btnRemoveStudent.setOnAction(removeStudent);
        btnDashboard.setOnAction(returnToDashboard);
    }


    private EventHandler<ActionEvent> removeStudent = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewStudents.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ispiši studenta");
                alert.setHeaderText(null);
                alert.setContentText("Molimo potvrdite vaš izbor.");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                    Student removed=tableViewStudents.getSelectionModel().getSelectedItem();
                    dao.removeStudent(removed);
                    tableViewStudents.getItems().remove(removed);
                    tableViewStudents.getSelectionModel().selectFirst();
                }
                alert.close();
            }
        }
    };

    private EventHandler<ActionEvent> returnToDashboard = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AdminController ctrl = new AdminController();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/admin.fxml"),Main.bundle);
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

}
