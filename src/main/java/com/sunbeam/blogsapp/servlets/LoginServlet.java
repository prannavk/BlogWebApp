package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.blogsapp.entitiesanddaos.User;
import com.sunbeam.blogsapp.entitiesanddaos.UserDao;

@WebServlet(value = "/loginservlet", name = "Login index.html page")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2035917762309862614L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext ctx = config.getServletContext();
		String key = ctx.getInitParameter("AccessCode");
		System.out.println(key);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("passwd");
		System.out.println(email + " " + password);
		InputStream in = req.getInputStream();
		OutputStream ot = resp.getOutputStream();
		User u = null;
		try (UserDao ud = new UserDao()) {
			u = ud.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (u != null && u.getPassword().equals(password)) {
			Cookie c = new Cookie("uname", u.getFname());
			c.setMaxAge(36000);
			resp.addCookie(c);

			HttpSession session = req.getSession();
			session.setAttribute("user", u);

			resp.sendRedirect("menuboard");

		} else {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Login Failed</title>");
			out.println("</head>");
			out.println("<body style=\"background-color: #92a8d1;\">");
			out.println("<h4>Invalid email or password.</h4>");
			out.println("<a href='index.html'>Login Again</a>");
			out.println("</body>");
			out.println("</html>");

		}

	}

}
