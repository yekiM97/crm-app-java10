package cybersoft.java10.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java10.constant.PathConstant;
import cybersoft.java10.constant.UrlConstant;
import cybersoft.java10.container.ContextUltil;
import cybersoft.java10.model.Role;
import cybersoft.java10.service.IRoleService;
import cybersoft.java10.service.IUserService;

@WebServlet(name = "roleServlet", urlPatterns = { PathConstant.ROLE, PathConstant.ROLE_ADD, PathConstant.ROLE_EDIT, PathConstant.ROLE_DELETE })
public class RoleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IRoleService iRoleService;

	@Override
	public void init() throws ServletException {
		IRoleService iRoleService = (IRoleService) ContextUltil.getContextApp().getBean("iRoleService");
		this.setiRoleService(iRoleService);
	}

	
	public IRoleService getiRoleService() {
		return iRoleService;
	}


	public void setiRoleService(IRoleService iRoleService) {
		this.iRoleService = iRoleService;
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();

		switch (action) {
		case PathConstant.ROLE:
			List<Role> listRole = iRoleService.getAllRole();
			req.setAttribute("Role", listRole);
			req.getRequestDispatcher(UrlConstant.ROLE_INDEX).forward(req, resp);
			break;
		case PathConstant.ROLE_ADD:
			req.getRequestDispatcher(UrlConstant.ROLE_ADD).forward(req, resp);
			break;
		case PathConstant.ROLE_EDIT:
			int id = Integer.parseInt(req.getParameter("id"));

			Role role = iRoleService.findByID(id);

			req.setAttribute("role", role);
			req.getRequestDispatcher(UrlConstant.ROLE_EDIT).forward(req, resp);

			break;
		case PathConstant.ROLE_DELETE:
			int id1 = Integer.parseInt(req.getParameter("id"));
			int count = iRoleService.delete(id1);
			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.ROLE);
			else
			{
				req.setAttribute("message", "Xóa thất bại!");
				req.getRequestDispatcher(UrlConstant.ROLE_INDEX).forward(req, resp);
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
		String description = req.getParameter("description");
		
		Role role = new Role(name, description);
		
		switch (action) {
		
		case PathConstant.ROLE_ADD:
			int count = iRoleService.add(role);
			
			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.ROLE);
			else
			{
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.ROLE_ADD).forward(req, resp);
			}
			break;
		case PathConstant.ROLE_EDIT:

			int id = Integer.parseInt(req.getParameter("id"));
			role.setId(id);
			count = iRoleService.edit(role);
			if (count > 0) {
				resp.sendRedirect(req.getContextPath() + PathConstant.ROLE);
			} else {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.ROLE_EDIT).forward(req, resp);
			}
			break;
			
		}

	}
}
