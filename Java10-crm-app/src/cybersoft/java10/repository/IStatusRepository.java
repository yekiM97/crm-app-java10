package cybersoft.java10.repository;

import java.util.List;

import cybersoft.java10.model.Status;

public interface IStatusRepository {
	List<Status> getListStatus();
	
	int add(String name);
	
	int edit(int id, String name);
	
	int delete(int id);
	
	Status findByID(int id);
}
