package cybersoft.java10.service.impl;

import java.util.List;

import cybersoft.java10.model.User;
import cybersoft.java10.repository.IUserRepository;
import cybersoft.java10.service.IUserService;


public class UserServiceImpl implements IUserService{
private IUserRepository iUserRepository;
	
	public void setUserRepository(IUserRepository iUserRepository) {
		this.iUserRepository =iUserRepository;
	}
	
	public List<User> getListUser(){
		return iUserRepository.getAllRole();
	}
	
	public int addUser(User user) {
		return iUserRepository.addUser(user.getUsername(),user.getPassword(),user.getEmail(),user.getAddress(),user.getFullname(),user.getPhone(),user.getRoleId());
	}
	
	public User findByID(int id) {
		return iUserRepository.findByID(id);
	}
	
	public int editUser(User user) {
		
		return iUserRepository.editUser(user.getId(),user.getUsername(),user.getPassword(),user.getEmail(),user.getAddress(),user.getFullname(),user.getPhone(),user.getRoleId());

	}
	
	public List<User> getRole(int id){
		return iUserRepository.getRole(id);
	}
	
	public int deleteUser(int id) {
		return iUserRepository.deleteUser(id);
	}
	
	public User findByEmail(String password) {
		return iUserRepository.findByEmail(password);
	}
}
