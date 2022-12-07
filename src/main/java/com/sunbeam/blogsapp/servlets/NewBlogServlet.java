package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.blogsapp.entitiesanddaos.Blog;
import com.sunbeam.blogsapp.entitiesanddaos.BlogDao;
import com.sunbeam.blogsapp.entitiesanddaos.Category;
import com.sunbeam.blogsapp.entitiesanddaos.CategoryDao;
import com.sunbeam.blogsapp.entitiesanddaos.User;

@WebServlet("/newblog")
public class NewBlogServlet extends HttpServlet {

	private static final long serialVersionUID = -3044224684162403483L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>New Blog</title>");
		out.println("</head>");
		out.println("<script>");
		out.println("function cat() {  \r\n");
		out.println("var mylist = document.getElementById(\"myList\");  \r\n");
		out.println("document.getElementById(\"favourite\").value = mylist.options[mylist.selectedIndex].text;  \r\n");
		out.println("}  ");
		out.println("</script>");
		out.println("<body>");
		out.println("<form method='post' action='newblog'>");
		out.println("Title: <input type='text' name='title' value=''/><br/><br/>");
		out.println("Content: <input type='text' name='content' value=''/><br/><br/>");
		out.println("Created Time: <input type='date' name='time' value=''/><br/><br/>");
		out.println("<b> Select Category using dropdown list </b>");
		out.println("<select id = \"myList\" onchange = \"cat()\" >");
//		out.println("<select id = \"myList\" >");
		try (CategoryDao cd = new CategoryDao()) {
			List<Category> ct = cd.getAllCategories();
			for (Category cat : ct) {
				out.println("<option>");
				out.println(cat.getTitle() + " - " + cat.getId());
				out.println("</option>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</select><br/><br/>");
		out.println(
				"<p>Category Picked is: <input type='text' name='catid' id='favourite' value='' readonly/></p><br/><br/>");
		out.println("<input type='submit' value='Add Blog'/>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String t = req.getParameter("title");
		String c = req.getParameter("content");
		String catidfull = req.getParameter("catid").trim();
		String catid = "" + catidfull.charAt(catidfull.length() - 1);
		System.out.println(catid);
		String birthStr = req.getParameter("time");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date b = null;
		try {
			b = sdf.parse(birthStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		System.out.println(u.getId());

		Blog blog = new Blog(t, c, b, u.getId(), Integer.parseInt(catid));
		try (BlogDao bd = new BlogDao()) {
			bd.save(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = req.getRequestDispatcher("/menuboard");
		rd.forward(req, resp);

	}

}
