package ba.unsa.etf.rpr;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DAOClass {

    private static DAOClass instance;
    private Connection conn;

    private PreparedStatement getStudents;
    private PreparedStatement getStudentById;
    private PreparedStatement getProfessors;
    private PreparedStatement getProfessorById;
    private PreparedStatement getCoursesByProfessorId;
    private PreparedStatement getCoursesByStudentId;
    private PreparedStatement getCourseNews;
    private PreparedStatement getEducationInfo;
    private PreparedStatement getResidenceInfo;
    private PreparedStatement getTitleInfo;
    private PreparedStatement getUserId;
    private PreparedStatement getCourseById;
    private PreparedStatement getCourseMaterials;
    private PreparedStatement getCourseMaterialsId;
    private PreparedStatement getCourseNewsId;
    private PreparedStatement getCourseStudentId;
    private PreparedStatement getStudentsOnCourse;
    private PreparedStatement getStudentsNotOnCourse;

    private PreparedStatement addCourseMaterial;
    private PreparedStatement addCourseNews;
    private PreparedStatement addStudentToCourse;

    private PreparedStatement removeStudentFromCourse;

    private PreparedStatement checkIfStudent ;
    private PreparedStatement checkIfProfessor;


    public Connection getConn(){
        return conn;
    }

    public static DAOClass getInstance(){
        if(instance == null){
            instance=new DAOClass();
        }
        return instance;
    }

    private static void initialize() {
        instance = new DAOClass();
    }

    private DAOClass(){

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            getStudents = conn.prepareStatement("SELECT * FROM person WHERE student IS NOT NULL");
        } catch (SQLException throwables) {
            regenerateDB();
            try {
                getStudents = conn.prepareStatement("SELECT * FROM person WHERE student IS NOT NULL");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            getProfessors=conn.prepareStatement("SELECT * FROM person WHERE professor IS NOT NULL");
            getStudentById=conn.prepareStatement("SELECT * FROM person WHERE student IS NOT NULL AND person.id=?");
            getProfessorById=conn.prepareStatement("SELECT * FROM person WHERE professor IS NOT NULL AND person.id=?");
            getCoursesByStudentId=conn.prepareStatement("SELECT * FROM course c WHERE c.id IN ( SELECT c1.courseId FROM courseStudent c1 WHERE c1.personId=? )");
            getCoursesByProfessorId=conn.prepareStatement("SELECT * FROM course c WHERE c.id IN ( SELECT c1.courseId FROM courseProfessor c1 WHERE c1.personId=? )");
            getCourseNews=conn.prepareStatement("SELECT * FROM courseNews c WHERE c.courseId=?");
            getEducationInfo=conn.prepareStatement("SELECT * FROM educationInfo WHERE personId=?");
            getResidenceInfo=conn.prepareStatement("SELECT * FROM residenceInfo WHERE personId=?");
            getTitleInfo=conn.prepareStatement("SELECT * FROM titleInfo WHERE personId=?");
            getUserId=conn.prepareStatement("SELECT personId FROM user WHERE email=? AND password=?");
            getCourseById = conn.prepareStatement("SELECT * FROM course WHERE id=?");
            getCourseMaterials = conn.prepareStatement("SELECT * FROM courseMaterials WHERE courseId=?");
            getCourseMaterialsId = conn.prepareStatement("SELECT MAX(id)+1 FROM courseMaterials");
            getCourseNewsId = conn.prepareStatement("SELECT MAX(id)+1 FROM courseNews");
            getCourseStudentId = conn.prepareStatement("SELECT MAX(ID)+1 FROM courseStudent");
            getStudentsOnCourse = conn.prepareStatement("SELECT id FROM person WHERE id IN ( SELECT personId FROM courseStudent WHERE courseId=? )");
            getStudentsNotOnCourse = conn.prepareStatement("SELECT id FROM person WHERE id NOT IN ( SELECT personId FROM courseStudent WHERE courseId=?)");

            addCourseMaterial = conn.prepareStatement("INSERT INTO courseMaterials VALUES (?,?,?,?)");
            addCourseNews = conn.prepareStatement("INSERT INTO courseNews VALUES (?,?,?,?)");
            addStudentToCourse = conn.prepareStatement("INSERT INTO courseSTUDENT VALUES(?,?,?)");

            removeStudentFromCourse = conn.prepareStatement("DELETE FROM courseStudent WHERE personId=? AND courseId=?");

            checkIfStudent = conn.prepareStatement("SELECT * FROM person WHERE id=? AND student IS NOT NULL");
            checkIfProfessor = conn.prepareStatement("SELECT * FROM person WHERE id=? AND professor IS NOT NULL");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void regenerateDB() {
        Scanner input= null;
        try {
            input = new Scanner(new FileInputStream("database.db.sql"));
            String sqlQuery="";
            while (input.hasNext()){
                sqlQuery+=input.nextLine();
                if(sqlQuery.charAt(sqlQuery.length()-1)==';'){
                    try {
                        Statement stnt=conn.createStatement();
                        stnt.execute(sqlQuery);
                        sqlQuery="";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public EducationInfo getEducationInfo(int id){
        try {
            getEducationInfo.setInt(1,id);
            ResultSet rs=getEducationInfo.executeQuery();
            if(!rs.next()) return null;
            return new EducationInfo(rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ResidenceInfo getResidenceInfo(int id){
        try {
            getResidenceInfo.setInt(1,id);
            ResultSet rs=getResidenceInfo.executeQuery();
            if(!rs.next()) return null;
            return new ResidenceInfo(rs.getString(2),Canton.valueOf(rs.getString(3)),rs.getString(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public TitleInfo getTitleInfo(int id){
        try {
            getTitleInfo.setInt(1,id);
            ResultSet rs = getTitleInfo.executeQuery();
            if(!rs.next()) return null;
            return new TitleInfo(rs.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Student getStudent(int id){
        try {
            getStudentById.setInt(1,id);
            ResultSet rs=getStudentById.executeQuery();
            if(!rs.next()) return null;
            EducationInfo eduInfo = getEducationInfo(id);
            ResidenceInfo resInfo = getResidenceInfo(id);
            Gender gender = Gender.valueOf(rs.getString(11));
            LocalDate birthDate = LocalDate.parse(rs.getString(10));
            return new Student(id,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),birthDate,gender,resInfo,eduInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Professor getProfessor(int id){
        try {
            getProfessorById.setInt(1,id);
            ResultSet rs= getProfessorById.executeQuery();
            if(!rs.next()) return null;
            ResidenceInfo resInfo = getResidenceInfo(id);
            TitleInfo titInfo = getTitleInfo(id);
            Gender gender = Gender.valueOf(rs.getString(11));
            LocalDate birthDate = LocalDate.parse(rs.getString(10));
            return new Professor(id,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),birthDate,gender,resInfo,titInfo);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public ArrayList<Course> getCoursesFromProfessor(int id){
        ArrayList<Course> courses=new ArrayList<>();
        try {
            getCoursesByProfessorId.setInt(1,id);
            ResultSet rs= getCoursesByProfessorId.executeQuery();
            while (rs.next()){
                courses.add(new Course(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courses;
    }

    public ArrayList<Course> getCoursesFromStudent(int id){
        ArrayList<Course> courses=new ArrayList<>();
        try {
            getCoursesByStudentId.setInt(1,id);
            ResultSet rs= getCoursesByStudentId.executeQuery();
            while (rs.next()){
                courses.add(new Course(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return courses;
    }

    public TreeMap<LocalDate,ArrayList<String>> getCourseNews(int id){
        TreeMap<LocalDate,ArrayList<String>> news = new TreeMap<>();
        try {
            getCourseNews.setInt(1,id);
            ResultSet rs = getCourseNews.executeQuery();
            while (rs.next()){
                LocalDate newsDate = LocalDate.parse(rs.getString(3));
                if (!news.containsKey(newsDate)) {
                    news.put(newsDate, new ArrayList<String>());
                }
                news.get(newsDate).add(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return news;
    }

    public ArrayList<Student> getStudents(){
        ArrayList<Student> students=new ArrayList<>();
        try {
            ResultSet rs=getStudents.executeQuery();
            while (rs.next()){
                students.add(getStudent(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    public Person getUser(String email, String password){
        Person person=null;
        try {
            getUserId.setString(1,email);
            getUserId.setString(2,password);
            ResultSet rs= getUserId.executeQuery();
            if(!rs.next()) return null;
            person=getStudent(rs.getInt(1));
            if(person==null){
                person=getProfessor(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public Course getCourse(int id){
        Course course = null;
        try {
            getCourseById.setInt(1,id);
            ResultSet rs = getCourseById.executeQuery();
            if(!rs.next()) return null;
            course=new Course(id,rs.getString(2),rs.getString(3),rs.getInt(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return course;
    }

    public ArrayList<CourseMaterial> getCourseMaterials(int id){
        ArrayList<CourseMaterial> materials = new ArrayList<>();
        try {
            getCourseMaterials.setInt(1,id);
            ResultSet rs = getCourseMaterials.executeQuery();
            while (rs.next()){
                materials.add(new CourseMaterial(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(id);
        System.out.println(materials.size());
        return materials;
    }

    public void addCourseMaterial(CourseMaterial material){
        try {
            ResultSet rs = getCourseMaterialsId.executeQuery();
            int id = 1;
            if(rs.next()) id = rs.getInt(1);
            addCourseMaterial.setInt(1,id);
            addCourseMaterial.setString(2,material.getTitle());
            addCourseMaterial.setString(3,material.getPath());
            addCourseMaterial.setInt(4,material.getCourseId());
            addCourseMaterial.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addCourseNews(CourseNews news,int courseId){
        try {
            ResultSet rs = getCourseNewsId.executeQuery();
            int id = 1;
            if(rs.next()) id = rs.getInt(1);
            addCourseNews.setInt(1,id);
            addCourseNews.setString(2,news.getNews());
            addCourseNews.setString(3,news.getDate().toString());
            addCourseNews.setInt(4,courseId);
            addCourseNews.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isProfessor(int id){
        try {
            checkIfProfessor.setInt(1,id);
            ResultSet rs = checkIfProfessor.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    };

    public boolean isStudent(int id){
        try {
            checkIfStudent.setInt(1,id);
            ResultSet rs = checkIfStudent.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ObservableList<Student> getStudentsOnCourse(int courseId){
        ArrayList<Student> students=new ArrayList<>();
        try {
            getStudentsOnCourse.setInt(1,courseId);
            ResultSet rs = getStudentsOnCourse.executeQuery();
            while (rs.next()){
                students.add(getStudent(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FXCollections.observableArrayList(students);
    }

    public ObservableList<Student> getStudentsNotOnCourse(int courseId){
        ArrayList<Student> students=new ArrayList<>();
        try {
            getStudentsNotOnCourse.setInt(1,courseId);
            ResultSet rs = getStudentsNotOnCourse.executeQuery();
            while (rs.next()){
                students.add(getStudent(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FXCollections.observableArrayList(students);
    };

    public void removeStudentFromCourse(Student student, int courseId){
        try {
            removeStudentFromCourse.setInt(1,student.getId());
            removeStudentFromCourse.setInt(2,courseId);
            removeStudentFromCourse.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addStudentToCourse(int studentId, int courseId){
        try {
            ResultSet rs = getCourseStudentId.executeQuery();
            int id = 1;
            if(rs.next()) id = rs.getInt(1);
            addStudentToCourse.setInt(1,id);
            addStudentToCourse.setInt(2,studentId);
            addStudentToCourse.setInt(3,courseId);
            addStudentToCourse.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
