package ba.unsa.etf.rpr;

public class Course {

    public String name;
    public String description;
    public int ects;

    //myb profesor


    public Course(String name,String desc, int ects) {
        this.name = name;
        this.ects = ects;
        this.description=desc;
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