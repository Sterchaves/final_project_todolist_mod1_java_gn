package final_project_todolist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

	private List<Task> tasks = new ArrayList<>(); 
	private Scanner scanner = new Scanner(System.in);
	
	
	public void viewMenu() {
		int choice;
		
		    do {
		        try {
		            System.out.println("**** TO DO LIST ****");
		            System.out.println("1. Add Task");
		            System.out.println("2. View List");
		            System.out.println("3. Run Task");
		            System.out.println("4. Exit");
		            System.out.print("Select an option: ");

		            choice = scanner.nextInt();

		            switch (choice) {
		                case 1:
		                    addTask();
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
		                    System.out.println("Invalid choice. Please try again.");
		            }
		        } catch (InputMismatchException e) {
		            System.out.println("Invalid");
		            scanner.next();  
		            choice = -1; 
		        }
		    } while (choice != 4);  
	}
	
	private void addTask() {
		scanner.nextLine(); // breaker
		System.out.println("Type the task title: ");
		String title = scanner.nextLine();
		System.out.println("Type the task description: ");
		String description = scanner.nextLine();
		System.out.println("Has the task a deadline (Y/N)? ");
		String answer = scanner.nextLine();
		
		if(answer.equalsIgnoreCase("Y")) { 
			System.out.println("Type the deadline (dd/MM/yyyy): "); 
			String dateInput = scanner.next();
			
			try {
				Date deadline = new SimpleDateFormat("dd/MM/yyyy").parse(dateInput);
				DeadlineTask task = new DeadlineTask(title, description, deadline);
				tasks.add(task); 
				System.out.println("Task with deadline has been added successfully!");
			}catch(Exception e) {
				System.out.println("Invalid");
			}
		} else {
			Task task = new Task(title, description) {
				@Override
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
		    System.out.println("1. View Tasks with Deadlines");
		    System.out.println("2. View Tasks without Deadlines");
		    System.out.println("3. View All Tasks");
		    System.out.println("Select an option: ");

		    int choice = scanner.nextInt();
		    List<Task> selectedTasks;  

		    switch (choice) {
		        case 1:
		            selectedTasks = tasks.stream()
		                    .filter(task -> task instanceof DeadlineTask)  
		                    .collect(Collectors.toList());
		            break;

		        case 2:
		            selectedTasks = tasks.stream()
		                    .filter(task -> !(task instanceof DeadlineTask))
		                    .collect(Collectors.toList());
		            break;

		        case 3:
		            selectedTasks = tasks;
		            break;

		        default:
		            System.out.println("Invalid option.");
		            return;
		    }

		    for (int i = 0; i < selectedTasks.size(); i++) { 
		        Task task = selectedTasks.get(i); 
		        System.out.println((i + 1) + ". " + task.getTitle() + " - " + task.getDescription());

		        if (task instanceof DeadlineTask) { 
		            DeadlineTask deadlineTask = (DeadlineTask) task; 
		            System.out.println("Deadline: " + new SimpleDateFormat("dd/MM/yyyy").format(deadlineTask.getDeadline())); 
		        }
		    }
	}
	
	private void runTask() {
		viewList();
		System.out.print("Type the task number to perform: ");
        int taskNumber = scanner.nextInt();
        
        if(taskNumber >= 1 && taskNumber <= tasks.size()) {
        	Task task = tasks.get(taskNumber - 1); 
        	task.view(); 
        }else {
        	System.out.print("Invalid task number");
        }
	}
	
    public static void main(String[] args) { 
    	Menu manager = new Menu();
    	manager.viewMenu();
    }
}
