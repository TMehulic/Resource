package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.*;
import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.models.Professor;
import ba.unsa.etf.rpr.models.TitleInfo;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;



    public ListProfessorsController() {
        menuClass=new MenuItemClass();
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
        setMenuListeners();
    }

    public void setMenuListeners(){
        itemBosnian.setOnAction(actionEvent -> {
            menuClass.setBosnian();
            restart();
        });

        itemEnglish.setOnAction(actionEvent -> {
            menuClass.setEnglish();
            restart();
        });

        itemLogout.setOnAction(actionEvent -> menuClass.logOut());

        itemAbout.setOnAction(actionEvent -> menuClass.showAbout());
    }

    public void restart(){
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/professorsList.fxml"), Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    try{
                        Professor removed=tableViewProfessors.getSelectionModel().getSelectedItem();

                        String path = "resources/"+removed.getImage();
                        Path fileToDeletePath = Paths.get(path);
                        Files.delete(fileToDeletePath);
                        dao.removeProfessor(removed);
                        tableViewProfessors.getItems().remove(removed);
                        tableViewProfessors.getSelectionModel().selectFirst();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
            Main.getGuiStage().setTitle("Admin");
            Main.load(loader);
        }
    };

}
