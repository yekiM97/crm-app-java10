package cybersoft.java10.repository;

import java.util.List;

import cybersoft.java10.model.Role;


public interface IRoleRepository {

	List<Role> getAllRole();
	
	int add(String name, String description);
	
	int edit(int id, String name, String description);
	
	Role findByID(int id);
	
	int delete(int id);
}
