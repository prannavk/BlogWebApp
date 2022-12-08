package com.sunbeam.blogsapp.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunbeam.blogsapp.entitiesanddaos.User;

@WebFilter("/*")
public class BlogsAppSecurityFilter implements Filter {
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		List<String> un = new ArrayList<>();
		List<String> usr = new ArrayList<>();
		
		un.add("/BlogWebApp/loginservlet");
		un.add("/BlogWebApp/index.html");
		un.add("/BlogWebApp/registeruser");
		
		usr.add("/BlogWebApp/menuboard");
		usr.add("/BlogWebApp/allblogs");
		usr.add("/BlogWebApp/newblog");
		usr.add("/BlogWebApp/myblogs");
		usr.add("/BlogWebApp/Categories");
		usr.add("/BlogWebApp/findBlogs");
		usr.add("/BlogWebApp/logout?key1=123&key2=qwe");
		usr.add("/BlogWebApp/logout");
		usr.add("/BlogWebApp/editblog");
		usr.add("/BlogWebApp/loginservlet");
		
		System.out.println("preprocessing: " + req.getRequestURI());
		String uri = req.getRequestURI();
		
		FilterChain chain = arg2;
		
		if(un.contains(uri))
			chain.doFilter(arg0, arg1);
		else {
			HttpSession session = req.getSession();
			User u = (User) session.getAttribute("user");
			if(u != null && usr.contains(uri)) {
				chain.doFilter(arg0, arg1);
			} else {
				resp.sendError(403, "Illegal Access");
			}
		}

	}

}
