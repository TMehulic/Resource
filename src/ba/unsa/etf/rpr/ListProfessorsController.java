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

public class ListProfessorsController {

    private DAOClass dao;

    public TableView<Professor> tableViewProfessors;
    public TableColumn<Professor,String> colName;
    public TableColumn<Professor,String> colLastName;
    public TableColumn<TitleInfo,String> colTitle;
    public Label labelName;
    public Button btnRemoveProfessor;
    public Button btnDashboard;



    public ListProfessorsController() {
    }

    @FXML
    public void initialize(){
        dao=DAOClass.getInstance();
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewProfessors.setItems(dao.getProfessors());


        btnRemoveProfessor.setOnAction(removeProfessor);
        btnDashboard.setOnAction(returnToDashboard);
    }


    private EventHandler<ActionEvent> removeProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewProfessors.getSelectionModel().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ukloni profesora");
                alert.setHeaderText(null);
                alert.setContentText("Molimo potvrdite vaš izbor.");
                Optional<ButtonType> result=alert.showAndWait();
                if(result.get()==ButtonType.OK){
                    Professor removed=tableViewProfessors.getSelectionModel().getSelectedItem();
                    dao.removeProfessor(removed);
                    tableViewProfessors.getItems().remove(removed);
                    tableViewProfessors.getSelectionModel().selectFirst();
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

}
