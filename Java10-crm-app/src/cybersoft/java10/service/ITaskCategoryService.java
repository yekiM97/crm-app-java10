package cybersoft.java10.service;

import java.util.List;

import cybersoft.java10.model.TaskCategory;


public interface ITaskCategoryService {
	List<TaskCategory> getAllTask();

	public int add(TaskCategory role);

	int edit(TaskCategory role);

	TaskCategory findByID(int id);

	int delete(int id);
	
	TaskCategory findTask(String taskName);
}
