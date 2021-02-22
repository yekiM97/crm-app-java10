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
import cybersoft.java10.model.Role;
import cybersoft.java10.model.Status;
import cybersoft.java10.model.Task;
import cybersoft.java10.model.TaskCategory;
import cybersoft.java10.model.User;
import cybersoft.java10.service.IStatusService;
import cybersoft.java10.service.ITaskCategoryService;
import cybersoft.java10.service.ITaskService;
import cybersoft.java10.service.IUserService;

@WebServlet(name = "taskCategoryServlet", urlPatterns = { PathConstant.TASKCATEGORY, PathConstant.TASKCATEGORY_ADD,
		PathConstant.TASKCATEGORY_EDIT, PathConstant.TASKCATEGORY_DELETE })
public class TaskCategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ITaskCategoryService iTaskCategoryService;

	private ITaskService iTaskService;

	private IStatusService iStatusService;

	private IUserService iUserService;

	@Override
	public void init() throws ServletException {
		ITaskCategoryService iTaskCategoryService = (ITaskCategoryService) ContextUltil.getContextApp()
				.getBean("iTaskCategoryService");
		ITaskService iTaskService = (ITaskService) ContextUltil.getContextApp().getBean("iTaskService");
		IStatusService iStatusService = (IStatusService) ContextUltil.getContextApp().getBean("iStatusService");
		IUserService iUserService = (IUserService) ContextUltil.getContextApp().getBean("iUserService");
		this.setiTaskCategoryService(iTaskCategoryService);
		this.setiTaskService(iTaskService);
		this.setiStatusService(iStatusService);
		this.setiUserService(iUserService);
	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

	public void setiStatusService(IStatusService iStatusService) {
		this.iStatusService = iStatusService;
	}

	public void setiTaskService(ITaskService iTaskService) {
		this.iTaskService = iTaskService;
	}

	public void setiTaskCategoryService(ITaskCategoryService iTaskCategoryService) {
		this.iTaskCategoryService = iTaskCategoryService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		List<Task> listTask = iTaskService.getAllTask();
		List<Status> listStatus = iStatusService.getListStatus();
		List<User> listUser = iUserService.getListUser();

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		switch (action) {
		case PathConstant.TASKCATEGORY:
			List<TaskCategory> listTaskCategory = iTaskCategoryService.getAllTask();
			req.setAttribute("taskCategory", listTaskCategory);
			req.getRequestDispatcher(UrlConstant.TASKCATEGORY_INDEX).forward(req, resp);
			break;
		case PathConstant.TASKCATEGORY_ADD:
			req.setAttribute("listUser", listUser);
			req.setAttribute("listTask", listTask);
			req.setAttribute("listStatus", listStatus);
			req.getRequestDispatcher(UrlConstant.TASKCATEGORY_ADD).forward(req, resp);
			break;
		case PathConstant.TASKCATEGORY_EDIT:
			int id = Integer.parseInt(req.getParameter("id"));

			TaskCategory TaskCategory = iTaskCategoryService.findByID(id);

			if ((user.getId() == TaskCategory.getUserID()) || user.getRoleId() == 1 || user.getRoleId() == 2) {
				req.setAttribute("listUserEdit", listUser);
				req.setAttribute("listTaskEdit", listTask);
				req.setAttribute("listStatusEdit", listStatus);

				req.setAttribute("TaskCategory", TaskCategory);
				req.getRequestDispatcher(UrlConstant.TASKCATEGORY_EDIT).forward(req, resp);

			} else {
				req.setAttribute("message", "Bạn không được phép chỉnh sửa dự án của người khác!");
				req.getRequestDispatcher(UrlConstant.TASKCATEGORY_INDEX).forward(req, resp);
			}

			break;
		case PathConstant.TASKCATEGORY_DELETE:
			int id1 = Integer.parseInt(req.getParameter("id"));
			int count = iTaskCategoryService.delete(id1);
			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.TASKCATEGORY);
			else {
				req.setAttribute("message", "Xóa thất bại!");
				req.getRequestDispatcher(UrlConstant.TASKCATEGORY_INDEX).forward(req, resp);
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

		String action = req.getServletPath();
		String name = req.getParameter("name");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");

		int userID = Integer.parseInt(req.getParameter("userID"));
		int taskID = Integer.parseInt(req.getParameter("taskID"));
		int statusID = Integer.parseInt(req.getParameter("statusID"));

		TaskCategory taskCategory = new TaskCategory(name, startDate, endDate, statusID, userID, taskID);

		switch (action) {

		case PathConstant.TASKCATEGORY_ADD:
			int count = iTaskCategoryService.add(taskCategory);

			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.TASKCATEGORY);
			else {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.TASKCATEGORY_ADD).forward(req, resp);
			}
			break;
		case PathConstant.TASKCATEGORY_EDIT:

			int id = Integer.parseInt(req.getParameter("id"));

			taskCategory.setId(id);
			count = iTaskCategoryService.edit(taskCategory);
			if (count > 0) {
				resp.sendRedirect(req.getContextPath() + PathConstant.TASKCATEGORY);
			} else {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.TASKCATEGORY_EDIT).forward(req, resp);
			}
			break;

		}
	}
}
