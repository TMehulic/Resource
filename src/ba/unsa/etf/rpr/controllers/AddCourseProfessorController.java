package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.Main;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import ba.unsa.etf.rpr.models.Professor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
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

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;

    public AddCourseProfessorController(int courseId) {
        this.courseId = courseId;
        menuClass=new MenuItemClass();
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/addCourseProfessor.fxml"), Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EventHandler<ActionEvent> addProfessor = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(!tableViewProfessors.getSelectionModel().isEmpty()){
                int profId = tableViewProfessors.getSelectionModel().getSelectedItem().getId();
                dao.addProfessorToCourse(profId,courseId);
                btnCancel.fire();
            }
        }
    };

    private EventHandler<ActionEvent> backToList = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            ListCourseProfessorsController ctrl = new ListCourseProfessorsController(courseId);
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/courseProfessorsList.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.load(loader);
        }
    };
}
