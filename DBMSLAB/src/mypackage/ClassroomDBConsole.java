package mypackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class ClassroomDBConsole {
    private static EskwelaController controller;
    private static Scanner kb = new Scanner(System.in);
    private static int procCRUD;
    
    //MAIN METHOD HERE
    public static void main(String[] args) {
        controller = new EskwelaController();
        try {   
        	int choice = 0;
        	String url = "jdbc:mysql://localhost/eskwelahan?useSSL=false";
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
    
    //MAIN TABLE
    public static int selectTableMenu(){
    	int choice = 0;
    	do {
    		System.out.println("What school record would you like to edit?");
    		System.out.println("1. Classes");
    		System.out.println("2. Instructor Information");
    		System.out.println("3. Student Information");
    		System.out.println("4. Subjects");
    		System.out.println("5. Quit");
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
    /* 
     * Block of case statements for appropriate tables and CRUD operations
     * 
     */
    
    public static void tableChoice(int table){
    	int procCRUD = 0;
    	switch (table) {
    		case 1: //edit respective tables, menu appears, then processes input for CRUD
    			procCRUD = classesMenu();
    			if (procCRUD == 5) { break;}
    			processClass(procCRUD);
    			break;
    		case 2: 
    			procCRUD = instructorMenu();
    			if (procCRUD == 5) { break;}
    			processInst(procCRUD);
    			break;
    		case 3: 
    			procCRUD = studentMenu();
    			if (procCRUD == 5) { break;}
    			processStudent(procCRUD);
    			break;
    		case 4: 
    			procCRUD = subjectMenu();
    			if (procCRUD == 5) { break;}
    			processSubject(procCRUD);
    			break;
    	}
    }
    
    public static void processClass(int procCRUD){
    	switch (procCRUD) {
    		case 1:
    			addClass();
    			break;
    		case 2:
    			viewClass();
    			break;
    		case 3:
    			editClass();
    			break;
    		case 4:
    			//delete
    			deleteClass();
    			break;
    	}
    }
    
    public static void processInst(int procCRUD){
    	switch (procCRUD) {
    		case 1:
    			//create
    			addClass();
    			break;
    		case 2:
    			//retrieve
    			//viewClass();
    			break;
    		case 3:
    			//update
    			//editClass();
    			break;
    		case 4:
    			//delete
    			//deleteClass();
    			break;
    	}
    }
    
    public static void processStudent(int procCRUD){
    	switch (procCRUD) {
    		case 1:
    			//create
    			break;
    		case 2:
    			//retrieve
    			break;
    		case 3:
    			//update
    			break;
    		case 4:
    			//delete
    			break;
    	}
    }
    
    public static void processSubject(int procCRUD){
    	switch (procCRUD) {
    		case 1:
    			//create
    			break;
    		case 2:
    			//retrieve
                	//listSubject();
    			break;
    		case 3:
    			//update
    			break;
    		case 4:
    			//delete
    			break;
    	}
    }
    /* 
     * Block of menu methods for each table
     */
    
    public static int classesMenu() {
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
    			procCRUD = Integer.parseInt(kb.nextLine());
    		} catch (Exception e) {
    			System.out.println("Please select from the options above.");
    			System.out.print("Press any key to continue.");
    			kb.nextLine();
    		}
    		System.out.println();
    	} while (procCRUD < 1 || procCRUD > 5);
    	return procCRUD;
    }
    
    public static int instructorMenu() {
    	do {
    		System.out.println("What would you like to do?");
    		System.out.println();
    		System.out.println("1. Add a new instructor");
    		System.out.println("2. View a list of all instructors");
    		System.out.println("3. Edit instructor information");
    		System.out.println("4. Remove an instructor from the list");
    		System.out.println("5. Go back");
    		System.out.println("Enter choice: ");
    		try {
    			procCRUD = Integer.parseInt(kb.nextLine());
    		} catch (Exception e) {
    			System.out.println("Please select from the options above.");
    			System.out.print("Press any key to continue.");
    			kb.nextLine();
    		}
    		System.out.println();
    	} while (procCRUD < 1 || procCRUD > 5);
    	return procCRUD;
    }
    
    public static int studentMenu() {
    	do {
    		System.out.println("What would you like to do?");
    		System.out.println();
    		System.out.println("1. Add a new student");
    		System.out.println("2. View all students");
    		System.out.println("3. Edit student information");
    		System.out.println("4. Remove a student from the list");
    		System.out.println("5. Go back");
    		System.out.println("Enter choice: ");
    		try {
    			procCRUD = Integer.parseInt(kb.nextLine());
    		} catch (Exception e) {
    			System.out.println("Please select from the options above.");
    			System.out.print("Press any key to continue.");
    			kb.nextLine();
    		}
    		System.out.println();
    	} while (procCRUD < 1 || procCRUD > 5);
    	return procCRUD;
    } 
    
    public static int subjectMenu() {
    	int procCRUD = 0;
    	do {
    		System.out.println("What would you like to do?");
    		System.out.println();
    		System.out.println("1. Add a new subject");
    		System.out.println("2. View a list of all subjects");
    		System.out.println("3. Edit subject information");
    		System.out.println("4. Remove a subject from the list");
    		System.out.println("5. Go back");
    		System.out.println("Enter choice: ");
    		try {
    			procCRUD = Integer.parseInt(kb.nextLine());
    		} catch (Exception e) {
    			System.out.println("Please select from the options above.");
    			System.out.print("Press any key to continue.");
    			kb.nextLine();
    		}
    		System.out.println();
    	} while (procCRUD < 1 || procCRUD > 5);
    	return procCRUD;
    }
    /*
     * Block of methods for CRUD statements
     * 
     */
    //start of class table
    private static void addClass(){
    	printDivider();
    	System.out.print("Enter the classcode: ");
    	String classcode = kb.nextLine();
    	System.out.print("Enter the room where the class is held: ");
    	String room = kb.nextLine();
    	System.out.print("Enter the schedule of the class (days): ");
    	String days = kb.nextLine();
    	System.out.print("Enter the subject id for this class: ");
    	String subjid = kb.nextLine();
    	System.out.print("How many units does this class have: ");
    	int units = kb.nextInt();
    	
    	try {
    		controller.addClass(classcode, room, days, subjid, units);
    		System.out.println(classcode + " " + subjid + " was successfully added to the list of classes.");
    		printDivider();
    		System.out.println();
    	} catch (MySQLIntegrityConstraintViolationException e) {
    		System.out.println("There is already a class with that code within the system. Please try again.");
    		addClass();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    //class table, retrieve data from table
    private static void viewClass() {
    	printDivider();
    	try {
    		ResultSet cls = controller.listClass();
    		if (getResTotal(cls) == 0) {
    			System.out.println("There are currently no classes available.");
    		} else {
    			System.out.println("Current available classes:");
    			System.out.printf("    %-15s %-15s %-15s %-15s %-15s5n","Class Code",
    					"Room","Days","Subject Number", "Units");
    			System.out.println();
    			int row = 1;
    			while (cls.next()) {
    				String ccode = cls.getString(1);
    				String rm = cls.getString(2);
    				String dys = cls.getString(3);
    				String sub = cls.getString(4);
    				int unitNum = cls.getInt(5);
    				System.out.printf("%-4d %-15s %-15s %-15s %-15s %-15s%n", row++, ccode, rm, dys, sub, unitNum);
    			}
    		}
			printDivider();
			System.out.println();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    private static void editClass() {
    	try {
    		viewClass();
			ResultSet cls = controller.listClass();
			if (getResTotal(cls) == 0) {
				System.out.println("No classes are available at this time.");
			}
			System.out.println("Enter the classcode of the class that you wish to edit from the table: ");
			String classcode = kb.nextLine();
			System.out.println("Enter the column that you wish to change");
			String col = kb.nextLine();
			System.out.println("Enter the new value of the item: ");
			String value = kb.nextLine();
			controller.updateClass(classcode, col, value);
			cls = null;
    	} catch (Exception e) {
    		System.err.println("Error: ");
    	}
    }

    public static void deleteClass() {
    	printDivider();
    	try {
    		viewClass();
    		System.out.print("Enter the class code of the class you wish to remove from the list.");
    		String cc = kb.nextLine();
    		controller.deleteClass(cc);
    		System.out.println(cc + " has been removed from the class list.");
    	} catch (Exception e){ 
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    //end of class table
    
    //start of instructor table    
    private static void addInstructor(){
    	printDivider();
    	System.out.print("Enter the Instrutor ID: ");
    	String instruc_id = kb.nextLine();
    	System.out.print("Enter the First Name: ");
    	String first_name = kb.nextLine();
    	System.out.print("Enter the Last Name: ");
    	String last_name = kb.nextLine();
    	System.out.print("Enter the gender(M/F): ");
    	String gender = kb.nextLine();
    	System.out.print("Enter Class Code: ");
    	String classcode = kb.nextLine();
    	
    	try {
    		controller.addInstructor(instruc_id, first_name, last_name, gender, classcode);
    		System.out.println(first_name + " " + last_name + " was successfully added to the list of Instructors.");
    		printDivider();
    		System.out.println();
    	} catch (MySQLIntegrityConstraintViolationException e) {
    		System.out.println("There is already a Instructor with that ID within the system. Please try again.");
    		addClass();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    private static void viewInstructor() {
    	printDivider();
    	try {
    		ResultSet cls = controller.listInstructor();
    		if (getResTotal(cls) == 0) {
    			System.out.println("There are currently no Instructor.");
    		} else {
    			System.out.println("Current Instructor:");
    			System.out.printf("    %-15s %-15s %-15s %-15s %-15s5n","Instructor ID",
    					"First name","Last name","gender", "classcode");
    			System.out.println();
    			int row = 1;
    			while (cls.next()) {
    				String inid = cls.getString(1);
    				String fstname = cls.getString(2);
    				String lstname = cls.getString(3);
    				String gen = cls.getString(4);
    				String cc = cls.getString(5);
    				System.out.printf("%-4d %-15s %-15s %-15s %-15s %-15s%n", row++, inid, fstname, lstname, gen, cc);
    			}
    		}
			printDivider();
			System.out.println();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    private static void editInstructor() {
    	try {
    		viewClass();
			ResultSet cls = controller.listClass();
			if (getResTotal(cls) == 0) {
				System.out.println("No classes are available at this time.");
			}
			System.out.println("Enter the classcode of the class that you wish to edit from the table: ");
			String classcode = kb.nextLine();
			System.out.println("Enter the column that you wish to change");
			String col = kb.nextLine();
			System.out.println("Enter the new value of the item: ");
			String value = kb.nextLine();
			controller.updateClass(classcode, col, value);
			cls = null;
    	} catch (Exception e) {
    		System.err.println("Error: ");
    	}
    }

    public static void deleteInstructor() {
    	printDivider();
    	try {
    		viewClass();
    		System.out.print("Enter the class code of the class you wish to remove from the list.");
    		String cc = kb.nextLine();
    		controller.deleteClass(cc);
    		System.out.println(cc + " has been removed from the class list.");
    	} catch (Exception e){ 
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    
    
    private static void printDivider() {
    	for (int i = 1; i <= 90; i++) {
    		System.out.print("*");
    	}
    	System.out.println();
    }
    
    private static int getResTotal(ResultSet rs) throws Exception {
    	int count = 0;
    	rs.last();
    	count = rs.getRow();
    	rs.beforeFirst();
    	return count;    	
    }
     
}
