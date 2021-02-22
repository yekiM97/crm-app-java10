package cybersoft.java10.repository;

import java.util.List;

import cybersoft.java10.model.*;

public interface ITaskCategoryRepository {
	List<TaskCategory> getAllTask();
	
	int add(String name, String startDate, String endDate, int statusID, int userID, int taskID);
	 
	int edit(int id, String name, String startDate, String endDate, int statusID, int userID, int taskID);
	
	int delete(int id);
	
	TaskCategory findByID(int id);
	
	TaskCategory findTask(String taskName);
}
