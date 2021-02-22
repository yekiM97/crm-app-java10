package cybersoft.java10.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import cybersoft.java10.connection.IDbConnection;
import cybersoft.java10.connection.impl.DbConnection;
import cybersoft.java10.model.User;
import cybersoft.java10.repository.IUserRepository;

public class UserRepositoryImpl implements IUserRepository{
	
	private IDbConnection iDbConnection;

	public void setDbConnection(IDbConnection iDbConnection) {
		this.iDbConnection = iDbConnection;
	}

	public List<User> getAllRole() {
		String query = "select * from user U, role R where U.roleId = R.id";

		List<User> listUser = new LinkedList<User>();

		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setFullname(result.getString("fullname"));
				user.setEmail(result.getString("email"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setAddress(result.getString("address"));
				user.setPhone(result.getString("phone"));
				user.setRoleId(result.getInt("roleId"));
				user.setRole(result.getString("description"));

				listUser.add(user);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUser;
	}

	public int addUser(String username,String password,String email,String address,String fullname,String phone, int roleId) {
		String query = "INSERT INTO user(username,password,email,address,fullname,phone,roleId) VALUES(?,?,?,?,?,?,?)";

		int count = -1;

		try {
			String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1,username);
			stateMent.setString(2,hashed);
			stateMent.setString(3,email);
			stateMent.setString(4,address);
			stateMent.setString(5,fullname);
			stateMent.setString(6,phone);
			stateMent.setInt(7, roleId);
			
			count = stateMent.executeUpdate();
			connection.close();
			return count;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//EDIT
	public User findByID(int id ) {
		String query = "select * from user U, role R where U.roleId = R.id and U.id = ?";
		
		User user = new User();
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setInt(1, id);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				
				
				user.setId(result.getInt("id"));
				user.setFullname(result.getString("fullname"));
				user.setEmail(result.getString("email"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setAddress(result.getString("address"));
				user.setPhone(result.getString("phone"));
				user.setRoleId(result.getInt("roleId"));
				user.setRole(result.getString("description"));
				
				break;
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User findByEmail(String email) {
		String query = "Select * From user U,role R where U.roleId = R.id and U.email = ?";
		
		User acc = null;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, email);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				acc = new User();
				acc.setId(result.getInt("U.id"));
				acc.setFullname(result.getString("U.fullname"));
				acc.setEmail(result.getString("U.email"));
				acc.setUsername(result.getString("U.username"));
				acc.setPassword(result.getString("U.password"));
				acc.setAddress(result.getString("U.address"));
				acc.setPhone(result.getString("U.phone"));
				acc.setRoleId(result.getInt("U.roleId"));
				acc.setRole(result.getString("R.description"));
				acc.setRoleName(result.getString("R.name"));
				break;
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return acc;
	}
	
	public int editUser(int id,String username,String password,String email,String address,String fullname,String phone, int roleId) {
		String query = "UPDATE user SET username = ?, password = ?, email = ?, address = ?, fullname = ?, phone = ?, roleId = ? where id = ?";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, username);
			stateMent.setString(2, password);
			stateMent.setString(3, email);
			stateMent.setString(4, address);
			stateMent.setString(5, fullname);
			stateMent.setString(6, phone);
			stateMent.setInt(7, roleId);
			stateMent.setInt(8, id);


			kq = stateMent.executeUpdate();
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	public List<User> getRole(int id){
		String query = "select * from user U, role R where U.roleId = R.id and U.id = ?";
		List<User> listUser = new LinkedList<User>();
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setInt(1, id);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				User user = new User();
				user.setId(result.getInt("id"));
				user.setRoleId(result.getInt("roleId"));
				user.setRole(result.getString("description"));

				listUser.add(user);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listUser;
	}
	
	public int deleteUser(int id) {
		String query = "DELETE FROM user WHERE id = ? ";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setInt(1, id);

			kq = stateMent.executeUpdate();
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

}
