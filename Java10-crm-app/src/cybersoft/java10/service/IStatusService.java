package cybersoft.java10.service;

import java.util.List;

import cybersoft.java10.model.Status;

public interface IStatusService {
	List<Status> getListStatus();

	public int add(Status status);

	int edit(Status status);

	Status findByID(int id);

	int delete(int id);
}
