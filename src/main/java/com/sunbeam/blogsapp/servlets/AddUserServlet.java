package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.blogsapp.entitiesanddaos.User;
import com.sunbeam.blogsapp.entitiesanddaos.UserDao;

@WebServlet("/registeruser")
public class AddUserServlet extends HttpServlet {

	private static final long serialVersionUID = -8604199102017608838L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>New User</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form method='post' action='registeruser'>");
		out.println("Name: <input type='text' name='name' value=''/><br/><br/>");
		out.println("Mobile: <input type='text' name='mobile' value=''/><br/><br/>");
		out.println("Email: <input type='text' name='email' value=''/><br/><br/>");
		out.println("Password: <input type='password' name='password' value=''/><br/><br/>");
		out.println("Date: <input type='date' name='bd' value=''/><br/><br/>");
		out.println("<input type='submit' value='Add Customer'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String birthStr = req.getParameter("bd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date birth = null;
		try {
			birth = sdf.parse(birthStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		int id = 0;
//		int enabled = 1;
		User user = new User(name, email, password, mobile, birth);
		try (UserDao d = new UserDao()) {
			d.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("index.html");
		rd.forward(req, resp);
	}

}
