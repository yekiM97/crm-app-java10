package cybersoft.java10.service;

import java.util.List;

import cybersoft.java10.model.Task;

public interface ITaskService {
	List<Task> getAllTask();

	public int add(Task role);

	int edit(Task role);

	Task findByID(int id);

	int delete(int id);
}
