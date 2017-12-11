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
                choice = selectTableMenu(); //select a table to edit
                if (choice == 5) {
                	System.out.println("This program will now exit.");
                	break;
                }
                tableChoice(choice); //case section for table choices
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
    		System.out.println("What school record would you like to edit?");
    		System.out.println("1. Classes");
    		System.out.println("2. Instructor Information");
    		System.out.println("3. Student Information");
    		System.out.println("4. Student's Class Info");
    		System.out.println("5. Quit")
    		System.out.println("Enter the number of your choice:");
            try {
            	choice = Integer.parseInt(kb.nextLine());
            } catch (Exception e) {
            	System.out.println("Error. Please select from the options above.");
            	System.out.print("Press any key to continue.");
            	kb.nextLine();
            }
        	System.out.println();
    	} while (choice < 1 || choice > 5);
        return choice;
    	}
    
    public static void tableChoice(int table){
    	switch (table) {
    		case 1: //edit classes table
    			//crud options for table 1 here
    			classesMenu();
    			break;
    		case 2: //edit class table
    			break;
    		case 3: //edit subject table
    			break;
    		case 4: //edit instructor table
    			break;
    	}
    }
    
    public static int classesMenu() {
    	int classCRUD = 0;
    	do {
    		System.out.println("What would you like to do?");
    		System.out.println();
    		System.out.println("1. Add a new class");
    		System.out.println("2. View all available classes");
    		System.out.println("3. Edit class information");
    		System.out.println("4. Remove a class from the list");
    		System.out.println("5. Go back");
    		System.out.println("Enter choice: ");
    		try {
    			classCrud = Integer.parseInt(kb.nextLine());
    		} catch (Exception e) {
    			System.out.println("Please select from the options above.");
    			System.out.print("Press any key to continue.");
    			kb.nextLine();
    		}
    		System.out.println();
    	} while (classCRUD < 1 || classCRUD > 5);
    	return classCRUD;
    }
    
    
}
