package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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

@WebServlet("/findBlogs")
public class FindBlogsServlet extends HttpServlet {

	private static final long serialVersionUID = 6063324313893802786L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String enteredValue = req.getParameter("keyword").trim().toUpperCase();
		System.out.println(enteredValue);
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Search Blog</title>");
		out.println("</head>");
		out.println("<body>\r\n");
		out.println("<div align=\"center\">");
		out.println("<h2 style=\"background-color: #92a8d1;\">Found Blogs</h2><br/>\r\n");
		try (BlogDao bdao = new BlogDao()) {
			System.out.println("error checking!!! - point 1");
			List<Blog> gottenBlogs = bdao.findThoseBlogs(enteredValue);
			System.out.println("error checking!!! - point 2");
			if (gottenBlogs != null) {
				System.out.println("error checking!!! - point 3");
				if (gottenBlogs.size() != 0) {
					System.out.println("error checking!!! - point 4");
					out.println("<table border='1'>");
					out.println("<tr>");
					out.println("<th>Id</th>");
					out.println("<th>Title</th>");
					out.println("<th>Contents</th>");
					out.println("<th>Created On</th>");
					out.println("<th>by User Id</th>");
					out.println("<th>of Category Id</th>");
					out.println("</tr>");
					for (Blog sb : gottenBlogs) {
						System.out.println("error checking!!! - point 5");
						out.println("<tr>");
						out.printf("<td>%d</td>\r\n", sb.getId());
						out.printf("<td>%s</td>\r\n", sb.getTitle());
						out.printf("<td>%s</td>\r\n", sb.getContents());
						out.printf("<td>%s</td>\r\n", sb.getCreated_time());
						out.printf("<td>%d</td>\r\n", sb.getUserid());
						out.printf("<td>%d</td>\r\n", sb.getCategoryid());
						out.println("</tr>");
					}
					out.println("</table><br/><hr/><br/>");
					out.println("<a href='findBlogs'>Find Again?</a><br/>");
				} else {
					out.println("<h5>Oops, Looks like there are no blogs with that name</h5><br/>");
					out.println("<a href='findBlogs'>Try Again?</a><br/>");
				}
			} else {
				out.println("<h5>Oops, Looks like there are no blogs with that name</h5><br/>");
				out.println("<a href='findBlogs'>Try Again?</a><br/>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Search Blog</title>");
		out.println("</head>");
//		out.println("<script>");
//		out.println("function cat() {  \r\n");
//		out.println("var mylist = document.getElementById(\"myList\");  \r\n");
//		out.println("document.getElementById(\"favourite\").value = mylist.options[mylist.selectedIndex].text;  \r\n");
//		out.println("}  ");
//		out.println("</script>");
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
		out.println("<body style=\"background-color: #92a8d1;\">\r\n");
		out.println("<h2>Blogs</h2><br/>\r\n");
		out.println("<div align=\"center\">");
		out.println("<h4>Hello " + u.getFname()
				+ ", please type first then click on the search button</h4><br/?<hr/><br/>");
		out.println("<form method='post' action='findBlogs'>");
		out.println("<p>Search Blog by Keyword: <input type='text' name='keyword' value='' required/></p><br/><br/>");
		out.println("<input type='submit' name='' value='Go' required/>");
		out.println("</form><br/>");
		out.println("<a href='menuboard'>Back to Home Page</a><br/>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}
