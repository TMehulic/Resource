package ba.unsa.etf.rpr;

import java.time.LocalDate;

public class Student extends Person {

    //Faculty
    private String smjer;
    private int ciklus;
    private int godina;
    private int index;

    public Student(String lastName, String firstName, String fathersName, String placeOfBirth, long jmbg, String phone, String email, String image, LocalDate birthDate, Gender gender, String adress, Canton canton, String county, String smjer, int ciklus, int godina, int index) {
        super(lastName, firstName, fathersName, placeOfBirth, jmbg, phone, email, image, birthDate, gender, adress, canton, county);
        this.smjer = smjer;
        this.ciklus = ciklus;
        this.godina = godina;
        this.index = index;
    }

    public String getSmjer() {
        return smjer;
    }

    public void setSmjer(String smjer) {
        this.smjer = smjer;
    }

    public int getCiklus() {
        return ciklus;
    }

    public void setCiklus(int ciklus) {
        this.ciklus = ciklus;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
