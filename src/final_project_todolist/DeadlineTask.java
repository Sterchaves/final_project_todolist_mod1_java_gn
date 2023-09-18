package final_project_todolist;

import java.util.Date;

// Why to use Date util java import to use the Date type?
// Answer: It is usually use to represent and manipulate Dates/Time; 

public class DeadlineTask extends Task{

	private Date deadline;
	
	public DeadlineTask(String title, String description, Date deadline) {
		super(title, description);
		this.deadline = deadline;
	};
	
	@Override
	public void view() {
		System.out.println("Performing the task: " + getTitle());
	};
	
	public Date getDeadline() {
		return deadline;
	};
}
