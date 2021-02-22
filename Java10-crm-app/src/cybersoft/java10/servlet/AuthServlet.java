package cybersoft.java10.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;


import cybersoft.java10.constant.PathConstant;
import cybersoft.java10.constant.UrlConstant;
import cybersoft.java10.container.ContextUltil;
import cybersoft.java10.model.User;
import cybersoft.java10.service.IUserService;


@WebServlet(name = "authServlet", urlPatterns = { PathConstant.REGISTER, PathConstant.HOME_LOGIN,
		PathConstant.HOME_LOGOUT,PathConstant.HOME })
public class AuthServlet extends HttpServlet {
	
	
	private IUserService iUserService;

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		IUserService iUserService = (IUserService) ContextUltil.getContextApp().getBean("iUserService");
		this.setiUserService(iUserService);
	}
	

	public IUserService getiUserService() {
		return iUserService;
	}


	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		switch (req.getServletPath()) {
		case PathConstant.HOME:
			req.getRequestDispatcher(UrlConstant.HOME_INDEX).forward(req, resp);
			break;
		case PathConstant.REGISTER:
			req.getRequestDispatcher(UrlConstant.HOME_REGISTER).forward(req, resp);
			break;
		case PathConstant.HOME_LOGIN:

			req.getRequestDispatcher(UrlConstant.HOME_LOGIN).forward(req, resp);

			break;
		case PathConstant.HOME_LOGOUT:
			HttpSession session = req.getSession();
			session.removeAttribute("user");
			req.getRequestDispatcher(UrlConstant.HOME_LOGIN).forward(req, resp);
			break;
		default:
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = req.getParameter("username");
		String action = req.getServletPath();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		switch (action) {
		case PathConstant.REGISTER:
			String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
			User user = new User(name, email, hashed);
			int result = iUserService.addUser(user);
			if (result > 0)
				resp.sendRedirect(req.getContextPath() + PathConstant.HOME_LOGIN);
			else {
				req.setAttribute("message", "Đăng kí thất bại");
				req.getRequestDispatcher(UrlConstant.HOME_REGISTER).forward(req, resp);
			}

			if (BCrypt.checkpw(password, hashed))
				System.out.println("It matches");
			else
				System.out.println("It does not match");
			break;

		case PathConstant.HOME_LOGIN:

			String email1 = req.getParameter("email");
			String password1 = req.getParameter("password1");

			User acc1 = iUserService.findByEmail(email1);

			if (acc1 != null) {
				if (BCrypt.checkpw(password1, acc1.getPassword())) {
					HttpSession session = req.getSession();
//					session.setMaxInactiveInterval(60);
					session.setAttribute("user", acc1);
					resp.sendRedirect(req.getContextPath() + PathConstant.HOME);
				} else {
					req.setAttribute("message", "Sai email hoặc mật khẩu");
					req.getRequestDispatcher(UrlConstant.HOME_LOGIN).forward(req, resp);
				}
			} else {
				req.setAttribute("message", "Sai email hoặc mật khẩu");
				req.getRequestDispatcher(UrlConstant.HOME_LOGIN).forward(req, resp);
			}

			break;
		}
	}

}
