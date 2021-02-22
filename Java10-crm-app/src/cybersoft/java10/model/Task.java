package cybersoft.java10.model;

public class Task {
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private int createUserID;
	
	
	
	
	public Task() {
		super();
	}
	public Task(int id, String name, String startDate, String endDate) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Task(String name, String startDate, String endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Task(String name, String startDate, String endDate, int createUserID) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createUserID = createUserID;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getCreateUserID() {
		return createUserID;
	}
	public void setCreateUserID(int createUserID) {
		this.createUserID = createUserID;
	}
	
	
	
}
