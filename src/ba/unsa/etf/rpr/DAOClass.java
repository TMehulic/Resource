package ba.unsa.etf.rpr;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DAOClass {

    private static DAOClass instance;
    private Connection conn;

    //TODO : OVDJE STATEMENTI IDU
    private PreparedStatement getStudents;
    private PreparedStatement getStudentById;
    private PreparedStatement getProfessors;
    private PreparedStatement getProfessorById;
    private PreparedStatement getCoursesByProfessorId;
    private PreparedStatement getCoursesByStudentId;
    private PreparedStatement getCourseNews;
    

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
            //todo : dodati ime baze
            conn = DriverManager.getConnection("jdbc:sqlite:bazaaaaaaa");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    private void regenerisiBazu() {
        Scanner input= null;
        try {
            //todo : i ovo popraviti
            input = new Scanner(new FileInputStream("baza.db.sql"));
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

}
