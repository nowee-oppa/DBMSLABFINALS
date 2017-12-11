/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBController {
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;
    private ResultSet resultSet;
    private String sql;
    
    public void dbaseConnect(String url, String user, String pass) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, pass);
    }
    
    public ResultSet getAllContacts() throws Exception {
        statement = connection.createStatement();
        sql = "select * from person left join person_number using(name);";
        return statement.executeQuery(sql);
    }
    
    public ResultSet getContactInfo(String name) throws Exception {
    	sql = "select * from person join person_number using(name) where name = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, name);
    	return ps.executeQuery();
    }
    
    public ResultSet searchContactInfo(String name) throws Exception {
    	sql = "select * from person left join person_number using(name) where name like ?;";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, "%" + name + "%");
    	return ps.executeQuery();
    }
    
    public void createContact(String name, String addr, String email, String gender) throws Exception {
		sql = "Insert into person values(?,?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setString(1,name);
		ps.setString(2, addr);
		ps.setString(3, email);
		ps.setString(4, gender);
		ps.executeUpdate();
	}
    
    public void deleteContact(String name) throws Exception {
    	sql = "delete from person where name = ?";
    	ps = connection.prepareStatement(sql);
    	ps.setString(1, name);
    	ps.executeUpdate();
    }
    
	public void updateContactInfo(String name, String col, String replacement) throws Exception {
		sql = "update person set " + col + " = ? where name = ?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, replacement);
		ps.setString(2, name);
		ps.executeUpdate();
	}

	public void updateNumberInfo(String name, String col, int row, String replacement) throws Exception {
		sql = "select * from person_number where name = ?";
		ps = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ps.setString(1, name);
		resultSet = ps.executeQuery();
		resultSet.absolute(row);
		if (col.equals("number")) {
			resultSet.updateString("number", replacement);
		} else {
			int val = replacement.equalsIgnoreCase("home") ? 1 : 0;
			resultSet.updateInt("home", val);
		}
		resultSet.updateRow();
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
