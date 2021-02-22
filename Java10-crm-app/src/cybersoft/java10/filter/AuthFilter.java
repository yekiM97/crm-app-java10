package cybersoft.java10.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java10.constant.PathConstant;
import cybersoft.java10.model.User;

@WebFilter(filterName = "authFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String action = req.getServletPath();
		User user = (User) req.getSession().getAttribute("user");

		if (PathConstant.HOME_LOGIN.equals(action) || PathConstant.REGISTER.equals(action)
				|| action.startsWith("/assets")) {
			chain.doFilter(request, response);
			return;
		}

		if (user == null) {
			resp.sendRedirect(req.getContextPath() + PathConstant.HOME_LOGIN);
			return;
		}

		String roleName = user.getRoleName();
		if (action.startsWith(PathConstant.ROLE) && !roleName.equals("ROLE_ADMIN")) {
			resp.sendRedirect(req.getContextPath() + PathConstant.ERROR_404);
			return;
		}

		if (PathConstant.USER_ADD.equals(action) || PathConstant.USER_DELETE.equals(action)
				|| PathConstant.USER_EDIT.equals(action)) {
			if (!roleName.equals("ROLE_ADMIN")) {
				resp.sendRedirect(req.getContextPath() + PathConstant.ERROR_403);
				return;
			}
			chain.doFilter(request, response);
			return;
		}

		if (PathConstant.TASK.equals(action) || PathConstant.TASK_ADD.equals(action)
				|| PathConstant.TASK_EDIT.equals(action) || PathConstant.TASK_DELETE.equals(action)) {
			if ((!roleName.equals("ROLE_MANAGER") && !roleName.equals("ROLE_ADMIN"))) {
				resp.sendRedirect(req.getContextPath() + PathConstant.ERROR_403);
				return;
			}
			chain.doFilter(request, response);
			return;
		}

		if (PathConstant.TASKCATEGORY_DELETE.equals(action) || PathConstant.TASKCATEGORY_ADD.equals(action)) {
			if ((!roleName.equals("ROLE_MANAGER") && !roleName.equals("ROLE_ADMIN"))) {
				resp.sendRedirect(req.getContextPath() + PathConstant.ERROR_404_TASKCATEGORY);
				return;
			}
			chain.doFilter(request, response);
			return;
		}

		if (PathConstant.STATUS_ADD.equals(action) || PathConstant.STATUS_EDIT.equals(action)
				|| PathConstant.STATUS_DELETE.equals(action)) {
			if ((!roleName.equals("ROLE_MANAGER") && !roleName.equals("ROLE_ADMIN"))) {
				resp.sendRedirect(req.getContextPath() + PathConstant.ERROR_404_STATUS);
				return;
			}
			chain.doFilter(request, response);
			return;
		}

		chain.doFilter(request, response);
		return;
	}

}
