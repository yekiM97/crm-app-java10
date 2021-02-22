package cybersoft.java10.service.impl;

import java.util.List;

import cybersoft.java10.model.TaskCategory;
import cybersoft.java10.repository.ITaskCategoryRepository;
import cybersoft.java10.service.ITaskCategoryService;

public class TaskCategoryServiceImpl implements ITaskCategoryService {

	private ITaskCategoryRepository iTaskCategoryRepository;

	public void setTaskCategoryRepositoryImpl(ITaskCategoryRepository iTaskCategoryRepository) {
		this.iTaskCategoryRepository = iTaskCategoryRepository;
	}

	@Override
	public List<TaskCategory> getAllTask() {
		// TODO Auto-generated method stub
		return iTaskCategoryRepository.getAllTask();
	}

	@Override
	public int add(TaskCategory taskCategory) {
		// TODO Auto-generated method stub
		return iTaskCategoryRepository.add(taskCategory.getName(), taskCategory.getStartDate(),
				taskCategory.getEndDate(), taskCategory.getStatusID(), taskCategory.getUserID(),
				taskCategory.getTaskID());
	}

	@Override
	public int edit(TaskCategory taskCategory) {
		// TODO Auto-generated method stub
		return iTaskCategoryRepository.edit(taskCategory.getId(), taskCategory.getName(), taskCategory.getStartDate(),
				taskCategory.getEndDate(), taskCategory.getStatusID(), taskCategory.getUserID(),
				taskCategory.getTaskID());
	}

	@Override
	public TaskCategory findByID(int id) {
		// TODO Auto-generated method stub
		return iTaskCategoryRepository.findByID(id);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return iTaskCategoryRepository.delete(id);
	}

	@Override
	public TaskCategory findTask(String taskName) {
		// TODO Auto-generated method stub
		return iTaskCategoryRepository.findTask(taskName);
	}

}
