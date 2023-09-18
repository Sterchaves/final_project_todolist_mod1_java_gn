package final_project_todolist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {

	private List<Task> tasks = new ArrayList<>(); // List is a type of collection, the <Tasks>  is the variable that is going to be input in the array. The ArrayList use the List type collection.
	private Scanner scanner = new Scanner(System.in);
	
	
	public void viewMenu() {
		int choice;
		
		do {
			System.out.println("**** TO DO LIST ****");
			System.out.println("1. Add Task");
			System.out.println("2. View List");
			System.out.println("3. Run Task");
			System.out.println("4. Exit");
			System.out.println("Select an option: ");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1: 
				addTask(); // functions
				break;
				
			case 2: 
				viewList();
				break;
				
			case 3: 
				runTask();
				break;
				
			case 4:
				System.out.println("Exit");
				break;
			default:
					System.out.println("Try again!");
			}
		}while(choice !=4); // If the option 4 is chosen the while loop pause (Exit). 
	}
	
	private void addTask() {
		scanner.nextLine(); // break line
		System.out.println("Type the task title: ");
		String title = scanner.nextLine();
		System.out.println("Type the task description: ");
		String description = scanner.nextLine();
		System.out.println("The task has deadline (Y/N)? ");
		String answer = scanner.nextLine();
		
		if(answer.equalsIgnoreCase("Y")) { // This String method  will return true if there is "S" in the String and false otherwise; 
			// equalsIgnoreCase: This is a method of the String class in Java. It's used to compare two strings while ignoring their case (uppercase or lowercase). The method checks if the content of the String referred to by answer is the same as the content of the string passed as an argument, regardless of case differences.
			System.out.println("Type the deadline (dd/MM/yyyy): "); 
			String dateInput = scanner.next();
			
			try {
				Date deadline = new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
				// SimpleDateFormat is a class in Java that allows you to format and parse dates using a specified pattern. The pattern "dd/MM/yyyy" specifies the format for the date (day/month/year).
				// .parse input a String in the Date type; Parse is a method of the SimpleDateFormat object. 
				DeadlineTask task = new DeadlineTask(title, description, deadline);
				tasks.add(task); // add the new task in the array list, it is like in Javascript.
				System.out.println("Task with deadline has been added successfully!");
			}catch(Exception e) {
				System.out.println("Invalid");
			}
		} else {
			Task task = new Task(title, description) {
				@Override
				// I call this view() to print that the following task has no deadline.
				public void view() {
					System.out.println("Performing task with no deadline: " + getTitle());
				}
			};
			tasks.add(task);
			System.out.println("Task with no deadline has been added successfully!");
		}
	}
	
	private void viewList() {
		System.out.println("**** TO DO LIST ****");
		
		for(int i =0; i < tasks.size(); i++) { // size() it is like the .length in javascript.
			Task task = tasks.get(i); // It will print the task added according to its index.
			System.out.println((i+1) + ". " + task.getTitle() + " - " + task.getDescription());
			
			// The deadline should be also printed.
			if(task instanceof DeadlineTask) { // It return true if task is a type of Deadline;
				DeadlineTask deadlinetask = (DeadlineTask) task; // the task that will be put in this variable should be only the deadline type.
				System.out.println("Deadline: " + new SimpleDateFormat("dd/MM/yyyy").format(deadlinetask.getDeadline())); // The format(date object) is a method of SimpleDate that says what it should format.
			};
		};
	}
	
	private void runTask() {
		viewList();
		System.out.print("Type the task number to perform: ");
        int taskNumber = scanner.nextInt();
        
        if(taskNumber >= 1 && taskNumber <= tasks.size()) {
        	Task task = tasks.get(taskNumber - 1); // This -1 is to get the index and not the task number.
        	task.view();
        }else {
        	System.out.print("Invalid task number");
        }
	}
	
    public static void main(String[] args) { 
    	// it print the class Menu and the viewMenu() perform the actions.
    	Menu manager = new Menu();
    	manager.viewMenu();
    }
}
