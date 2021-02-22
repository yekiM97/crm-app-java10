package cybersoft.java10.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java10.constant.PathConstant;
import cybersoft.java10.constant.UrlConstant;


@WebServlet(name = "errorServlet", urlPatterns= {PathConstant.ERROR_404, PathConstant.ERROR_403, PathConstant.ERROR_404_STATUS, PathConstant.ERROR_404_TASKCATEGORY})
public class ErrorServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch(action) {
		case PathConstant.ERROR_404:
			req.getRequestDispatcher(UrlConstant.ERROR_404).forward(req, resp);
			break;
		case PathConstant.ERROR_403:
			req.getRequestDispatcher(UrlConstant.ERROR_403).forward(req, resp);
			break;
		case PathConstant.ERROR_404_STATUS:
			req.getRequestDispatcher(UrlConstant.ERROR_404_STATUS).forward(req, resp);
			break;
		case PathConstant.ERROR_404_TASKCATEGORY:
			req.getRequestDispatcher(UrlConstant.ERROR_404_TASKCATEGORY).forward(req, resp);
			break;
		}
	}
}
