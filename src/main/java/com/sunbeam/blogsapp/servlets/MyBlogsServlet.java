package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.blogsapp.entitiesanddaos.Blog;
import com.sunbeam.blogsapp.entitiesanddaos.BlogDao;
import com.sunbeam.blogsapp.entitiesanddaos.User;

@WebServlet("/myblogs")
public class MyBlogsServlet extends HttpServlet {

	private static final long serialVersionUID = 7180353403051859552L;

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
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>My Blogs</title>");
		out.println("</head>");
		out.println("<body>");
		Cookie[] arr = req.getCookies();
		String usname = "";
		if (arr != null) {
			for (Cookie c : arr) {
				if (c.getName().equals("uname"))
					usname = c.getValue();
				break;
			}
		}
		out.println("<div align=\"center\">");
		out.printf("Hello, %s<br/><br/>\r\n", usname);
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		try (BlogDao bdao = new BlogDao()) {
			List<Blog> bg;
			try {
				bg = bdao.findMyBlogs(u);
				out.println("<table border='1'>");
				out.println("<tr>");
				out.println("<th>Id</th>");
				out.println("<th>Title</th>");
				out.println("<th>Contents</th>");
				out.println("<th>Created On</th>");
				out.println("<th>of Category Id</th>");
				out.println("<th>Actions: </th>");
				out.println("</tr>");
				for (Blog b : bg) {
					out.println("<tr>");
					out.printf("<td>%d</td>\r\n", b.getId());
					out.printf("<td>%s</td>\r\n", b.getTitle());
					out.printf("<td>%s</td>\r\n", b.getContents());
					out.printf("<td>%s</td>\r\n", b.getCreated_time());
					out.printf("<td>%d</td>\r\n", b.getCategoryid());
					out.println("<td><p><a href=\"editblog\">Edit</a>  <a href=\"deleteblog\">Delete</a></p></td>\r\n");
					out.println("</tr>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.println("</table><br/><hr/><br/>");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		out.println("</div>");
//		String message = (String) req.getAttribute("Message");
		out.println("<a href='menuboard'>Back to Home Page</a><br/>");
		out.println("<a href='logout'>Sign Out</a><br/>");
		out.println("</body>");
		out.println("</html>");

	}

}
