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
    			addInstructor();
    			break;
    		case 2:
    			viewInstructor();
    			break;
    		case 3:
    			editInstructor();
    			break;
    		case 4:
    			deleteInstructor();
    			break;
    	}
    }
    
    public static void processStudent(int procCRUD){
    	switch (procCRUD) {
    		case 1:
    			addStudent();
    			break;
    		case 2:
    			viewStudent();
    			break;
    		case 3:
    			editStudent();
    			break;
    		case 4:
    			deleteStudent();
    			break;
    	}
    }
    
    public static void processSubject(int procCRUD){
    	switch (procCRUD) {
    		case 1:
    			addSubject();
    			break;
    		case 2:
    			viewSubject();
    			break;
    		case 3:
    			editSubject();
    			break;
    		case 4:
    			deleteSubject();
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

    private static void viewClass() {
    	printDivider();
    	try {
    		ResultSet cls = controller.listClass();
    		if (getResTotal(cls) == 0) {
    			System.out.println("There are currently no classes available.");
    		} else {
    			System.out.println("Current available classes:");
    			System.out.printf("    %-15s %-15s %-15s %-15s %-15s","classcode",
    					"Room","Day","subjid", "Units");
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
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
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
    			System.out.printf("    %-15s %-15s %-15s %-15s %-15s","instruc_id",
    					"First_name","Last_name","gender", "classcode");
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
    		viewInstructor();
			ResultSet cls = controller.listInstructor();
			if (getResTotal(cls) == 0) {
				System.out.println("No classes are available at this time.");
			}
			System.out.println("Enter the Instructor ID of the Instructor that you wish to edit from the table: ");
			int instruc_id = Integer.parseInt(kb.nextLine());
			System.out.println("Enter the column that you wish to change");
			String col = kb.nextLine();
			System.out.println("Enter the new value of the item: ");
			String value = kb.nextLine();
			controller.updateInstructor(instruc_id, col, value);
			cls = null;
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }

    public static void deleteInstructor() {
    	printDivider();
    	try {
    		viewInstructor();
    		System.out.print("Enter the Instructor ID of the instructor you wish to remove from the list.");
    		int insid = Integer.parseInt(kb.nextLine());
    		controller.deleteInstructor(insid);
    		System.out.println(insid + " has been removed from the instructor list.");
    	} catch (Exception e){ 
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    //end of instructor table
    
  //start of student table    
    private static void addStudent(){
    	printDivider();
    	System.out.print("Enter the ID Number: ");
    	int stud_id = Integer.parseInt(kb.nextLine());
    	System.out.print("Enter the First Name: ");
    	String first_name = kb.nextLine();
    	System.out.print("Enter the Last Name: ");
    	String last_name = kb.nextLine();
    	System.out.print("Enter the address: ");
    	String address = kb.nextLine();
    	System.out.print("Enter the gender(M/F): ");
    	String gender = kb.nextLine();
    	System.out.print("Enter course: ");
    	String course = kb.nextLine();
    	System.out.print("Enter year: ");
    	int year = Integer.parseInt(kb.nextLine());
    	
    	try {
    		controller.addStudent(stud_id, first_name, last_name, address, gender, course, year);
    		System.out.println(first_name + " " + last_name + " was successfully added to the list of Student.");
    		printDivider();
    		System.out.println();
    	} catch (MySQLIntegrityConstraintViolationException e) {
    		System.out.println("There is already a student with that ID within the system. Please try again.");
    		addClass();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    private static void viewStudent() {
    	printDivider();
    	try {
    		ResultSet cls = controller.listStudent();
    		if (getResTotal(cls) == 0) {
    			System.out.println("There are currently no Student.");
    		} else {
    			System.out.println("Current Instructor:");
    			System.out.printf("    %-15s %-15s %-15s %-15s %-15s %-15s %-15s","stud_id",
    					"First_name" ,"Last_name" ,"address" ,"gender" , "course", "year");
    			System.out.println();
    			int row = 1;
    			while (cls.next()) {
    				String stid = cls.getString(1);
    				String fstname = cls.getString(2);
    				String lstname = cls.getString(3);
    				String address = cls.getString(4);
    				String gen = cls.getString(5);
    				String course = cls.getString(6);
    				String year = cls.getString(7);
    				System.out.printf("%-4d %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", row++, stid, fstname, lstname,address, gen, course, year);
    			}
    		}
			printDivider();
			System.out.println();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    private static void editStudent() {
    	try {
    		viewStudent();
			ResultSet cls = controller.listStudent();
			if (getResTotal(cls) == 0) {
				System.out.println("No students at this time.");
			}
			System.out.println("Enter the student ID of the student that you wish to edit from the table: ");
			int stud_id = Integer.parseInt(kb.nextLine());
			System.out.println("Enter the column that you wish to change");
			String col = kb.nextLine();
			System.out.println("Enter the new value of the item: ");
			String value = kb.nextLine();
			controller.updateStudent(stud_id, col, value);
			cls = null;
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }

    public static void deleteStudent() {
    	printDivider();
    	try {
    		viewStudent();
    		System.out.print("Enter the Student ID of the Student you wish to remove from the list.");
    		int stid = Integer.parseInt(kb.nextLine());
    		controller.deleteStudent(stid);
    		System.out.println(stid + " has been removed from the student list.");
    	} catch (Exception e){ 
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    //end of student table
    
    //start of Subject table    
    private static void addSubject(){
    	printDivider();
    	System.out.print("Enter the Subject ID: ");
    	String subj_id = kb.nextLine();
    	System.out.print("Enter the desc: ");
    	String desc = kb.nextLine();
    	System.out.print("Enter how many units: ");
    	int units = Integer.parseInt(kb.nextLine());
    	System.out.print("Enter the instructor ID: ");
    	int instuc_id = Integer.parseInt(kb.nextLine());
    	
    	try {
    		controller.addSubject(subj_id, desc, units, instuc_id);
    		System.out.println(subj_id + " " + units + " was successfully added to the list of Suject.");
    		printDivider();
    		System.out.println();
    	} catch (MySQLIntegrityConstraintViolationException e) {
    		System.out.println("There is already a Subject with that ID within the system. Please try again.");
    		addSubject();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    private static void viewSubject() {
    	printDivider();
    	try {
    		ResultSet cls = controller.listSubject();
    		if (getResTotal(cls) == 0) {
    			System.out.println("There are currently no Subject.");
    		} else {
    			System.out.println("Current Subject:");
    			System.out.printf("    %-15s %-15s %-15s %-15s","subj_id",
    					"desc","units","instruc_id");
    			System.out.println();
    			int row = 1;
    			while (cls.next()) {
    				String inid = cls.getString(1);
    				String fstname = cls.getString(2);
    				String lstname = cls.getString(3);
    				String gen = cls.getString(4);
    				System.out.printf("%-4d %-15s %-15s %-15s %-15s%n", row++, inid, fstname, lstname, gen);
    			}
    		}
			printDivider();
			System.out.println();
    	} catch (Exception e) {
    		System.err.println("Error" + e.getClass() + ": \n" + e.getMessage());
    	}
    }
    
    private static void editSubject() {
    	try {
    		viewInstructor();
			ResultSet cls = controller.listSubject();
			if (getResTotal(cls) == 0) {
				System.out.println("No Subject are available at this time.");
			}
			System.out.println("Enter the Subject ID of the Subject that you wish to edit from the table: ");
			String subj_id = kb.nextLine();
			System.out.println("Enter the column that you wish to change");
			String col = kb.nextLine();
			System.out.println("Enter the new value of the item: ");
			String value = kb.nextLine();
			controller.updateSubject(subj_id, col, value);
			cls = null;
    	} catch (Exception e) {
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }

    public static void deleteSubject() {
    	printDivider();
    	try {
    		viewSubject();
    		System.out.print("Enter the Subject ID of the subject you wish to remove from the list.");
    		String subj_id = kb.nextLine();
    		controller.deleteSubject(subj_id);
    		System.out.println(subj_id + " has been removed from the subject list.");
    	} catch (Exception e){ 
    		System.err.println("error: " + e.getClass() + "\n" + e.getMessage());
    	}
    }
    //end of subject table
    
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
