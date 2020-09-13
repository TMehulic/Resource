package ba.unsa.etf.rpr;

import java.util.Locale;
import java.util.ResourceBundle;

public class MenuItemClass {

    public void setEnglish(){
        Locale.setDefault(new Locale("en_US"));
        Main.bundle= ResourceBundle.getBundle("Translation");
    }

    public void setBosnian(){
        Locale.setDefault(new Locale("bs"));
        Main.bundle= ResourceBundle.getBundle("Translation");
    }

    public void showAbout(){

    }

    public void logOut(){
        Main.returnHome();
    }

}
