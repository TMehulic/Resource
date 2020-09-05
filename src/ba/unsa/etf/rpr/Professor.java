package ba.unsa.etf.rpr;

import java.time.LocalDate;

public class Professor extends Person {

    private TitleInfo titleInfo;

    public Professor(int id, String lastName, String firstName, String fathersName, String placeOfBirth, String jmbg, String phone, String email, String image, LocalDate birthDate, Gender gender, ResidenceInfo residenceInfo, TitleInfo titleInfo) {
        super(id, lastName, firstName, fathersName, placeOfBirth, jmbg, phone, email, image, birthDate, gender, residenceInfo);
        this.titleInfo = titleInfo;
    }

    public TitleInfo getTitleInfo() {
        return titleInfo;
    }

    public void setTitleInfo(TitleInfo titleInfo) {
        this.titleInfo = titleInfo;
    }
}
