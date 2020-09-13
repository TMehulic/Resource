package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.*;
import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.models.EducationInfo;
import ba.unsa.etf.rpr.models.Student;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import ba.unsa.etf.rpr.utilities.PrintReport;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public Button btnPrint;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;



    public ListStudentsController() {
        menuClass=new MenuItemClass();
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
        btnPrint.setOnAction(printStudents);

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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/studentsList.fxml"), Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EventHandler<ActionEvent> printStudents = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try {
                new PrintReport().showReport(dao.getConn());
            } catch (JRException e1) {
                e1.printStackTrace();
            }
        }
    };



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
                    try{
                        String path = "resources/"+removed.getImage();
                        Path fileToDeletePath = Paths.get(path);
                        Files.delete(fileToDeletePath);
                        dao.removeStudent(removed);
                        tableViewStudents.getItems().remove(removed);
                        tableViewStudents.getSelectionModel().selectFirst();
                    }catch (IOException e) {
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
