package ba.unsa.etf.rpr;

import com.sun.source.tree.Tree;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CourseController {

    private int courseId;
    private DAOClass dao;

    @FXML
    public ListView<CourseNews> listNews;
    public ListView<CourseMaterial> materials;

    public Label labelName;
    public Label labelDesc;
    public Button btnUpload;


    public CourseController(int courseId) {
        this.courseId=courseId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @FXML
    public void initialize(){
        dao = DAOClass.getInstance();
        Course course = dao.getCourse(getCourseId());
        labelName.setText(course.getName());
        labelDesc.setWrapText(true);
        labelDesc.setText(course.getDescription());
        btnUpload.setOnAction(uploadFile);
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

        materials.setItems(FXCollections.observableArrayList(dao.getCourseMaterials(getCourseId())));
        materials.setCellFactory(courseMaterialListView -> new CourseMaterialsListCell());
    }

    EventHandler<ActionEvent> uploadFile = new EventHandler<ActionEvent>() {
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
            dao.addCourseMaterial(new CourseMaterial("resources/courseMaterials/"+getCourseId()+"-"+uploadedFile.getName(),uploadedFile.getName(),getCourseId()));
        }
    };

    private void setMaterials(){

    }

    private String getExtension(String fileName){
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }
}
