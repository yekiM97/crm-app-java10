package cybersoft.java10.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java10.connection.IDbConnection;
import cybersoft.java10.model.Role;
import cybersoft.java10.repository.IRoleRepository;


public class RoleRepositoryImpl implements IRoleRepository{
	
	private IDbConnection iDbConnection;

	public void setDbConnection(IDbConnection iDbConnection) {
		this.iDbConnection = iDbConnection;
	}

	public List<Role> getAllRole() {

		List<Role> listRole = new LinkedList<Role>();

		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall("select * from role");
			ResultSet result = stateMent.executeQuery();
			
			while (result.next()) {
				Role role = new Role();
				role.setId(result.getInt("id"));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));

				listRole.add(role);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRole;
	}

	public int add(String name, String description) {
		String query = "INSERT INTO role(name,description) VALUES(?,?)";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);
			stateMent.setString(2, description);

			kq = stateMent.executeUpdate();
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	public int edit(int id, String name, String description) {
		String query = "UPDATE role SET name = ?, description = ? where id = ?";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);
			stateMent.setString(2, description);
			stateMent.setInt(3, id);

			kq = stateMent.executeUpdate();
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public Role findByID(int id) {
		Role role = null;
		String query = "select * from role where id = ?";
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setInt(1, id);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				role = new Role();
				role.setId(result.getInt("id"));
				role.setName(result.getString("name"));
				role.setDescription(result.getString("description"));
				break;
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}
	
	public int delete(int id) {
		String query = "DELETE FROM role WHERE id = ?";
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
