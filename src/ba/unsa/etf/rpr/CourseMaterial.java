package ba.unsa.etf.rpr;

public class CourseMaterial {

    private int id;
    private String title;
    private String path;
    private int courseId;

    public CourseMaterial(int id, String title, String path, int courseId) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.courseId = courseId;
    }

    public CourseMaterial(String title, String path, int courseId) {
        this.title = title;
        this.path = path;
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
