package ba.unsa.etf.rpr;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Arrays;

public class CourseMaterialsListCell extends ListCell<CourseMaterial> {

    @FXML
    public HBox hbox;
    private FXMLLoader mLLoader;
    @FXML
    public Label materialName;
    @FXML
    public ImageView materialIcon;

    private final String [] textExt = {".pdf",".txt"};
    private final String [] videoExt = {".mp4"};
    private final String [] imageExt = {".png",".jpg",".jpeg"};


    public CourseMaterialsListCell() {
        {
            setStyle("-fx-padding: 0 0 0.5 0 ");
        }
    }

    @Override
    protected void updateItem(CourseMaterial material, boolean b) {
        super.updateItem(material, b);
        if(b || material == null) {

            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/courseMaterialsLV.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            materialName.setWrapText(true);
            materialName.setText(material.getTitle());

            setIcon(material);

            setText(null);
            setGraphic(hbox);
        }
    }

    private void setIcon(CourseMaterial material){
        String ext = getExtension(material.getPath());
        Image img = null;
        if(Arrays.asList(textExt).contains(ext)) {
            img = new Image("images/icons/txt.png");
        }else if(Arrays.asList(imageExt).contains(ext)){
            img = new Image("images/icons/img.png");
        }else if(Arrays.asList(videoExt).contains(ext)){
            img = new Image("images/icons/mp4.png");
        }
        materialIcon.setImage(img);
    }

    private String getExtension(String fileName){
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return fileName.substring(lastIndexOf);
    }
}
