package cybersoft.java10.repository;

import java.util.List;

import cybersoft.java10.model.Task;

public interface ITaskRepository {
	List<Task> getAllTask();
	
	int add(String name, String startDate, String endDate, int createUserID);
	 
	int edit(int id, String name, String startDate, String endDate, int createUserID);
	
	int delete(int id);
	
	Task findByID(int id);
	
	
}
