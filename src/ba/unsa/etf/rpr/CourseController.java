package ba.unsa.etf.rpr;

import com.sun.source.tree.Tree;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class CourseController {

    private int courseId;
    private DAOClass dao;

    @FXML
    public ListView<CourseNews> listNews;
    public ListView<CourseMaterial> materials;

    public Label labelName;
    public Label labelDesc;
    public Button btnUpload;
    public Button btnAddNews;


    public CourseController(int courseId) {
        this.courseId=courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    @FXML
    public void initialize(){
        dao = DAOClass.getInstance();
        Course course = dao.getCourse(getCourseId());
        labelName.setText(course.getName());
        labelDesc.setWrapText(true);
        labelDesc.setText(course.getDescription());
        btnUpload.setOnAction(uploadFile);
        btnAddNews.setOnAction(addNews);
        materials.setOnMouseClicked(materialClicked);
        setNews();
        setMaterials();
    }

    private void setNews(){
        TreeMap<LocalDate, ArrayList<String>> news = dao.getCourseNews(getCourseId());
        List<CourseNews> newsList = new ArrayList<>();
        for(Map.Entry<LocalDate,ArrayList<String>> e:news.entrySet()){
            for(int i=0;i<e.getValue().size();i++){
                newsList.add(new CourseNews(e.getKey(),e.getValue().get(i)));
            }
        }
        listNews.setItems(FXCollections.observableArrayList(newsList));
        listNews.setCellFactory(courseNewsListView->new CourseNewsListCell());
    }

    private void setMaterials(){
        materials.setItems(FXCollections.observableArrayList(dao.getCourseMaterials(getCourseId())));
        materials.setCellFactory(courseMaterialListView -> new CourseMaterialsListCell());
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
            try {
                FileInputStream inputStream = new FileInputStream(uploadedFile);
                FileOutputStream outputStream =new FileOutputStream(new File("resources/courseMaterials/"+getCourseId()+"-"+uploadedFile.getName()));
                int b;
                while ((b=inputStream.read())!=-1){
                    outputStream.write(b);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dao.addCourseMaterial(new CourseMaterial(uploadedFile.getName(),"resources/courseMaterials/"+getCourseId()+"-"+uploadedFile.getName(),getCourseId()));
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
                listNews.getItems().add(news);
            }
        }
    };

}
