package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class StudentController {

    private ObservableList<Testna> testnaLista;
    @FXML
    private ListView<Testna> listView;

    @FXML
    private Circle circle;


    public StudentController() {

        testnaLista= FXCollections.observableArrayList();

        testnaLista.addAll(
                new Testna("Matematika","Oovo je tezaaaaak predmet"),
                new Testna("Matematika","Oovo je tezaaaaak predmet"),
                new Testna("Matematika","Oovo je tezaaaaak predmet"),
                new Testna("Matematika","Oovo je tezaaaaak predmet"),
                new Testna("Matematika","Oovo je tezaaaaak predmet"),
                new Testna("Matematika","Oovo je tezaaaaak predmet")
        );

    }

    @FXML
    public void initialize(){
        listView.setItems(testnaLista);
        listView.setCellFactory(testnaListView -> new TestnaListCell());
        Image im = new Image("/images/test.png");
        circle.setFill(new ImagePattern(im));
    }
}
