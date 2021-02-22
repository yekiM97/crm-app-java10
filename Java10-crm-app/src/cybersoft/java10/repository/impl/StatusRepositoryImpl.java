package cybersoft.java10.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java10.connection.IDbConnection;
import cybersoft.java10.model.Role;
import cybersoft.java10.model.Status;
import cybersoft.java10.repository.IStatusRepository;

public class StatusRepositoryImpl implements IStatusRepository{

	private IDbConnection iDbConnection;

	public void setDbConnection(IDbConnection iDbConnection) {
		this.iDbConnection = iDbConnection;
	}
	@Override
	public List<Status> getListStatus() {
		// TODO Auto-generated method stub
		List<Status> listStatus = new LinkedList<Status>();

		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall("select * from status");
			ResultSet result = stateMent.executeQuery();
			
			while (result.next()) {
				Status status = new Status();
				status.setId(result.getInt("id"));
				status.setName(result.getString("name"));

				listStatus.add(status);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listStatus;
	}

	@Override
	public int add(String name) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO status(name) VALUES(?)";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);

			kq = stateMent.executeUpdate();
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int edit(int id, String name) {
		// TODO Auto-generated method stub
		String query = "UPDATE status SET name = ? where id = ?";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);
			stateMent.setInt(2, id);

			kq = stateMent.executeUpdate();
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM status WHERE id = ?";
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
	@Override
	public Status findByID(int id) {
		// TODO Auto-generated method stub
		Status status = null;
		String query = "select * from status where id = ?";
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setInt(1, id);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				status = new Status();
				status.setId(result.getInt("id"));
				status.setName(result.getString("name"));
				break;
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
