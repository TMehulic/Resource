package ba.unsa.etf.rpr.models;

public class Course {

    public int id;
    public String name;
    public String description;
    public int ects;


    public Course(int id,String name,String desc, int ects) {
        this.id=id;
        this.name = name;
        this.ects = ects;
        this.description=desc;
    }

    public Course(String name, String description, int ects) {
        this.name = name;
        this.description = description;
        this.ects = ects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }
}
