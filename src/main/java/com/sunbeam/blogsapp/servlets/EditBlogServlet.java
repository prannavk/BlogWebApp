package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunbeam.blogsapp.entitiesanddaos.Blog;
import com.sunbeam.blogsapp.entitiesanddaos.BlogDao;

@WebServlet("/editblog")
public class EditBlogServlet extends HttpServlet {

	private static final long serialVersionUID = -8634023509194529823L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Edit Blog</title>");
		out.println("</head>");
//		out.println("<script>");
//		out.println("function cat() {  \r\n");
//		out.println("var mylist = document.getElementById(\"myList\");  \r\n");
//		out.println("document.getElementById(\"favourite\").value = mylist.options[mylist.selectedIndex].text;  \r\n");
//		out.println("}  ");
//		out.println("</script>");
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
		int id = Integer.parseInt(req.getParameter("bid"));
		System.out.println(id);
		out.println("<div align=\"center\">");
		out.printf("Hello, %s<br/><br/>\r\n", usname);
		try (BlogDao bdao = new BlogDao()) {
			Blog blog = bdao.findById(id);
			if (blog != null) {
				out.println("<form method='post' action='editblog'>");
				out.printf("Title: <input type='text' name='title' value='%s'/><br/><br/>\r\n", blog.getTitle());
				out.printf("Content: <input type='text' name='content' value='%s'/><br/><br/>\r\n", blog.getContents());
				out.printf("Created Time: <input type='date' name='time' value='%s'/><br/><br/>\r\n", blog.getCreated_time());
				out.println("<input type='submit' value='Edit Blog'/>");
				out.println("</form>");
			} else {
				out.println("<h4>Error in editing this Blog</h4>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// out.println(cat.getTitle() + " - " + cat.getId());
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Under construction</title>");
		out.println("</head>");
		out.println("<body style=\"background-color: #92a8d1;\">");
		out.println("<h4>Edit Functionality can't be executed at the moment</h4>");
		out.println("</body>");
		out.println("</html>");
	}

}
