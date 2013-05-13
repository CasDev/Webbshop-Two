package org.casdev.web.controller;

import org.casdev.web.Constants;
import org.casdev.web.http.Request;
import org.casdev.web.model.Page;
import org.casdev.web.model.attribute.PageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { Constants.STARTPAGE_PREFIX, Constants.STARTPAGE_ALT_PREFIX})	
public class IndexController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(Model model) {
		ModelAndView mav = new ModelAndView();
		
		applyApplication(model);
		isOnline();

		Page p = new Page();
		p.setType(PageType.START);
		boolean loaded = p.Load();
		Request req = new Request(this.request, this.session);

		if (loaded) {
			mav.setViewName("startpage");
			mav.addObject("page", p);
			mav.addObject("pageid", p.getId());
		} else {
			return new ModelAndView("redirect:" + Constants.ERROR_PREFIX);
		}

		return mav;
	}

}
