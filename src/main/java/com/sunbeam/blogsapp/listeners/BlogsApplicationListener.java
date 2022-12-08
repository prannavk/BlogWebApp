package com.sunbeam.blogsapp.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BlogsApplicationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContextListener.super.contextDestroyed(sce);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Class.forName(com.sunbeam.blogsapp.entitiesanddaos.DbUtil.DB_DRIVER);
			System.out.println("Driver Registered");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
}
