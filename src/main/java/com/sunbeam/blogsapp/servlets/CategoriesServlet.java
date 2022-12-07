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

import com.sunbeam.blogsapp.entitiesanddaos.Category;
import com.sunbeam.blogsapp.entitiesanddaos.CategoryDao;

@WebServlet(value = "/Categories")
public class CategoriesServlet extends HttpServlet {

	private static final long serialVersionUID = 4347712396911317221L;

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
		out.println("<title>All Categories</title>");
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
		try (CategoryDao cd = new CategoryDao()) {
			List<Category> clist = cd.getAllCategories();
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>Id</th>");
			out.println("<th>Title</th>");
			out.println("<th>Description</th>");
			// TODO Implement join here : Show number of blogs of this category
			out.println("</tr>");
			for (Category c : clist) {
				out.println("<tr>");
				out.printf("<td>%d</td>\r\n", c.getId());
				out.printf("<td>%s</td>\r\n", c.getTitle());
				out.printf("<td>%s</td>\r\n", c.getDescription());
				out.println("</tr>");
			}
			out.println("</table><br/><hr/><br/><br/>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("<a href='addcategory'>Add a new Category</a><br/>");
		// TODO Add category
		out.println("<a href='menuboard'>Back to Home Page</a><br/>");
		out.println("</div>");
//		String message = (String) req.getAttribute("Message");
		out.println("</body>");
		out.println("</html>");
	}

}
