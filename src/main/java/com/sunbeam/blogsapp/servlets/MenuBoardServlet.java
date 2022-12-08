package com.sunbeam.blogsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/menuboard", name = "Menu Board Main Page", initParams = {
		@WebInitParam(name = "greeting", value = "Welcome to the MenuBoard Page") })
public class MenuBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -396543264951938152L;

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
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Blog Menu Board</title>");
		out.println("</head>");
		out.println("<body>");
//		ServletContext ctx = this.getServletContext();
//		String paramValue = ctx.getInitParameter("app.title");
//		out.println("<h3>" + paramValue + "</h3>");
		ServletConfig cfg = this.getServletConfig();
		String greeting = cfg.getInitParameter("greeting");
		System.out.println(greeting);
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
//		String message = (String) req.getAttribute("Message");
		out.println("<div align=\"center\">");
		out.println("<h3>Welcome</h3>");
		out.println("</div>");
		out.printf("Hello, %s<br/><br/>\r\n", userName);
		out.println("<div class=\"left sidebar\" id=\"sidebar\">");
		out.println("<div class=\"section\">");
		out.println("<div class=\"section-content\">");
		out.println("<h2></h2>");
		out.println("<ul class=\"nice-list\">");
		out.println("<li><a href=\"newblog\">ADD A NEW BLOG</a></li>\r\n");
		out.println("<li><a href=\"allblogs\">ALL BLOGS</a></li>");
		out.println("<li><a href=\"myblogs\">MY BLOGS </a></li>");
		out.println("<li><a href=\"findBlogs\">FIND BLOGS</a></li>");
		out.println("<li><a href=\"Categories\">CATEGORIES</a></li>");
		out.println("<li><a href=\"logout\">SIGN OUT</a></li>");
		//Demo of queryString
		String a = "123", b = "qwe";
		out.printf("\n<li><a href=\"logout?key1=%s&key2=%s\">SIGN OUT - mod</a></li>\n", a, b);
		out.println("</ul>");
		out.println("</div>");
//		<div align="center">
//		<h3>${initParam.appTitle}</h3>
//		</div>
//		Hello, ${lb.users.name}<hr/>
//	<div class="left sidebar" id="sidebar">
//	    <div class="section">
//	        <div class="section-content">
//	            <h2></h2>
//	            <ul class="nice-list">
//	                <li><a href="newblog">ADD A NEW BLOG</a></li>
//	                <li><a href="allBlogs.jsp">ALL BLOGS</a></li>
//	                <li><a href="myBlog.jsp">MY BLOGS </a></li>
//	                <li><a href="findBlogs.jsp">FIND BLOGS</a></li>
//	                <li><a href="showcategories.jsp">CATEGORIES</a></li>
//	                 <li><a href="logout.jsp">SIGN OUT</a></li>
//	            </ul>
//	        </div>
		out.println("<br/>");
		out.println("</body>");
		out.println("</html>");
		// req.openInputStream();
		// resp.openOutputStream();
		// resp.sendRedirect() sends a minimal response to the client which contain
		// status code 302 and location (url) of next web component.
		// Cookie c = new Cookie("key", "value");
		// resp.addCookie(c);
	}

}
