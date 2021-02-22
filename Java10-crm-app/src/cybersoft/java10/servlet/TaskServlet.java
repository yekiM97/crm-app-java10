package cybersoft.java10.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cybersoft.java10.constant.PathConstant;
import cybersoft.java10.constant.UrlConstant;
import cybersoft.java10.container.ContextUltil;
import cybersoft.java10.model.Task;
import cybersoft.java10.model.TaskCategory;
import cybersoft.java10.model.User;
import cybersoft.java10.service.ITaskCategoryService;
import cybersoft.java10.service.ITaskService;

@WebServlet(name = "taskServlet", urlPatterns = { PathConstant.TASK, PathConstant.TASK_ADD, PathConstant.TASK_EDIT,
		PathConstant.TASK_DELETE })
public class TaskServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ITaskService iTaskService;

	private ITaskCategoryService iTaskCategoryService;

	@Override
	public void init() throws ServletException {
		ITaskService iTaskService = (ITaskService) ContextUltil.getContextApp().getBean("iTaskService");
		ITaskCategoryService iTaskCategoryService = (ITaskCategoryService) ContextUltil.getContextApp()
				.getBean("iTaskCategoryService");
		this.setITaskService(iTaskService);
		this.setiTaskCategoryService(iTaskCategoryService);
	}

	public void setiTaskCategoryService(ITaskCategoryService iTaskCategoryService) {
		this.iTaskCategoryService = iTaskCategoryService;
	}

	public void setITaskService(ITaskService iTaskService) {
		this.iTaskService = iTaskService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();

		
		
		switch (action) {
		case PathConstant.TASK:
			List<Task> listTask = iTaskService.getAllTask();
			req.setAttribute("task", listTask);
			req.getRequestDispatcher(UrlConstant.TASK_INDEX).forward(req, resp);
			break;
		case PathConstant.TASK_ADD:
			req.getRequestDispatcher(UrlConstant.TASK_ADD).forward(req, resp);
			break;
		case PathConstant.TASK_EDIT:
			int id = Integer.parseInt(req.getParameter("id"));

			Task task = iTaskService.findByID(id);

			req.setAttribute("Task", task);
			req.getRequestDispatcher(UrlConstant.TASK_EDIT).forward(req, resp);

			break;
		case PathConstant.TASK_DELETE:
			int id1 = Integer.parseInt(req.getParameter("id"));
			Task taskDelete = iTaskService.findByID(id1);
			TaskCategory taskCategoryDelete = iTaskCategoryService.findTask(taskDelete.getName());
			
			if(taskCategoryDelete != null) {
				if (taskCategoryDelete.getTaskID() == taskDelete.getId()) {
					req.setAttribute("message", "Xóa thất bại!");
					req.getRequestDispatcher(UrlConstant.TASK_INDEX).forward(req, resp);
				} else {
					int count = iTaskService.delete(id1);
					if (count > 0)
						resp.sendRedirect(req.getContextPath() + PathConstant.TASK);
					else {
						req.setAttribute("message", "Xóa thất bại!");
						req.getRequestDispatcher(UrlConstant.TASK_INDEX).forward(req, resp);
					}
				}
			}
			else {
				int count = iTaskService.delete(id1);
				if (count > 0)
					resp.sendRedirect(req.getContextPath() + PathConstant.TASK);
				else {
					req.setAttribute("message", "Xóa thất bại!");
					req.getRequestDispatcher(UrlConstant.TASK_INDEX).forward(req, resp);
				}
			}


			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		

		String action = req.getServletPath();
		String name = req.getParameter("name");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		int createUserID = user.getId();
		Task task = new Task(name, startDate, endDate, createUserID);

		switch (action) {

		case PathConstant.TASK_ADD:
			int count = iTaskService.add(task);

			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.TASK);
			else {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.TASK_ADD).forward(req, resp);
			}
			break;
		case PathConstant.TASK_EDIT:

			int id = Integer.parseInt(req.getParameter("id"));
			task.setId(id);
			count = iTaskService.edit(task);
			if (count > 0) {
				resp.sendRedirect(req.getContextPath() + PathConstant.TASK);
			} else {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.TASK_EDIT).forward(req, resp);
			}
			break;

		}
	}
}
