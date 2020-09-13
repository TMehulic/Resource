package ba.unsa.etf.rpr.models;

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
    private PreparedStatement getCourses;
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
    private PreparedStatement getCourseProfessorId;
    private PreparedStatement getPersonId;
    private PreparedStatement getEducationInfoId;
    private PreparedStatement getResidenceInfoId;
    private PreparedStatement getTitleInfoId;
    private PreparedStatement getStudentsOnCourse;
    private PreparedStatement getStudentsNotOnCourse;
    private PreparedStatement getProfessorsOnCourse;
    private PreparedStatement getProfessorsNotOnCourse;
    private PreparedStatement getNextUserId;
    private PreparedStatement getCourseId;

    private PreparedStatement addCourseMaterial;
    private PreparedStatement addCourseNews;
    private PreparedStatement addStudentToCourse;
    private PreparedStatement addProfessorToCourse;
    private PreparedStatement addStudent;
    private PreparedStatement addProfessor;
    private PreparedStatement addEducationInfo;
    private PreparedStatement addResidenceInfo;
    private PreparedStatement addTitleInfo;
    private PreparedStatement addUser;
    private PreparedStatement addCourse;

    private PreparedStatement removeStudentFromCourse;
    private PreparedStatement removeProfessorFromCourse;
    private PreparedStatement removeStudent;
    private PreparedStatement removeProfessor;
    private PreparedStatement removeEduInfo;
    private PreparedStatement removeResInfo;
    private PreparedStatement removeTitInfo;
    private PreparedStatement removeUser;
    private PreparedStatement removeStudentFromCourses;
    private PreparedStatement removeProfessorFromCourses;
    private PreparedStatement removeCourse;
    private PreparedStatement removeCourseFromCourseMaterials;
    private PreparedStatement removeCourseFromCourseNews;
    private PreparedStatement removeCourseFromCourseProfessor;
    private PreparedStatement removeCourseFromCourseStudent;

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
            getCourseStudentId = conn.prepareStatement("SELECT MAX(id)+1 FROM courseStudent");
            getCourseProfessorId = conn.prepareStatement("SELECT MAX(id)+1 FROM courseProfessor");
            getStudentsOnCourse = conn.prepareStatement("SELECT id FROM person WHERE id IN ( SELECT personId FROM courseStudent WHERE courseId=? )");
            getStudentsNotOnCourse = conn.prepareStatement("SELECT id FROM person WHERE id NOT IN ( SELECT personId FROM courseStudent WHERE courseId=?) AND professor IS NULL");
            getProfessorsOnCourse = conn.prepareStatement("SELECT id FROM person WHERE id IN ( SELECT personId FROM courseProfessor WHERE courseId=?)");
            getProfessorsNotOnCourse = conn.prepareStatement("SELECT id FROM person WHERE id NOT IN ( SELECT personId FROM courseProfessor WHERE courseId=?) AND professor IS NOT NULL");
            getPersonId = conn.prepareStatement("SELECT MAX(id)+1 FROM person");
            getEducationInfoId = conn.prepareStatement("SELECT MAX(id)+1 FROM educationInfo");
            getResidenceInfoId = conn.prepareStatement("SELECT MAX(id)+1 FROM residenceInfo");
            getTitleInfoId = conn.prepareStatement("SELECT MAX(id)+1 FROM titleInfo");
            getNextUserId = conn.prepareStatement("SELECT MAX(id)+1 FROM user");
            getCourseId = conn.prepareStatement("SELECT MAX(id)+1 FROM course");
            getCourses = conn.prepareStatement("SELECT * FROM course");

            addCourseMaterial = conn.prepareStatement("INSERT INTO courseMaterials VALUES (?,?,?,?)");
            addCourseNews = conn.prepareStatement("INSERT INTO courseNews VALUES (?,?,?,?)");
            addStudentToCourse = conn.prepareStatement("INSERT INTO courseStudent VALUES(?,?,?)");
            addProfessorToCourse = conn.prepareStatement("INSERT INTO courseProfessor VALUES(?,?,?)");
            addStudent = conn.prepareStatement("INSERT INTO person VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            addProfessor = conn.prepareStatement("INSERT INTO person VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            addEducationInfo = conn.prepareStatement("INSERT INTO educationInfo VALUES (?,?,?,?,?,?)");
            addResidenceInfo = conn.prepareStatement("INSERT INTO residenceInfo VALUES (?,?,?,?,?)");
            addTitleInfo = conn.prepareStatement("INSERT INTO titleInfo VALUES (?,?,?)");
            addUser = conn.prepareStatement("INSERT INTO user VALUES(?,?,?,?)");
            addCourse = conn.prepareStatement("INSERT INTO course VALUES(?,?,?,?)");

            removeStudentFromCourse = conn.prepareStatement("DELETE FROM courseStudent WHERE personId=? AND courseId=?");
            removeProfessorFromCourse = conn.prepareStatement("DELETE FROM courseProfessor WHERE personId=? AND courseId=?");
            removeStudent = conn.prepareStatement("DELETE FROM person WHERE id=?");
            removeProfessor = conn.prepareStatement("DELETE FROM person WHERE id=?");
            removeEduInfo = conn.prepareStatement("DELETE FROM educationInfo WHERE personId=?");
            removeResInfo = conn.prepareStatement("DELETE FROM residenceInfo WHERE personId=?");
            removeTitInfo = conn.prepareStatement("DELETE FROM titleInfo WHERE personId=?");
            removeUser = conn.prepareStatement("DELETE FROM user WHERE personId=?");
            removeStudentFromCourses = conn.prepareStatement("DELETE FROM courseStudent WHERE personId=?");
            removeProfessorFromCourses = conn.prepareStatement("DELETE FROM courseProfessor WHERE personId=?");
            removeCourse = conn.prepareStatement("DELETE FROM course WHERE id=?");
            removeCourseFromCourseMaterials = conn.prepareStatement("DELETE FROM courseMaterials WHERE courseId=?");
            removeCourseFromCourseNews = conn.prepareStatement("DELETE FROM courseNews WHERE courseId=?");
            removeCourseFromCourseProfessor = conn.prepareStatement("DELETE FROM courseProfessor WHERE courseId=?");
            removeCourseFromCourseStudent = conn.prepareStatement("DELETE FROM courseStudent WHERE courseId=?");



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

    public ObservableList<Student> getStudents(){
        ArrayList<Student> students=new ArrayList<>();
        try {
            ResultSet rs=getStudents.executeQuery();
            while (rs.next()){
                students.add(getStudent(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FXCollections.observableArrayList(students);
    }

    public ObservableList<Professor> getProfessors(){
        ArrayList<Professor> professors = new ArrayList<>();
        try{
            ResultSet rs = getProfessors.executeQuery();
            while (rs.next()){
                professors.add(getProfessor(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FXCollections.observableArrayList(professors);
    }

    public ObservableList<Course> getCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        try {
            ResultSet rs = getCourses.executeQuery();
            while (rs.next()){
                courses.add(getCourse(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FXCollections.observableArrayList(courses);
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

    public ObservableList<Professor> getProfessorsOnCourse(int courseId){
        ArrayList<Professor> professors = new ArrayList<>();
        try{
            getProfessorsOnCourse.setInt(1,courseId);
            ResultSet rs = getProfessorsOnCourse.executeQuery();
            while (rs.next()){
                professors.add(getProfessor(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FXCollections.observableArrayList(professors);
    }

    public ObservableList<Professor> getProfessorsNotOnCourse(int courseId){
        ArrayList<Professor> professors=new ArrayList<>();
        try {
            getProfessorsNotOnCourse.setInt(1,courseId);
            ResultSet rs = getProfessorsNotOnCourse.executeQuery();
            while (rs.next()){
                professors.add(getProfessor(rs.getInt(1)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return FXCollections.observableArrayList(professors);
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

    public void removeProfessorFromCourse(Professor professor, int courseId){
        try {
            removeProfessorFromCourse.setInt(1,professor.getId());
            removeProfessorFromCourse.setInt(2,courseId);
            removeProfessorFromCourse.executeUpdate();
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
            addStudentToCourse.setInt(2,courseId);
            addStudentToCourse.setInt(3,studentId);
            addStudentToCourse.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addProfessorToCourse(int professorId, int courseId){
        try{
            ResultSet rs = getCourseProfessorId.executeQuery();
            int id = 1;
            if(rs.next()) id = rs.getInt(1);
            addProfessorToCourse.setInt(1,id);
            addProfessorToCourse.setInt(2,courseId);
            addProfessorToCourse.setInt(3,professorId);
            addProfessorToCourse.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createStudent(Student student){
        try {
            ResultSet rs = getPersonId.executeQuery();
            int studentId = 1;
            if(rs.next()) studentId = rs.getInt(1);
            addStudent.setInt(1,studentId);
            addStudent.setString(2,student.getLastName());
            addStudent.setString(3,student.getFirstName());
            addStudent.setString(4,student.getFathersName());
            addStudent.setString(5,student.getPlaceOfBirth());
            addStudent.setString(6,student.getJmbg());
            addStudent.setString(7,student.getPhone());
            addStudent.setString(8,student.getEmail());
            addStudent.setString(9,student.getImage());
            addStudent.setString(10,student.getBirthDate().toString());
            addStudent.setString(11,student.getGender().toString());
            addStudent.setInt(12,1);
            addStudent.setNull(13,Types.NULL);
            addStudent.executeUpdate();
            createResidenceInfo(student.getResidenceInfo(),studentId);
            createEducationInfo(student.getEducationInfo(),studentId);
            student.setId(studentId);
            createUser(student);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createProfessor(Professor professor){
        try {
            ResultSet rs = getPersonId.executeQuery();
            int profId = 1;
            if(rs.next()) profId = rs.getInt(1);
            addProfessor.setInt(1,profId);
            addProfessor.setString(2,professor.getLastName());
            addProfessor.setString(3,professor.getFirstName());
            addProfessor.setString(4,professor.getFathersName());
            addProfessor.setString(5,professor.getPlaceOfBirth());
            addProfessor.setString(6,professor.getJmbg());
            addProfessor.setString(7,professor.getPhone());
            addProfessor.setString(8,professor.getEmail());
            addProfessor.setString(9,professor.getImage());
            addProfessor.setString(10,professor.getBirthDate().toString());
            addProfessor.setString(11,professor.getGender().toString());
            addProfessor.setNull(12,Types.NULL);
            addProfessor.setInt(13,1);
            addProfessor.executeUpdate();
            createResidenceInfo(professor.getResidenceInfo(),profId);
            createTitleInfo(professor.getTitleInfo(),profId);
            professor.setId(profId);
            createUser(professor);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createResidenceInfo(ResidenceInfo resInfo,int personId){
        try {
            int resInfoId = 1;
            ResultSet rs = getResidenceInfoId.executeQuery();
            if(rs.next()) resInfoId=rs.getInt(1);
            addResidenceInfo.setInt(1,resInfoId);
            addResidenceInfo.setString(2,resInfo.getAdress());
            addResidenceInfo.setString(3,resInfo.getCanton().toString());
            addResidenceInfo.setString(4, resInfo.getCounty());
            addResidenceInfo.setInt(5,personId);
            addResidenceInfo.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createEducationInfo(EducationInfo eduInfo, int personId){
        try{
            int eduInfoId=1;
            ResultSet rs = getEducationInfoId.executeQuery();
            if(rs.next()) eduInfoId=rs.getInt(1);
            addEducationInfo.setInt(1,eduInfoId);
            addEducationInfo.setString(2,eduInfo.getDegree());
            addEducationInfo.setInt(3,eduInfo.getCycle());
            addEducationInfo.setInt(4,eduInfo.getYear());
            addEducationInfo.setInt(5,eduInfo.getIndex());
            addEducationInfo.setInt(6,personId);
            addEducationInfo.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTitleInfo(TitleInfo titleInfo, int personId){
        try{
            int titleInfoId=1;
            ResultSet rs = getTitleInfoId.executeQuery();
            if(rs.next()) titleInfoId=rs.getInt(1);
            addTitleInfo.setInt(1,titleInfoId);
            addTitleInfo.setString(2,titleInfo.getTitle());
            addTitleInfo.setInt(3,personId);
            addTitleInfo.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createUser(Person user){
        try {
            int userId = 1;
            ResultSet rs = getNextUserId.executeQuery();
            if(rs.next()) userId=rs.getInt(1);
            addUser.setInt(1,userId);
            addUser.setInt(2,user.getId());
            addUser.setString(3,user.getEmail());
            addUser.setString(4,createPassword(user));
            addUser.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String createPassword(Person user){
        String pass = "";
        int lastIndex = user.getEmail().lastIndexOf('@');
        pass = user.getEmail().substring(0,lastIndex)+"123";
        return pass;
    }

    public void createCourse(Course course){
        try {
            int courseId=1;
            ResultSet rs = getCourseId.executeQuery();
            if(rs.next()) courseId = rs.getInt(1);
            addCourse.setInt(1,courseId);
            addCourse.setString(2,course.getName());
            addCourse.setString(3,course.getDescription());
            addCourse.setInt(4,course.getEcts());
            addCourse.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeStudent(Student student){
        try {
            removeStudent.setInt(1,student.getId());
            removeEduInfo.setInt(1,student.getId());
            removeResInfo.setInt(1,student.getId());
            removeUser.setInt(1,student.getId());
            removeStudentFromCourses.setInt(1,student.getId());
            removeStudent.executeUpdate();
            removeEduInfo.executeUpdate();
            removeResInfo.executeUpdate();
            removeUser.executeUpdate();
            removeStudentFromCourses.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeProfessor(Professor professor){
        try{
            removeProfessor.setInt(1,professor.getId());
            removeResInfo.setInt(1,professor.getId());
            removeTitInfo.setInt(1,professor.getId());
            removeUser.setInt(1,professor.getId());
            removeProfessorFromCourses.setInt(1,professor.getId());
            removeProfessor.executeUpdate();
            removeResInfo.executeUpdate();
            removeTitInfo.executeUpdate();
            removeUser.executeUpdate();
            removeProfessorFromCourses.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeCourse(Course course){
        try{
            removeCourse.setInt(1,course.getId());
            removeCourseFromCourseStudent.setInt(1,course.getId());
            removeCourseFromCourseProfessor.setInt(1,course.getId());
            removeCourseFromCourseNews.setInt(1,course.getId());
            removeCourseFromCourseMaterials.setInt(1,course.getId());
            removeCourse.executeUpdate();
            removeCourseFromCourseStudent.executeUpdate();
            removeCourseFromCourseProfessor.executeUpdate();
            removeCourseFromCourseNews.executeUpdate();
            removeCourseFromCourseMaterials.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
