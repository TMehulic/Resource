package ba.unsa.etf.rpr.models;

public class EducationInfo {

    private String degree;
    private int cycle;
    private int year;
    private int index;

    public EducationInfo(String degree, int cycle, int year, int index) {
        this.degree = degree;
        this.cycle = cycle;
        this.year = year;
        this.index = index;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
