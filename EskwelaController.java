
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
    	ps = connectiont.prepareStatement(sql);
    	ps.setString(1, classcode);
    	ps.setString(2, stud_id);
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


