package bigbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bigbank.bean.User;
import bigbank.service.RestaurantService;
import bigbank.service.UserService;

@Controller
@RequestMapping(value = "/manage")
public class ManageController {
	@Autowired
	RestaurantService restService;
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String getRestaurantList(Model model) {

		String email = ((org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userService.getUserByEmail(email);
		
		model.addAttribute("restList", restService.getRestaurantsByOwnerId(user.getId()));
		return "manage";
	}
}
