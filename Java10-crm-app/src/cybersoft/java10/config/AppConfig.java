package cybersoft.java10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import cybersoft.java10.connection.IDbConnection;
import cybersoft.java10.connection.impl.DbConnection;
import cybersoft.java10.repository.IRoleRepository;
import cybersoft.java10.repository.IStatusRepository;
import cybersoft.java10.repository.ITaskCategoryRepository;
import cybersoft.java10.repository.ITaskRepository;
import cybersoft.java10.repository.IUserRepository;
import cybersoft.java10.repository.impl.RoleRepositoryImpl;
import cybersoft.java10.repository.impl.StatusRepositoryImpl;
import cybersoft.java10.repository.impl.TaskCategoryRepositoryImpl;
import cybersoft.java10.repository.impl.TaskRepositoryImpl;
import cybersoft.java10.repository.impl.UserRepositoryImpl;
import cybersoft.java10.service.IRoleService;
import cybersoft.java10.service.IStatusService;
import cybersoft.java10.service.ITaskCategoryService;
import cybersoft.java10.service.ITaskService;
import cybersoft.java10.service.IUserService;
import cybersoft.java10.service.impl.RoleServiceImpl;
import cybersoft.java10.service.impl.StatusServiceImpl;
import cybersoft.java10.service.impl.TaskCategoryServiceImpl;
import cybersoft.java10.service.impl.TaskServiceImpl;
import cybersoft.java10.service.impl.UserServiceImpl;
import cybersoft.java10.servlet.RoleServlet;
import cybersoft.java10.servlet.UserServlet;

public class AppConfig {

	@Bean
	@Scope("prototype")
	public IDbConnection getConnection() {
		return new DbConnection();
	}
	
	@Bean
	@Scope("prototype")
	public IRoleRepository iRoleRepository() {
		RoleRepositoryImpl roleRepositoryImpl = new RoleRepositoryImpl();
		roleRepositoryImpl.setDbConnection(getConnection());
		return roleRepositoryImpl;
	}
	
	
	@Bean
	@Scope("prototype")
	public IUserRepository iUserRepository() {
		UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
		userRepositoryImpl.setDbConnection(getConnection());
		return userRepositoryImpl;
	}
	
	@Bean
	@Scope("prototype")
	public IStatusRepository iStatusRepository() {
		StatusRepositoryImpl statusRepositoryImpl = new StatusRepositoryImpl();
		statusRepositoryImpl.setDbConnection(getConnection());
		return statusRepositoryImpl;
	}
	
	@Bean
	@Scope("prototype")
	public ITaskCategoryRepository iTaskCategory() {
		TaskCategoryRepositoryImpl taskCategoryRepositoryImpl = new TaskCategoryRepositoryImpl();
		taskCategoryRepositoryImpl.setDbConnection(getConnection());
		return taskCategoryRepositoryImpl;
	}
	
	@Bean
	@Scope("prototype")
	public ITaskRepository iTaskRepository() {
		TaskRepositoryImpl taskRepositoryImpl = new TaskRepositoryImpl();
		taskRepositoryImpl.setDbConnection(getConnection());
		return taskRepositoryImpl;
	}
	
	@Bean
	@Scope("prototype")
	public ITaskCategoryService iTaskCategoryService() {
		TaskCategoryServiceImpl taskCategoryServiceImpl = new TaskCategoryServiceImpl();
		taskCategoryServiceImpl.setTaskCategoryRepositoryImpl(iTaskCategory());
		return taskCategoryServiceImpl;
	}
	
	@Bean
	@Scope("prototype")
	public ITaskService iTaskService() {
		TaskServiceImpl taskServiceImpl = new TaskServiceImpl();
		taskServiceImpl.setTaskRepositoryImpl(iTaskRepository());
		return taskServiceImpl;
	}
	
	@Bean
	@Scope("prototype")
	public IStatusService iStatusService() {
		StatusServiceImpl statusServiceImpl = new StatusServiceImpl();
		statusServiceImpl.setStatusRepositoryImpl(iStatusRepository());
		return statusServiceImpl;
	}
	
	@Bean
	@Scope("prototype")
	public IRoleService iRoleService() {
		RoleServiceImpl roleServiceImpl = new RoleServiceImpl();
		roleServiceImpl.setRoleRepository(iRoleRepository());
		return roleServiceImpl;
	}
	
	@Bean
	@Scope("prototype")
	public IUserService iUserService() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setUserRepository(iUserRepository());
		return userServiceImpl;
	}
	
	@Bean
	@Scope("prototype")
	public UserServlet userServlet() {
		return new UserServlet(iUserService(), iRoleService());
	}
}
	
	
