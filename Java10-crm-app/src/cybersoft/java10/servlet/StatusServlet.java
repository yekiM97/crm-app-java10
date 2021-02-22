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
import cybersoft.java10.model.Status;
import cybersoft.java10.service.IStatusService;

@WebServlet(name = "statusServlet", urlPatterns = { PathConstant.STATUS, PathConstant.STATUS_ADD,
		PathConstant.STATUS_EDIT, PathConstant.STATUS_DELETE })
public class StatusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IStatusService iStatusService;

	@Override
	public void init() throws ServletException {
		IStatusService iStatusService = (IStatusService) ContextUltil.getContextApp().getBean("iStatusService");
		this.setiStatusService(iStatusService);
	}

	public IStatusService getiStatusService() {
		return iStatusService;
	}

	public void setiStatusService(IStatusService iStatusService) {
		this.iStatusService = iStatusService;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();

		switch (action) {
		case PathConstant.STATUS:
			List<Status> listStatus = iStatusService.getListStatus();
			req.setAttribute("Status", listStatus);
			req.getRequestDispatcher(UrlConstant.STATUS_INDEX).forward(req, resp);
			break;
		case PathConstant.STATUS_ADD:
			req.getRequestDispatcher(UrlConstant.STATUS_ADD).forward(req, resp);
			break;
		case PathConstant.STATUS_EDIT:
			int id = Integer.parseInt(req.getParameter("id"));

			Status status = iStatusService.findByID(id);

			req.setAttribute("status", status);
			req.getRequestDispatcher(UrlConstant.STATUS_EDIT).forward(req, resp);

			break;
		case PathConstant.STATUS_DELETE:
			int id1 = Integer.parseInt(req.getParameter("id"));
			int count = iStatusService.delete(id1);
			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.STATUS);
			else {
				req.setAttribute("message", "Xóa thất bại!");
				req.getRequestDispatcher(UrlConstant.STATUS_INDEX).forward(req, resp);
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

		Status status = new Status(name);

		switch (action) {

		case PathConstant.STATUS_ADD:
			int count = iStatusService.add(status);

			if (count > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.STATUS);
			else {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher(UrlConstant.STATUS_ADD).forward(req, resp);
			}
			break;
		case PathConstant.STATUS_EDIT:

			int id = Integer.parseInt(req.getParameter("id"));
			status.setId(id);
			count = iStatusService.edit(status);
			if (count > 0) {
				resp.sendRedirect(req.getContextPath() + PathConstant.STATUS);
			} else {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher(UrlConstant.STATUS_EDIT).forward(req, resp);
			}
			break;

		}
	}
}
