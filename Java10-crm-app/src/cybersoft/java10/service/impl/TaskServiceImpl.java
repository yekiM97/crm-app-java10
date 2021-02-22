package cybersoft.java10.service.impl;

import java.util.List;

import cybersoft.java10.model.Task;
import cybersoft.java10.repository.ITaskRepository;
import cybersoft.java10.service.ITaskService;

public class TaskServiceImpl implements ITaskService {
	private ITaskRepository iTaskRepository;

	public void setTaskRepositoryImpl(ITaskRepository iTaskRepository) {
		this.iTaskRepository = iTaskRepository;
	}

	@Override
	public List<Task> getAllTask() {
		// TODO Auto-generated method stub
		return iTaskRepository.getAllTask();
	}

	@Override
	public int add(Task task) {
		// TODO Auto-generated method stub
		return iTaskRepository.add(task.getName(), task.getStartDate(), task.getEndDate(),task.getCreateUserID());
	}

	@Override
	public int edit(Task task) {
		// TODO Auto-generated method stub
		return iTaskRepository.edit(task.getId(), task.getName(), task.getStartDate(), task.getEndDate(),task.getCreateUserID());
	}

	@Override
	public Task findByID(int id) {
		// TODO Auto-generated method stub
		return iTaskRepository.findByID(id);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return iTaskRepository.delete(id);
	}

}
