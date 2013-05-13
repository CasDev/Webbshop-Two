package org.casdev.web.http;

import javax.servlet.http.HttpSession;

import org.casdev.web.model.User;

public class Session {

	private HttpSession session;

	public Session(HttpSession session) {
		this.session = session;
		
		if (this.isNew()) {
			this.session.setMaxInactiveInterval(((1 * 60) * 20));
		}
		// 20 minutes

		if (this.session.getAttribute("useronline") == null) {
			this.session.setAttribute("useronline", false);
		}
	}

	public void addAttribute(String key, Object value) {
		this.session.setAttribute(key, value);
	}

	/**
	 * 
	 * @return Get the session-object
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * To check if the session is new
	 * 
	 * @return Is the session newly opened?
	 */
	public boolean isNew() {
		return session.isNew();
	}

	/**
	 * Log on
	 * 
	 * @return Has it been logged in, or not?
	 */
	public boolean logIn(User user) {

		if (!this.isOnline()) {
			this.session.setAttribute("user", user);
			this.session.setAttribute("useronline", true);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Log out
	 * 
	 * @return Has it been logged out, or not?
	 */
	public boolean logOut() {

		if (this.isOnline()) {
			this.session.setAttribute("user", null);
			this.session.setAttribute("useronline", false);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if anyone is logged into the session for the moment
	 * 
	 * @return To check if someone is logged in
	 */
	public boolean isOnline() {

		if (session.getAttribute("useronline") != null && ((Boolean) session.getAttribute("useronline")) == true) {
			return true;
		}

		return false;

	}

}
