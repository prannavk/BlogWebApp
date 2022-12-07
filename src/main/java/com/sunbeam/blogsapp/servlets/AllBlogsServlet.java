package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.blogsapp.entitiesanddaos.Blog;
import com.sunbeam.blogsapp.entitiesanddaos.BlogDao;

@WebServlet("/allblogs")
public class AllBlogsServlet extends HttpServlet {

	private static final long serialVersionUID = 2551013751529814527L;

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
		out.println("<title>All Blogs</title>");
		out.println("</head>");
		out.println("<body>");
//		ServletContext ctx = this.getServletContext();
//		String paramValue = ctx.getInitParameter("app.title");
//		out.println("<h3>" + paramValue + "</h3>");
		String userName = "";
		Cookie[] arr = req.getCookies();
		if (arr != null) {
			for (Cookie c : arr) {
				if (c.getName().equals("uname")) {
					userName = c.getValue();
					break;
				}
			}
		}
		out.println("<div align=\"center\">");
		out.printf("Hello, %s<br/><br/>\r\n", userName);
		try (BlogDao bd = new BlogDao()) {
			List<Blog> b = bd.findAllTheBlogsJoinedWithName();
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>Id</th>");
			out.println("<th>Title</th>");
			out.println("<th>Contents</th>");
			out.println("<th>Created On</th>");
			out.println("<th>by User Id</th>");
			out.println("<th>by UserName</th>");
			out.println("<th>of Category Id</th>");
			out.println("</tr>");
			for (Blog bg : b) {
				out.println("<tr>");
				out.printf("<td>%d</td>\r\n", bg.getId());
				out.printf("<td>%s</td>\r\n", bg.getTitle());
				out.printf("<td>%s</td>\r\n", bg.getContents());
				out.printf("<td>%s</td>\r\n", bg.getCreated_time());
				out.printf("<td>%d</td>\r\n", bg.getUserid());
				out.printf("<td>%s</td>\r\n", bg.getExtraField());
				out.printf("<td>%d</td>\r\n", bg.getCategoryid());
				out.println("</tr>");
			}
			out.println("</table><br/><hr/><br/>");

		} catch (Exception e) {
			e.printStackTrace();
		}

		out.println("</div>");
//		String message = (String) req.getAttribute("Message");
		out.println("<a href='menuboard'>Back to Home Page</a><br/>");
		out.println("<a href='logout'>Sign Out</a><br/>");
		out.println("</body>");
		out.println("</html>");
	}

}
