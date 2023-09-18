package final_project_todolist;

public abstract class Task {

	private String title; 
	private String description; 
	
	public Task(String title, String description) {
		this.title = title;
		this.description = description;
		
	}
	
	public abstract void view();
	
	public String getTitle() {
		return title;
	};
	
	public String getDescription() {
		return description;
	};
	
	// Why to apply only the getters here? 
	// Answer: Control Over Modifications: By not providing setters, you have control over how the title and description are set. You might want to enforce certain rules or validations when setting these values, which can be done within the constructor.
}
