package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class CreateStudentController implements IValidateInputs {

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

    public TextField fldAdress;
    public ChoiceBox<Canton> cbCanton;
    public TextField fldCounty;

    public TextField fldDegree;
    public TextField fldIndex;
    public ChoiceBox<Integer> cbCycle;
    public ChoiceBox<Integer> cbYear;

    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty fathersName;
    private SimpleStringProperty birthPlace;
    private SimpleStringProperty jmbg;
    private SimpleStringProperty phone;
    private SimpleStringProperty email;
    private SimpleStringProperty address;
    private SimpleStringProperty county;
    private SimpleStringProperty title;

    public Button btnConfirm;
    public Button btnCancel;

    public Label errorLabel;

    private InputValidator validator;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;



    public CreateStudentController() {
        firstName = new SimpleStringProperty("");
        lastName = new SimpleStringProperty("");
        fathersName = new SimpleStringProperty("");
        birthPlace = new SimpleStringProperty("");
        jmbg = new SimpleStringProperty("");
        phone = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        address = new SimpleStringProperty("");
        county = new SimpleStringProperty("");
        title = new SimpleStringProperty("");
        validator=new InputValidator();
        menuClass=new MenuItemClass();
    }

    @FXML
    public void initialize(){

        dao = DAOClass.getInstance();

        cbCanton.getItems().setAll(Canton.values());
        cbCycle.getItems().setAll(1,2,3);
        cbYear.getItems().setAll(1,2,3,4,5,6);
        tgGender=new ToggleGroup();
        tgGender.getToggles().addAll(btnMale,btnFemale);
        btnUpload.setOnAction(uploadImage);
        btnCancel.setOnAction(cancelAction);

        bindProperties();
        setMenuListeners();
        setListeners();


    }

    private void bindProperties(){
        fldName.textProperty().bindBidirectional(firstName);
        fldLastName.textProperty().bindBidirectional(lastName);
        fldFathersName.textProperty().bindBidirectional(fathersName);
        fldBirthPlace.textProperty().bindBidirectional(birthPlace);
        fldJmbg.textProperty().bindBidirectional(jmbg);
        fldPhone.textProperty().bindBidirectional(phone);
        fldEmail.textProperty().bindBidirectional(email);
        fldAdress.textProperty().bindBidirectional(address);
        fldCounty.textProperty().bindBidirectional(county);
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/createStudent.fxml"),Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
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

        fldDegree.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectDegree(newValue)){
                fldDegree.getStyleClass().removeAll("invalid");
                fldDegree.getStyleClass().add("valid");
            }else{
                fldDegree.getStyleClass().removeAll("valid");
                fldDegree.getStyleClass().add("invalid");
            }
        });

        fldIndex.textProperty().addListener((obs,oldValue,newValue)->{
            if(validator.isCorrectIndex(newValue)){
                fldIndex.getStyleClass().removeAll("invalid");
                fldIndex.getStyleClass().add("valid");
            }else{
                fldIndex.getStyleClass().removeAll("valid");
                fldIndex.getStyleClass().add("invalid");
            }
        });

    }

    @Override
    public void checkInputs() throws InvalidInputException {
        if(!validator.isCorrectName(firstName.get())) throw new InvalidInputException("Ime mora sadržavati barem 3 slova.");
        if(!validator.isCorrectName(lastName.get())) throw new InvalidInputException("Prezime mora sadržavati barem 3 slova.");
        if(!validator.isCorrectName(fathersName.get())) throw new InvalidInputException("Ime oca mora sadržavati barem 3 slova.");
        if(!validator.isCorrectAdress(address.get())) throw new InvalidInputException("Molimo unesite ispravnu adresu.");
        if(!validator.isCorrectCounty(county.get())) throw new InvalidInputException("Molimo unesite ispravnu općinu.");
        if(!validator.isCorrectBirthPlace(birthPlace.get())) throw new InvalidInputException("Molimo unesite mjesto rođenja.");
        if(!validator.isCorrectEmail(email.get())) throw new InvalidInputException("Molimo unesite validan email.");
        if(!validator.isCorrectPhone(phone.get())) throw new InvalidInputException("Molimo unesite ispravan broj mobitela.");
        if(!validator.isCorrectJMBG(jmbg.get())) throw new InvalidInputException("JMBG mora sadržavati 13 cifara.");
        if(!validator.isCorrectIndex(fldIndex.getText())) throw new InvalidInputException("Molimo unesite ispravan index.");
        if(!validator.isCorrectDegree(fldDegree.getText())) throw new InvalidInputException("Molimo unesite smjer.");
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


    public void createStudent(ActionEvent actionEvent){

        try{
            checkInputs();

            LocalDate date = pickerDate.getValue();

            Gender gender=Gender.MALE;
            if(tgGender.getSelectedToggle()!=null){
                if(tgGender.getSelectedToggle().equals(tgGender.getToggles().get(0))){
                    gender=Gender.MALE;
                }else{
                    gender=Gender.FEMALE;
                }
            }


            Canton canton = cbCanton.getValue();

            ResidenceInfo resInfo = new ResidenceInfo(address.get(),canton,county.get());


            String degree = fldDegree.getText();
            int index = Integer.parseInt(fldIndex.getText());
            int cycle = cbCycle.getValue();
            int year = cbYear.getValue();

            saveImage(lastName.get(),firstName.get());

            EducationInfo eduInfo = new EducationInfo(degree,cycle,year,index);

            Student student=new Student(-1,lastName.get(),firstName.get(),fathersName.get(),birthPlace.get(),jmbg.get(),phone.get(),email.get(),imagePath,date,gender,resInfo,eduInfo);
            dao.createStudent(student);
            AdminController.returnToDashboard();
        }catch (Exception e){
            errorLabel.setText(e.getMessage());
        }

    }

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
