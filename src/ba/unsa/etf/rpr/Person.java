package ba.unsa.etf.rpr;

import java.time.LocalDate;

public abstract class Person {

    private String lastName;
    private String firstName;
    private String fathersName;
    private String placeOfBirth;
    private long jmbg;
    private String phone;   //as string, to easily handle leading zeros in database
    private String email;
    private String image;   //path to image
    private LocalDate birthDate;
    private Gender gender;

    //Prebivaliste
    private String adress;
    private Canton canton;
    private String county;


    public Person(String lastName, String firstName, String fathersName, String placeOfBirth, long jmbg, String phone, String email, String image, LocalDate birthDate, Gender gender, String adress, Canton canton, String county) {
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
        this.adress = adress;
        this.canton = canton;
        this.county = county;
    }
}
