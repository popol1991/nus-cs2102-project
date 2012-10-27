package bigbank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bigbank.bean.Restaurant;
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

		model.addAttribute("restList",
				restService.getRestaurantsByOwnerId(user.getId()));
		return "manage";
	}

	@RequestMapping(value = "/approve/{restId}")
	public @ResponseBody
	boolean approveRestaurantById(@PathVariable int restId) {
		return restService.approveRestaurantWithId(restId);
	}

	@RequestMapping(value = "/delete/{restId}")
	public @ResponseBody
	boolean deleteRestaurantById(@PathVariable int restId) {
		return restService.removeRestaurantById(restId);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public @ResponseBody String newRestModel(@RequestParam("value") String value,
			@RequestParam("id") String field) {
		return value;
	}

}
