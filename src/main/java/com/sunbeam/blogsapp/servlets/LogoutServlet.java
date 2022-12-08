package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.blogsapp.entitiesanddaos.User;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1725730199108662809L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("User Successfully Logged Out");
		if(!req.getParameterNames().equals(null))
			System.out.println(req.getParameter("key1") + ", " + req.getParameter("key2"));
		

		Cookie[] c = req.getCookies();
		if (c != null) {
			for (Cookie ch : c) {
				if (ch.getName().equals("uname")) {
					ch.setValue("");
					ch.setMaxAge(-1);
					break;
				}
			}
		}
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		session.invalidate();
		resp.setContentType("text.html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>LOG OUT</title></head>");
		out.println("<body style=\"background-color: #92a8d1;\">\r\n");
		out.println("<h1>Blogs</h1><br/>\r\n");
		if (u != null)
			out.println("<h2>Thank you " + u.getFname() + ", please Visit Again</h2><br/?<hr/><br/>");
		out.println("<a href=\"index.html\">Login Again</a>");
		out.println("</body>");
		out.println("");
		out.println("</html>");

	}

}
