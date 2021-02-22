package cybersoft.java10.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import cybersoft.java10.connection.IDbConnection;
import cybersoft.java10.model.Role;
import cybersoft.java10.model.Task;
import cybersoft.java10.model.TaskCategory;
import cybersoft.java10.model.User;
import cybersoft.java10.repository.ITaskCategoryRepository;

public class TaskCategoryRepositoryImpl implements ITaskCategoryRepository {

	private IDbConnection iDbConnection;

	public void setDbConnection(IDbConnection iDbConnection) {
		this.iDbConnection = iDbConnection;
	}

	@Override
	public List<TaskCategory> getAllTask() {
		// TODO Auto-generated method stub
		String query = "select * from task_category TC, status S, task T, user U where TC.statusID = S.id and TC.taskID = T.id and TC.userID = U.id";
		List<TaskCategory> list = new LinkedList<TaskCategory>();
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			ResultSet result = stateMent.executeQuery();

			while (result.next()) {
				TaskCategory taskCategory = new TaskCategory();
				taskCategory.setId(result.getInt("id"));
				taskCategory.setName(result.getString("name"));
				taskCategory.setStartDate(result.getString("startDate"));
				taskCategory.setEndDate(result.getString("endDate"));
				taskCategory.setStatusID(result.getInt("statusID"));
				taskCategory.setUserID(result.getInt("userID"));
				taskCategory.setTaskID(result.getInt("taskID"));
				taskCategory.setStatusName(result.getString("S.name"));
				taskCategory.setTaskName(result.getString("T.name"));
				taskCategory.setUserName(result.getString("U.fullname"));
				list.add(taskCategory);
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int add(String name, String startDate, String endDate, int statusID, int userID, int taskID) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO task_category(name,startDate,endDate,statusID,userID,taskID) VALUES(?,?,?,?,?,?)";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);
			stateMent.setString(2, startDate);
			stateMent.setString(3, endDate);
			stateMent.setInt(4, statusID);
			stateMent.setInt(5, userID);
			stateMent.setInt(6, taskID);

			kq = stateMent.executeUpdate();
			connection.close();
			return kq;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int edit(int id, String name, String startDate, String endDate, int statusID, int userID, int taskID) {
		// TODO Auto-generated method stub
		String query = "UPDATE task_category SET name = ?, startDate = ?, endDate = ?, statusID = ?, userID = ?, taskID = ? where id = ?";
		int kq = -1;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, name);
			stateMent.setString(2, startDate);
			stateMent.setString(3, endDate);
			stateMent.setInt(4, statusID);
			stateMent.setInt(5, userID);
			stateMent.setInt(6, taskID);
			stateMent.setInt(7, id);

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
		String query = "DELETE FROM task_category WHERE id = ?";
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
	public TaskCategory findByID(int id) {
		// TODO Auto-generated method stub
		TaskCategory taskCategory = null;
		String query = "select * from task_category TC, user U, task T, status S where TC.id = ? and TC.userID = U.id and TC.statusID = S.id and TC.taskID = T.id";
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setInt(1, id);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				taskCategory = new TaskCategory();
				taskCategory.setId(result.getInt("id"));
				taskCategory.setName(result.getString("name"));
				taskCategory.setStartDate(result.getString("startDate"));
				taskCategory.setEndDate(result.getString("endDate"));
				taskCategory.setStatusID(result.getInt("statusID"));
				taskCategory.setUserID(result.getInt("userID"));
				taskCategory.setTaskID(result.getInt("taskID"));
				taskCategory.setStatusName(result.getString("S.name"));
				taskCategory.setTaskName(result.getString("T.name"));
				taskCategory.setUserName(result.getString("U.fullname"));
				break;
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskCategory;
	}

	@Override
	public TaskCategory findTask(String taskName) {
		// TODO Auto-generated method stub
		String query = "Select * From task_category TC,task T where TC.taskID = T.id and T.name = ?";

		TaskCategory taskCategory = null;
		try {
			Connection connection = iDbConnection.getConnection();
			PreparedStatement stateMent = connection.prepareCall(query);
			stateMent.setString(1, taskName);
			ResultSet result = stateMent.executeQuery();
			while (result.next()) {
				taskCategory = new TaskCategory();
				taskCategory.setId(result.getInt("TC.id"));
				taskCategory.setStartDate(result.getString("TC.startDate"));
				taskCategory.setEndDate(result.getString("TC.endDate"));
				taskCategory.setName(result.getString("T.name"));
				taskCategory.setTaskID(result.getInt("T.id"));
				taskCategory.setStatusID(result.getInt("TC.statusID"));
				taskCategory.setUserID(result.getInt("TC.userID"));
				

				break;
			}
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskCategory;
	}

}
