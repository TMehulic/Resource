package ba.unsa.etf.rpr;

import com.sun.source.tree.Tree;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
//    public ListView<> materials;

    public Label labelName;
    public Label labelDesc;


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

    }
}
