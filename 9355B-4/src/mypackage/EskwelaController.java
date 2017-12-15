
package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EskwelaController {
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private String sql;
    
    public void dbaseConnect(String url, String user, String pwd) throws Exception{
    	Class.forName("com.mysql.jdbc.Driver");
    	connection = DriverManager.getConnection(url, user, pwd);
    }
    //start of classes table
    public void addClass(String classcode, String room, String day, String subjid, int units) throws Exception{
    	sql = "Insert into classes values(?,?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.setString(2, room);
    	ps.setString(3, day);
    	ps.setString(4, subjid);
    	ps.setInt(5, units);
    	ps.executeUpdate();
    }
    
    public void updateClass(String classcode, String col, String replacement) throws Exception{
    	sql = "update classes set " + col + " = ? where classcode = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, replacement);
    	ps.setString(2, classcode);
    	ps.executeUpdate();
    }
    
    public ResultSet listClass() throws Exception {
    	statement = connection.createStatement();
    	sql = "select * from classes";
    	return statement.executeQuery(sql);
    }
    
    public void deleteClass(String classcode) throws Exception {
    	sql = "delete from classes where classcode = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.executeUpdate();
    }
    //end of class table
    
    //start of instructor table
    public void addInstructor(String instruc_id, String first_name, String last_name, String gender, String classcode) throws Exception{
    	sql = "Insert into instructor values(?,?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, instruc_id);
    	ps.setString(2, first_name);
    	ps.setString(3, last_name);
    	ps.setString(4, gender);
    	ps.setString(5, classcode);
    	ps.executeUpdate();
    }
    public ResultSet listInstructor() throws Exception {
    	statement = connection.createStatement();
    	sql = "select * from instructor";
    	return statement.executeQuery(sql);
    }
    
    public void updateInstructor(int instruc_id,String col, String replacement) throws Exception{
    	sql = "update instructor set " + col + " = ? where instruc_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, replacement);
    	ps.setInt(2, instruc_id);
    	ps.executeUpdate();
    }
    
    public void deleteInstructor(int instruc_id) throws Exception {
    	sql = "delete from instructor where instruc_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setInt(1, instruc_id);
    	ps.executeUpdate();
    }
    //end of Instructor table
    
    //start of student table
    public void addStudent(int stud_id, String first_name, String last_name, String address, String gender, String course, int year) throws Exception{
    	sql = "Insert into student values(?,?,?,?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setInt(1, stud_id);
    	ps.setString(2, first_name);
    	ps.setString(3, last_name);
    	ps.setString(4, address);
    	ps.setString(5, gender);
    	ps.setString(6, course);
    	ps.setInt(7, year);
    	ps.executeUpdate();
    }
    
    public ResultSet listStudent() throws Exception {
    	statement = connection.createStatement();
    	sql = "select * from student";
    	return statement.executeQuery(sql);
    }
    
    public void updateStudent(int stud_id, String col, String replacement) throws Exception{
    	sql = "update student set " + col + " = ? where stud_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, replacement);
    	ps.setInt(2, stud_id);
    	ps.executeUpdate();
    }
    
    public void deleteStudent(int stud_id) throws Exception {
    	sql = "delete from student where stud_id = ? ";
    	ps = connection.prepareStatement(sql);
    	ps.setInt(1, stud_id);
    	ps.executeUpdate();
    }
    //end of student table
    
    //start of subject table
    public void addSubject(String subjid,String description,int units,int instruc_id) throws Exception{
    	sql = "Insert into subject values(?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, subjid);
    	ps.setString(2, description);
    	ps.setInt(3, units);
    	ps.setInt(4, instruc_id);
    	ps.executeUpdate();
    }
    
    public ResultSet listSubject() throws Exception {
    	statement = connection.createStatement();
    	sql = "select * from subject";
    	return statement.executeQuery(sql);
    	
    }
    
    public void updateSubject(String subjid, String col, String replacement) throws Exception{
    	sql = "update subject set " + col + " = ? where stud_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, replacement);
    	ps.setString(2, subjid);
    	ps.executeUpdate();
    }
    
    public void deleteSubject(String subjid) throws Exception {
    	sql = "delete from subject where stud_id = ? ";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, subjid);
    	ps.executeUpdate();
    }

	
	public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (ps != null) {
            	ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
	}	
}


