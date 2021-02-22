package cybersoft.java10.service;

import java.util.List;

import cybersoft.java10.model.User;

public interface IUserService {
	List<User> getListUser();

	int addUser(User user);

	User findByID(int id);

	int editUser(User user);

	List<User> getRole(int id);

	int deleteUser(int id);

	User findByEmail(String password);
}
