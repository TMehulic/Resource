package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class CreateStudentController {

    public TextField fldLastName;
    public TextField fldFathersName;
    public TextField fldName;
    public TextField fldBirthPlace;
    // todo : use toggle
    public ToggleGroup tgGender;
    public RadioButton btnMale;
    public RadioButton btnFemale;
    public TextField fldJmbg;
    // todo : use date
    public TextField fldBirthDate;
    public TextField fldPhone;
    public TextField fldEmail;
    public Button fldImage;

    public TextField fldAdress;
    public ChoiceBox<String> cbCanton;
    public TextField fldCounty;

    public TextField fldDegree;
    public TextField fldIndex;
    public ChoiceBox<Integer> cbCycle;
    public ChoiceBox<Integer> cbYear;

    public Button btnConfirm;




    public CreateStudentController() {
    }

    @FXML
    public void initialize(){
        tgGender=new ToggleGroup();
        tgGender.getToggles().addAll(btnMale,btnFemale);
    }

    public void createStudent(ActionEvent actionEvent){

        System.out.println(fldLastName.getText());
        if(tgGender.getSelectedToggle()!=null){
            RadioButton selected = (RadioButton) tgGender.getSelectedToggle();
            System.out.println(selected.getText());
        }
    }

}
