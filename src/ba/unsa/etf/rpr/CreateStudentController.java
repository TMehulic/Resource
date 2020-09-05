package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;


public class CreateStudentController {

    public TextField fldLastName;
    public TextField fldFathersName;
    public TextField fldName;
    public TextField fldBirthPlace;
    public ToggleGroup tgGender;
    public RadioButton btnMale;
    public RadioButton btnFemale;
    public TextField fldJmbg;
    public DatePicker pickerDate;
    public TextField fldPhone;
    public TextField fldEmail;
    public Button fldImage;

    public TextField fldAdress;
    public ChoiceBox<Canton> cbCanton;
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

        cbCanton.getItems().setAll(Canton.values());
        cbCycle.getItems().setAll(1,2,3);
        cbYear.getItems().setAll(1,2,3,4,5,6);
        tgGender=new ToggleGroup();
        tgGender.getToggles().addAll(btnMale,btnFemale);

    }

    public void createStudent(ActionEvent actionEvent){

        //Guess da je input good
        String lastName = fldLastName.getText();
        String fathersName = fldFathersName.getText();
        String firstName = fldName.getText();
        String birthPlace = fldBirthPlace.getText();
        String jmbg = fldJmbg.getText();
        LocalDate date = pickerDate.getValue();
        String phone = fldPhone.getText();
        String mail = fldEmail.getText();
        Gender gender=Gender.MALE;
        if(tgGender.getSelectedToggle()!=null){
            RadioButton selected = (RadioButton) tgGender.getSelectedToggle();
            if(((RadioButton) tgGender.getSelectedToggle()).getText().equals("Muško")){
                gender=Gender.MALE;
            }else{
                gender=Gender.FEMALE;
            }
        }
        String adress = fldAdress.getText();
        String county = fldCounty.getText();
        Canton canton = cbCanton.getValue();
        String degree = fldDegree.getText();
        int index = Integer.parseInt(fldIndex.getText());
        int cycle = cbCycle.getValue();
        int year = cbYear.getValue();

    }

    private void ispisi(Student person){
        System.out.println(person.getFirstName());
        System.out.println(person.getLastName());
        System.out.println(person.getFathersName());
        System.out.println(person.getPlaceOfBirth());
        System.out.println(person.getJmbg());
        System.out.println(person.getBirthDate());
        System.out.println(person.getPhone());
        System.out.println(person.getEmail());
        System.out.println(person.getGender());

    }

}
