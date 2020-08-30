package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class StudentController {

    private ObservableList<Testna> testnaLista;
    @FXML
    private ListView<Testna> listView;


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
    }
}
