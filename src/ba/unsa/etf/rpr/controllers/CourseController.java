package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.*;
import ba.unsa.etf.rpr.models.Course;
import ba.unsa.etf.rpr.models.CourseMaterial;
import ba.unsa.etf.rpr.models.CourseNews;
import ba.unsa.etf.rpr.models.DAOClass;
import ba.unsa.etf.rpr.utilities.CourseMaterialsListCell;
import ba.unsa.etf.rpr.utilities.CourseNewsListCell;
import ba.unsa.etf.rpr.utilities.MenuItemClass;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class CourseController {

    private int courseId;
    private DAOClass dao;
    private FXMLLoader loader;

    private boolean isProfessor=false;

    @FXML
    public ListView<CourseNews> listNews;
    public ListView<CourseMaterial> materials;

    public Label labelName;
    public Label labelDesc;
    public Button btnUpload;
    public Button btnAddNews;
    public Button btnDashboard;
    public Button btnStudents;

    public MenuItemClass menuClass;
    public MenuItem itemAbout;
    public MenuItem itemBosnian;
    public MenuItem itemEnglish;
    public MenuItem itemLogout;



    public CourseController(int courseId) {
        this.courseId=courseId;
        menuClass=new MenuItemClass();
    }

    public int getCourseId() {
        return courseId;
    }

    @FXML
    public void initialize(){
        dao = DAOClass.getInstance();
        Course course = dao.getCourse(getCourseId());
        isProfessor=dao.isProfessor(HomeController.userID);

        labelName.setText(course.getName());
        labelDesc.setWrapText(true);
        labelDesc.setText(course.getDescription());
        btnDashboard.setOnAction(returnToDashboard);
        materials.setOnMouseClicked(materialClicked);

        if(isProfessor){
            setProfessorOptions();
        }else{
            btnUpload.setVisible(false);
            btnAddNews.setVisible(false);
            btnStudents.setVisible(false);
        }

        setMenuListeners();
        setNews();
        setMaterials();
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
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/course.fxml"),Main.bundle);
        loader.setController(this);
        try {
            Main.getGuiStage().setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setNews(){
        TreeMap<LocalDate, ArrayList<String>> news = dao.getCourseNews(getCourseId());
        List<CourseNews> newsList = new ArrayList<>();
        for(Map.Entry<LocalDate,ArrayList<String>> e:news.entrySet()){
            for(int i=0;i<e.getValue().size();i++){
                newsList.add(new CourseNews(e.getKey(),e.getValue().get(i)));
            }
        }
        newsList = newsList.stream().sorted((s1,s2)->s2.getDate().compareTo(s1.getDate())).collect(Collectors.toList());
        listNews.setItems(FXCollections.observableArrayList(newsList));
        listNews.setCellFactory(courseNewsListView->new CourseNewsListCell());
    }

    private void setMaterials(){
        materials.setItems(FXCollections.observableArrayList(dao.getCourseMaterials(getCourseId())));
        materials.setCellFactory(courseMaterialListView -> new CourseMaterialsListCell());
    }

    private void setProfessorOptions(){
        btnUpload.setOnAction(uploadFile);
        btnAddNews.setOnAction(addNews);
        btnStudents.setOnAction(showStudents);
    }


    private EventHandler<ActionEvent> uploadFile = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Izaberi materijal");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt","*.pdf"),
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                    new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac")
            );
            File uploadedFile = fileChooser.showOpenDialog(Main.getGuiStage());
            if(uploadedFile!=null){
                try {
                    FileInputStream inputStream = new FileInputStream(uploadedFile);
                    FileOutputStream outputStream =new FileOutputStream(new File("resources/courseMaterials/"+getCourseId()+"-"+uploadedFile.getName()));
                    int b;
                    while ((b=inputStream.read())!=-1){
                        outputStream.write(b);
                    }
                    inputStream.close();
                    outputStream.close();
                    CourseMaterial newMaterial = new CourseMaterial(uploadedFile.getName(),"resources/courseMaterials/"+getCourseId()+"-"+uploadedFile.getName(),getCourseId());
                    dao.addCourseMaterial(newMaterial);
                    materials.getItems().add(newMaterial);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private EventHandler<? super MouseEvent> materialClicked = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop desktop = Desktop.getDesktop();
                    File myFile = new File(materials.getSelectionModel().getSelectedItem().getPath());
                    desktop.open(myFile);
                } catch (IOException ignored) {

                }
            }
        }
    };

    private EventHandler<ActionEvent> addNews = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Nova obavijest");
            dialog.setHeaderText("Unesite obavijest ");
            dialog.setContentText("Obavijest : ");
            ImageView iv = new ImageView(new Image("images/icons/bell.png"));
            iv.setFitHeight(35);
            iv.setFitWidth(35);
            dialog.setGraphic(iv);
            dialog.getDialogPane().setMinWidth(600);
            Optional<String> result = dialog.showAndWait();
            if(result.isPresent()){
                CourseNews news=new CourseNews(LocalDate.now(),result.get());
                dao.addCourseNews(news,getCourseId());
                listNews.getItems().add(0,news);
            }
        }
    };

    private EventHandler<ActionEvent> returnToDashboard = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            if(dao.isProfessor(HomeController.userID)){
                ProfessorController ctrl = new ProfessorController();
                loader=new FXMLLoader(getClass().getResource("/fxml/professor.fxml"),Main.bundle);
                loader.setController(ctrl);
                Main.getGuiStage().setTitle("Professor");
                redirectToDashboard();
            }else if(dao.isStudent(HomeController.userID)){
                StudentController ctrl = new StudentController();
                loader=new FXMLLoader(getClass().getResource("/fxml/student.fxml"),Main.bundle);
                loader.setController(ctrl);
                Main.getGuiStage().setTitle("Student");
                redirectToDashboard();
            }
        }
    };

    private EventHandler<ActionEvent> showStudents = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            ListCourseStudentsController ctrl = new ListCourseStudentsController(courseId);
            loader=new FXMLLoader(getClass().getResource("/fxml/courseStudentsList.fxml"),Main.bundle);
            loader.setController(ctrl);
            Main.getGuiStage().setTitle("Students");
            Main.load(loader);
        }
    };


    public void redirectToDashboard(){
        try {
            Parent root;
            root = loader.load();
            Main.getGuiStage().setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            Main.getGuiStage().centerOnScreen();
            Main.getGuiStage().show();
            Main.getGuiStage().setResizable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
