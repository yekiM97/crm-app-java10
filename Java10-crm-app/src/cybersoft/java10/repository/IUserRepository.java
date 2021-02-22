package cybersoft.java10.repository;

import java.util.List;

import cybersoft.java10.model.User;


public interface IUserRepository {
	List<User> getAllRole();
	
	int addUser(String username,String password,String email,String address,String fullname,String phone, int roleId);
	
	User findByID(int id );
	
	User findByEmail(String email);
	
	int editUser(int id,String username,String password,String email,String address,String fullname,String phone, int roleId);
	
	List<User> getRole(int id);
	
	int deleteUser(int id);
}
