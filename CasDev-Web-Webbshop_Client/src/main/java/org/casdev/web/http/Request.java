package org.casdev.web.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.casdev.web.model.User;

import sun.misc.BASE64Decoder;

public class Request {

	private HttpServletRequest req;
	private HttpSession session;

	public Request(HttpServletRequest request, HttpSession session) {
		this.req = request;
		this.session = session;
	}

	/**
	 * Check for header if there is any basic authentication present
	 * 
	 * @param username The username to check after
	 * @param password The password to check after
	 * @return
	 */
	public boolean checkCredentials() {
		String oauth = this.req.getHeader("Authentication");
		
		if (oauth != null && !this.getSession().isOnline()) {
			
			try {
			oauth = oauth.replaceAll("Basic ", "");

			BASE64Decoder decoder = new BASE64Decoder();
			oauth = new String(decoder.decodeBuffer(oauth), "UTF-8");
			} catch (Exception e) {
				
				return false;
			}

			String[] credentials = oauth.split(":");
			String username = credentials[0];
			String password = credentials[1];
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			if (user.Load()) {
				
				this.getSession().logIn(user);
				return true;
			}else{
				return false;
			}

		}

		return false;
	}
	
	/**
	 * Get current session, or create a new if no is found
	 * 
	 * @return Current session, or new if none exist
	 */
	public Session getSession() {
		return new Session(this.session);
	}
	
	/**
	 * Check if header exist to indicate that this is an Ajax-request
	 * 
	 * @return
	 */
	public boolean isAjax() {
		String ajax = this.req.getHeader("X-Requested-With");

		if (ajax == null) {
			return false;
		} else {
			return true;
		}
	}

}
