package ba.unsa.etf.rpr;

import java.time.LocalDate;

public class Student extends Person {

    private EducationInfo educationInfo;

    public Student(int id, String lastName, String firstName, String fathersName, String placeOfBirth, String jmbg, String phone, String email, String image, LocalDate birthDate, Gender gender, ResidenceInfo residenceInfo, EducationInfo educationInfo) {
        super(id, lastName, firstName, fathersName, placeOfBirth, jmbg, phone, email, image, birthDate, gender, residenceInfo);
        this.educationInfo = educationInfo;
    }


    // Getters needed to fill listViews

    public int getIndex(){
        return educationInfo.getIndex();
    }

    public String getDegree(){
        return educationInfo.getDegree();
    }

    public EducationInfo getEducationInfo() {
        return educationInfo;
    }

    public void setEducationInfo(EducationInfo educationInfo) {
        this.educationInfo = educationInfo;
    }
}
