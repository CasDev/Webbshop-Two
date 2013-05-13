package org.casdev.web.controller;

import org.casdev.web.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = Constants.USER_PREFIX)
public class UserController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(Model model) {
		ModelAndView mav = new ModelAndView();

		if (isAsynchronousCall()) {
			// AJAX-call
			if (!this.isOnline()) {
				login(model);
			} else {
				logout(model);
			}

			mav.setViewName("include/user");
		} else {
			// none-AJAX
		}

		return mav;
	}

}
