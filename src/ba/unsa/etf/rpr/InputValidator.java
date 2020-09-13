package ba.unsa.etf.rpr;

import java.util.regex.Pattern;

public class InputValidator {

    public InputValidator() {
    }

    public boolean isCorrectName(String newName) {
        return (newName.length()>=3 && !Pattern.compile( "[0-9]" ).matcher( newName ).find());
    }

    public boolean isCorrectBirthPlace(String place){
        return place.length()>0 && !Pattern.compile( "[0-9]" ).matcher( place ).find();
    }

    public boolean isCorrectJMBG(String jmbg){
        return jmbg.length()==13 && Pattern.compile("^\\d{13}$").matcher(jmbg).find();
    }

    public boolean isCorrectEmail(String email){
        return Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$").matcher(email).find();
    }

    public boolean isCorrectPhone(String phone){
        return Pattern.compile("^[+]*[0-9]+$").matcher(phone).find();
    }

    public boolean isCorrectCounty(String county) {
        return county.length()>0;
    }

    public boolean isCorrectAdress(String adress) {
        return adress.length()>0;
    }

    public boolean isCorrectTitle(String title){
        return title.length()>0;
    }

    public boolean isCorrectEcts(String ects){
        try{
            int value = Integer.parseInt(ects);
            return value>0;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public boolean isCorrectDegree(String degree){
        return degree.length()>0;
    }

    public boolean isCorrectIndex(String index){
        return index.length()==5 && Pattern.compile("^\\d{5}$").matcher(index).find();
    }
}
