package ba.unsa.etf.rpr;

import java.time.LocalDate;

public class Professor extends Person {

    private String title;

    public Professor(String lastName, String firstName, String fathersName, String placeOfBirth, long jmbg, String phone, String email, String image, LocalDate birthDate, Gender gender, String adress, Canton canton, String county, String title) {
        super(lastName, firstName, fathersName, placeOfBirth, jmbg, phone, email, image, birthDate, gender, adress, canton, county);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
