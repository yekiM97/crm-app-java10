package cybersoft.java10.service.impl;

import java.util.List;

import cybersoft.java10.model.Role;
import cybersoft.java10.repository.IRoleRepository;
import cybersoft.java10.service.IRoleService;

public class RoleServiceImpl implements IRoleService{

	private IRoleRepository iRoleRepository;

	public void setRoleRepository(IRoleRepository iRoleRepository) {
		this.iRoleRepository = iRoleRepository;
	}

	public List<Role> getAllRole() {
		return iRoleRepository.getAllRole();
	}

	public int add(Role role) {
		return iRoleRepository.add(role.getName(), role.getDescription());
	}

	public int edit(Role role) {
		return iRoleRepository.edit(role.getId(), role.getName(), role.getDescription());
	}
	
	public Role findByID(int id) {
		return iRoleRepository.findByID(id);
	}

	public int delete(int id) {
		return iRoleRepository.delete(id);
	}
}
