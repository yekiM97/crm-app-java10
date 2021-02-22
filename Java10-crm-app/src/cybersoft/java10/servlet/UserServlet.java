package cybersoft.java10.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cybersoft.java10.config.AppConfig;
import cybersoft.java10.constant.PathConstant;
import cybersoft.java10.constant.UrlConstant;
import cybersoft.java10.container.ContextUltil;
import cybersoft.java10.model.Role;
import cybersoft.java10.model.User;
import cybersoft.java10.service.IRoleService;
import cybersoft.java10.service.IUserService;

@WebServlet(name = "userServlet", urlPatterns = { PathConstant.USER, PathConstant.USER_ADD, PathConstant.USER_EDIT,
		PathConstant.USER_DELETE })
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IUserService iUserService;

	private IRoleService iRoleService;

	@Override
	public void init() throws ServletException {

		IUserService iUserService = (IUserService) ContextUltil.getContextApp().getBean("iUserService");
		IRoleService iRoleService = (IRoleService) ContextUltil.getContextApp().getBean("iRoleService");
		this.setiRoleService(iRoleService);
		this.setiUserService(iUserService);

	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

	public void setiRoleService(IRoleService iRoleService) {
		this.iRoleService = iRoleService;
	}

	public UserServlet() {

	}

	public UserServlet(IUserService iUserService, IRoleService iRoleService) {
		this.iUserService = iUserService;
		this.iRoleService = iRoleService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case PathConstant.USER:
			List<User> listUser = iUserService.getListUser();
			req.setAttribute("User", listUser);
			req.getRequestDispatcher(UrlConstant.USER_INDEX).forward(req, resp);
			break;
		case PathConstant.USER_ADD:
			List<Role> roles = iRoleService.getAllRole();
			req.setAttribute("roles", roles);
			req.getRequestDispatcher(UrlConstant.USER_ADD).forward(req, resp);
			break;
		case PathConstant.USER_EDIT:
			int id = Integer.parseInt(req.getParameter("id"));

			List<Role> role = iRoleService.getAllRole();
			req.setAttribute("roles", role);
			User user = iUserService.findByID(id);
			req.setAttribute("user", user);
			req.getRequestDispatcher(UrlConstant.USER_EDIT).forward(req, resp);
			break;
		case PathConstant.USER_DELETE:
			int id1 = Integer.parseInt(req.getParameter("id"));
			int count = iUserService.deleteUser(id1);
			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.USER);
			else {
				req.setAttribute("message", "Xóa thất bại!");
				req.getRequestDispatcher(UrlConstant.USER_INDEX).forward(req, resp);
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
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		int roleId = Integer.parseInt(req.getParameter("roleId"));

		User user = new User(username, password, email, address, fullname, phone, roleId);

		switch (action) {

		case PathConstant.USER_ADD:
			int count = iUserService.addUser(user);
			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.USER);
			else {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.USER_ADD).forward(req, resp);
			}
			break;
		case PathConstant.USER_EDIT:
			String passwordEdit = req.getParameter("passwordEdit");
			if("".equals(passwordEdit) || passwordEdit == null) {
				int id = Integer.parseInt(req.getParameter("id"));
				user.setId(id);
				count = iUserService.editUser(user);
				if (count > 0)
					resp.sendRedirect(req.getContextPath() + PathConstant.USER);
				else {
					req.setAttribute("message", "Cập nhật thất bại!");
					req.getRequestDispatcher(UrlConstant.USER_EDIT).forward(req, resp);
				}
				
			}
			else
			{
				int id = Integer.parseInt(req.getParameter("id"));
				user.setId(id);
				String hashed = BCrypt.hashpw(passwordEdit, BCrypt.gensalt(12));

				user.setPassword(hashed);
				count = iUserService.editUser(user);
				if (count > 0)
					resp.sendRedirect(req.getContextPath() + PathConstant.USER);
				else {
					req.setAttribute("message", "Cập nhật thất bại!");
					req.getRequestDispatcher(UrlConstant.USER_EDIT).forward(req, resp);
				}
			}
			break;

		}
	}
}