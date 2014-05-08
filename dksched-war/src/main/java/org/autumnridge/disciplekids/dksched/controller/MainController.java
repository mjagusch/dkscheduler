package org.autumnridge.disciplekids.dksched.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@PreAuthorize("hasRole('ROLE_ACTIVE')")
	@RequestMapping(value={"/"}) 
	public String getMainPage(){
		// TODO: login
		// TODO: cookies to save user
		// TODO: week entity
		// TODO: volunteer instance entity
		// TODO: audit user access
		// TODO: mysql database
		// TODO: styling to match ARC/Ridgelines
		// TODO: email reminders
		// TODO: reporting - access, volunteer, status, etc.
		// TODO: JSON mapper
		// TODO: Cookies for registration
		// TODO: only prompt for registration if no cookie
		return "index";
	}
}
