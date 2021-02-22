package cybersoft.java10.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java10.connection.IDbConnection;
import cybersoft.java10.model.Task;
import cybersoft.java10.model.TaskCategory;
import cybersoft.java10.repository.ITaskRepository;

public class TaskRepositoryImpl implements ITaskRepository{
	private IDbConnection iDbConnection;

	public void setDbConnection(IDbConnection iDbConnection) {
		this.iDbConnection = iDbConnection;
	}
	@Override
	public List<Task> getAllTask() {
		// TODO Auto-generated method stub
		List<Task> list = new LinkedList<Task>();

		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall("select * from task");
			ResultSet result = stateMent.executeQuery();
			
			while (result.next()) {
				Task task = new Task();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setStartDate(result.getString("startDate"));
				task.setEndDate(result.getString("endDate"));
				task.setCreateUserID(result.getInt("createUserID"));

				list.add(task);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int add(String name, String startDate, String endDate, int createUserID) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO task(name,startDate,endDate,createUserID) VALUES(?,?,?,?)";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);
			stateMent.setString(2, startDate);
			stateMent.setString(3, endDate);
			stateMent.setInt(4, createUserID);
			kq = stateMent.executeUpdate();
			
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int edit(int id, String name, String startDate, String endDate, int createUserID) {
		// TODO Auto-generated method stub
		String query = "UPDATE task SET name = ?, startDate = ?, endDate = ? where id = ?";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);
			stateMent.setString(2, startDate);
			stateMent.setString(3, endDate);
			stateMent.setInt(4, id);


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
		String query = "DELETE FROM task WHERE id = ?";
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
	public Task findByID(int id) {
		// TODO Auto-generated method stub
		Task task = null;
		String query = "select * from task where id = ?";
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setInt(1, id);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				task = new Task();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setStartDate(result.getString("startDate"));
				task.setEndDate(result.getString("endDate"));
				task.setCreateUserID(result.getInt("createUserID"));

				break;
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

}
