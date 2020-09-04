package ba.unsa.etf.rpr;

import java.time.LocalDate;

public abstract class Person {

    private String lastName;
    private String firstName;
    private String fathersName;
    private String placeOfBirth;
    private String jmbg;    //as string, to avoid problems with leading zeroes
    private String phone;   //as string, to easily handle leading zeroes in database
    private String email;
    private String image;   //path to image
    private LocalDate birthDate;
    private Gender gender;
    private ResidenceInfo residenceInfo;

    //Prebivaliste


    public Person(String lastName, String firstName, String fathersName, String placeOfBirth, String jmbg, String phone, String email, String image, LocalDate birthDate, Gender gender, ResidenceInfo residenceInfo) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.fathersName = fathersName;
        this.placeOfBirth = placeOfBirth;
        this.jmbg = jmbg;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.birthDate = birthDate;
        this.gender = gender;
        this.residenceInfo=residenceInfo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ResidenceInfo getResidenceInfo() {
        return residenceInfo;
    }

    public void setResidenceInfo(ResidenceInfo residenceInfo) {
        this.residenceInfo = residenceInfo;
    }
}
