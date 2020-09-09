package ba.unsa.etf.rpr;

import com.mysql.cj.xdevapi.DatabaseObject;
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


public class CreateStudentController {

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

    public Button btnConfirm;




    public CreateStudentController() {
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

        ResidenceInfo resInfo = new ResidenceInfo(adress,canton,county);


        String degree = fldDegree.getText();
        int index = Integer.parseInt(fldIndex.getText());
        int cycle = cbCycle.getValue();
        int year = cbYear.getValue();

        //todo : ako je proslo sve provjere
        saveImage(lastName,firstName);

        EducationInfo eduInfo = new EducationInfo(degree,cycle,year,index);

//        AdminController ctrl = new AdminController();
//        loader=new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
//        loader.setController(ctrl);
//        Main.getGuiStage().setTitle("Admin");
//        try {
//            Parent root = loader.load();
//            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
//            Main.getGuiStage().show();
//            Main.getGuiStage().setResizable(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Student student=new Student(-1,lastName,firstName,fathersName,birthPlace,jmbg,phone,mail,imagePath,date,gender,resInfo,eduInfo);
        dao.createStudent(student);

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
