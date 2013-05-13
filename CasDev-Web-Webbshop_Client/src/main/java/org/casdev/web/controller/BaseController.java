package org.casdev.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.casdev.web.http.Request;
import org.casdev.web.model.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class BaseController {

	public @Autowired(required = true)
	HttpServletRequest request;
	public @Autowired(required = true)
	HttpSession session;

	public boolean isOnline() {

		Request req = new Request(this.request, this.session);

		req.getSession()
				.addAttribute("useronline", req.getSession().isOnline());

		return req.getSession().isOnline();

	}

	public boolean isAsynchronousCall() {

		Request req = new Request(this.request, this.session);

		return req.isAjax();
	}

	public void logout(Model model) {

		Request req = new Request(this.request, this.session);

		req.getSession().logOut();
	}

	public void login(Model model) {

		Request req = new Request(this.request, this.session);

		if (req.getSession().isOnline()) {

			req.getSession().logOut();
			req.getSession().addAttribute("useronline", false);

		} else {

			if (req.checkCredentials()) {
				req.getSession().addAttribute("useronline", true);
			} else {
				req.getSession().addAttribute("useronline", false);
			}

		}

	}

	public void applyApplication(Model model) {

		Application app = new Application();
		app.setId(1);
		boolean load = app.Load();
		if (load) {
			model.addAttribute("application", app);
		}

	}

}
