package cybersoft.java10.service;

import java.util.List;

import cybersoft.java10.model.Role;

public interface IRoleService {
	List<Role> getAllRole();

	public int add(Role role);

	int edit(Role role);

	Role findByID(int id);

	int delete(int id);
}
