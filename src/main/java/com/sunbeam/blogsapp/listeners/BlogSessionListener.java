package com.sunbeam.blogsapp.listeners;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sunbeam.blogsapp.entitiesanddaos.User;

@WebListener
public class BlogSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		HashMap<String, Integer> userSettings = new HashMap<>();
		userSettings.put("valid Session", 1);
		session.setAttribute("userSettings", userSettings);
		System.out.println("User Settings HashMap Added on session creation");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		User u = (User) session.getAttribute("user");
		if (u != null)
			System.out.println(u.getFname() + "'s session has come to an end");
		HashMap<String, Integer> settingsMap = (HashMap<String, Integer>) session.getAttribute("userSettings");
		Iterator<HashMap.Entry<String, Integer>> itr = settingsMap.entrySet().iterator();
		System.out.println("User populated settings: ");
		while (itr.hasNext()) {
			HashMap.Entry<String, Integer> entry = itr.next();
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		if (session != null)
			session.invalidate();
	}

}
