package mypackage;

import java.sql.SQLException;
import java.util.Scanner;

public class ClassroomDBConsole {
    private static DBController controller;
    private static Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args) {
        controller = new DBController();
        try {   
        	int choice = 0;
        	String url = "jdbc:mysql://localhost/contact?useSSL=false";
            controller.dbaseConnect(url,"root",null);
        	while (true) {        		
                choice = selectTableMenu(); //edit for actual menu - select table you want to edit
                if (choice == 7) {
                	System.out.println("Thank your for trying this program...");
                	break;
                }
                tableChoice(choice); //case section for choices
        	}        	
        } catch (SQLException e) {
        	System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
        } catch (Exception e) {
        	System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
        } finally {
            controller.close();
        }
    }
    
    public static int selectTableMenu(){
    	int choice = 0;
    	do {
    		System.out.println("What record would you want to edit?");
    		System.out.println("1. Student");
    		System.out.println("2. Class");
    		System.out.println("3. Subject");
    		System.out.println("4. Instructor");
    		System.out.println("Enter the number of your choice:");
            try {
            	choice = Integer.parseInt(kb.nextLine());
            } catch (Exception e) {
            	System.out.println("error: input a valid value...");
            	System.out.print("Press enter key to continue...");
            	kb.nextLine();
            }
        	System.out.println();
    	} while (choice < 1 || choice > 5);
        return choice;
    	}
    
    public static void tableChoice(int table){
    	switch (table) {
    		case 1: //edit student table
    			//crud options for table 1 here
    			break;
    		case 2: //edit class table
    			break;
    		case 3: //edit subject table
    			break;
    		case 4: //edit instructor table
    			break;
    	}
    }
}
