package bigbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bigbank.bean.Restaurant;
import bigbank.service.RestaurantService;

@Controller
@RequestMapping(value = "/store")
public class RestController {
	@Autowired
	RestaurantService restService;
	
	@RequestMapping(value = "/{restId}", method = RequestMethod.GET) 
	public String getPage(@PathVariable int restId, Model model) {
		Restaurant rest = restService.getRestaurantById(restId);
		model.addAttribute("rest", rest);
		return "store";
	}
}
