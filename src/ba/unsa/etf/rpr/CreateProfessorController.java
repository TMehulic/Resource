package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;



public class CreateProfessorController implements IValidateInputs {

    private FXMLLoader loader;
    private DAOClass dao;
    private File image;
    private String imagePath;

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
    public Button btnUpload;
    public Button btnCancel;

    public TextField fldAdress;
    public ChoiceBox<Canton> cbCanton;
    public TextField fldCounty;

    public TextField fldTitle;
    public Label errorLabel;

    private InputValidator validator;

    public CreateProfessorController() {
        validator=new InputValidator();
    }

    @FXML
    public void initialize(){

        dao = DAOClass.getInstance();

        cbCanton.getItems().setAll(Canton.values());
        tgGender=new ToggleGroup();
        tgGender.getToggles().addAll(btnMale,btnFemale);
        btnUpload.setOnAction(uploadImage);
        btnCancel.setOnAction(cancelAction);
        setListeners();

    }

    public void createProfessor(ActionEvent actionEvent){
        try{
            checkInputs();

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
            ResidenceInfo resInfo = new ResidenceInfo(adress,canton,county);


            String title = fldTitle.getText();
            TitleInfo titleInfo = new TitleInfo(title);

            saveImage(lastName,firstName);


            Professor professor=new Professor(-1,lastName,firstName,fathersName,birthPlace,jmbg,phone,mail,imagePath,date,gender,resInfo,titleInfo);
            dao.createProfessor(professor);

            AdminController.returnToDashboard();
        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }

    }


    @Override
    public void setListeners() {
        fldName.textProperty().addListener((obs,oldName,newName)->{
            if(validator.isCorrectName(newName)){
                fldName.getStyleClass().removeAll("invalid");
                fldName.getStyleClass().add("valid");
            }else{
                fldName.getStyleClass().removeAll("valid");
                fldName.getStyleClass().add("invalid");
            }
        });

        fldFathersName.textProperty().addListener((obs,oldName,newName)->{
            if(validator.isCorrectName(newName)){
                fldFathersName.getStyleClass().removeAll("invalid");
                fldFathersName.getStyleClass().add("valid");
            }else{
                fldFathersName.getStyleClass().removeAll("valid");
                fldFathersName.getStyleClass().add("invalid");
            }
        });

        fldLastName.textProperty().addListener((obs,oldName,newName)->{
            if(validator.isCorrectName(newName)){
                fldLastName.getStyleClass().removeAll("invalid");
                fldLastName.getStyleClass().add("valid");
            }else{
                fldLastName.getStyleClass().removeAll("valid");
                fldLastName.getStyleClass().add("invalid");
            }
        });

        fldBirthPlace.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectBirthPlace(newValue)){
                fldBirthPlace.getStyleClass().removeAll("invalid");
                fldBirthPlace.getStyleClass().add("valid");
            }else{
                fldBirthPlace.getStyleClass().removeAll("valid");
                fldBirthPlace.getStyleClass().add("invalid");
            }
        });

        fldJmbg.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectJMBG(newValue)){
                fldJmbg.getStyleClass().removeAll("invalid");
                fldJmbg.getStyleClass().add("valid");
            }else{
                fldJmbg.getStyleClass().removeAll("valid");
                fldJmbg.getStyleClass().add("invalid");
            }
        });


        fldPhone.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectPhone(newValue)){
                fldPhone.getStyleClass().removeAll("invalid");
                fldPhone.getStyleClass().add("valid");
            }else{
                fldPhone.getStyleClass().removeAll("valid");
                fldPhone.getStyleClass().add("invalid");
            }
        });

        fldEmail.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectEmail(newValue)){
                fldEmail.getStyleClass().removeAll("invalid");
                fldEmail.getStyleClass().add("valid");
            }else{
                fldEmail.getStyleClass().removeAll("valid");
                fldEmail.getStyleClass().add("invalid");
            }
        });


        fldAdress.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectAdress(newValue)){
                fldAdress.getStyleClass().removeAll("invalid");
                fldAdress.getStyleClass().add("valid");
            }else{
                fldAdress.getStyleClass().removeAll("valid");
                fldAdress.getStyleClass().add("invalid");
            }
        });

        fldCounty.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectCounty(newValue)){
                fldCounty.getStyleClass().removeAll("invalid");
                fldCounty.getStyleClass().add("valid");
            }else{
                fldCounty.getStyleClass().removeAll("valid");
                fldCounty.getStyleClass().add("invalid");
            }
        });

        fldTitle.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectTitle(newValue)){
                fldTitle.getStyleClass().removeAll("invalid");
                fldTitle.getStyleClass().add("valid");
            }else{
                fldTitle.getStyleClass().removeAll("valid");
                fldTitle.getStyleClass().add("invalid");
            }
        });
    }


    @Override
    public void checkInputs() throws InvalidInputException {
        if(!validator.isCorrectName(fldName.getText())) throw new InvalidInputException("Ime mora sadržavati barem 3 slova.");
        if(!validator.isCorrectName(fldLastName.getText())) throw new InvalidInputException("Prezime mora sadržavati barem 3 slova.");
        if(!validator.isCorrectName(fldFathersName.getText())) throw new InvalidInputException("Ime oca mora sadržavati barem 3 slova.");
        if(!validator.isCorrectAdress(fldAdress.getText())) throw new InvalidInputException("Molimo unesite ispravnu adresu.");
        if(!validator.isCorrectCounty(fldCounty.getText())) throw new InvalidInputException("Molimo unesite ispravnu općinu.");
        if(!validator.isCorrectBirthPlace(fldBirthPlace.getText())) throw new InvalidInputException("Molimo unesite mjesto rođenja.");
        if(!validator.isCorrectEmail(fldEmail.getText())) throw new InvalidInputException("Molimo unesite validan email.");
        if(!validator.isCorrectPhone(fldPhone.getText())) throw new InvalidInputException("Molimo unesite ispravan broj mobitela.");
        if(!validator.isCorrectJMBG(fldJmbg.getText())) throw new InvalidInputException("JMBG mora sadržavati 13 cifara.");
        if(!validator.isCorrectTitle(fldTitle.getText())) throw new InvalidInputException("Molimo unesite titulu");
        if(pickerDate.getValue()==null) throw new InvalidInputException("Molimo unesite datum rođenja");
        if(cbCanton.getSelectionModel().isEmpty()) throw new InvalidInputException("Molimo izaberite kanton.");
        if(!tgGender.getSelectedToggle().isSelected()) throw new InvalidInputException("Molimo izaberite spol.");
        if(image==null) throw new InvalidInputException("Molimo izaberite sliku.");
    }

    private EventHandler<ActionEvent> cancelAction = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            AdminController.returnToDashboard();
        }
    };



    private EventHandler<ActionEvent> uploadImage = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Izaberite sliku");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            image = fileChooser.showOpenDialog(Main.getGuiStage());
        }
    };

    private void saveImage(String lastName, String firstName){
        if(image!=null){
            try {
                FileInputStream inputStream = new FileInputStream(image);
                FileOutputStream outputStream =new FileOutputStream(new File("resources/images/users/"+lastName+firstName+getExtension(image.getName())));
                int b;
                while ((b=inputStream.read())!=-1){
                    outputStream.write(b);
                }
                inputStream.close();
                outputStream.close();
                imagePath = "resources/images/users/"+lastName+firstName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getExtension(String fileName){
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }


}
