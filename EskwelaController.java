
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
    	connection = DriverManager.getConnection(url, user, pass);
    }
    public void addClass(String classcode, String room, String day, String subjid, int units) throws Exception{ //Create group create/insert table 1
    	sql = "Insert into classes values(?,?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.setString(2, room);
    	ps.setString(3, day);
    	ps.setString(4, subjid);
    	ps.setString(5, units);
    	ps.executeUpdate();
    }
    
    public ResultSet listStudents() throws Exception { //Create group retrieve table 2
    	statement = connection.createStatment();
    	sql = "select * from instructor";
    	return statement.executeQuery(sql);
    }
    
    public void updateStudentInfo(int stud_id, String col, String replacement){ //Create group update table 3
    	sql = "update student set " + col + " = ? where stud_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, replacement);
    	ps.setString(2, stud_id);
    }
    
    public void deleteStudentClass(String classcode, String stud_id) throws Exception { //Create group delete table 4
    	sql = "delete from student_class where classcode = ? and stud_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.setString(2, stud_id);
    	ps.executeUpdate();
    }
	
    //justine create/insert table 3
    public void addStudent(int stud_id, String first_name, String last_name, String address, char gender, String course, int year) throws Exception{ //Create group create/insert table 3
    	sql = "Insert into student values(?,?,?,?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, stud_id);
    	ps.setString(2, first_name);
    	ps.setString(3, last_name);
    	ps.setString(4, address);
    	ps.setString(5, gender);
    	ps.setString(6, course);
    	ps.setString(7, year);
    	ps.executeUpdate();
    }
    
    //justine retrieve table 4
    public ResultSet listSubjects() throws Exception { //Create group retrieve table 4
    	statement = connection.createStatment();
    	sql = "select * from subject";
    	return statement.executeQuery(sql);
    }
    
    //justine update table 1
    public void updateClass(String classcode, String col, String replacement){ //Create group update table 1
    	sql = "update student set " + col + " = ? where classcode = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, replacement);
    	ps.setString(2, classcode);
    	ps.executeUpdate();
    }
    
    //justine delete table 2
    public void deleteInstructor(String instruc_id) throws Exception { //Create group delete table 2
    	sql = "delete from instructor where instruc_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, instruc_id);
    	ps.executeUpdate();
    }
    
    public void addSubject(String description,int instruc_id,String subjid,int units) throws Exception{ //Create group create/insert table 4
    	sql = "Insert into classes values(?,?,?,?)";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, description);
    	ps.setString(2, instruc_id);
    	ps.setString(3, subjid);
    	ps.setString(4, units);
    	ps.executeUpdate();
    }
    
    public ResultSet listClasses() throws Exception { //Create group retrieve table 1
    	statement = connection.createStatment();
    	sql = "select * from classes";
    	return statement.executeQuery(sql);
    }
    
    public void updateInstructor(int instruc_id,String col, String replacement){ //Create group update table 2
    	sql = "update instructor set " + col + " = ? where instruc_id = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, replacement);
    	ps.setString(2, instruc_id);
    }
    
    public void deleteStudent(int stud_id) throws Exception { //Create group delete table 3
    	sql = "delete from student where stud_id = ? ";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, stud_id);
    	ps.executeUpdate();
    }

	public void deleteClass(String classcode) throws Exception { table 1 delete
		sql = "delete from classes where classcode = ?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, classcode);
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


