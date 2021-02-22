package cybersoft.java10.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "homeServlet", urlPatterns = "/d")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// tao moi cookie
		Cookie cookie = new Cookie("username", "minh");
		resp.addCookie(cookie);

		// them cookie vo trong response de tra ve cho browser
		resp.addCookie(new Cookie("password", "12345"));

		// luu thong tin vao session
		HttpSession session = req.getSession();

		Object user = session.getAttribute("user");

		if (user == null) {

			session.setAttribute("user", "admin");
			session.setMaxInactiveInterval(10);
			System.out.println("Đã set session");
		} else {
			System.out.println("User: " + user.toString());
		}

		// stateful
		
		req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
	}
}
