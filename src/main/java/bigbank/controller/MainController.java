package bigbank.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bigbank.bean.Restaurant;

@Controller
@RequestMapping(value = "/main")
public class MainController {
	//@Autowired
	//RestaurantService restService;
	
	@RequestMapping(value = "/hotshops", method = RequestMethod.GET)
	public @ResponseBody List<Restaurant> getHotShops() {
		//return restService.getTopRestaurants();
		Restaurant r = new Restaurant();
		r.setAddress("biz");
		r.setName("western");
		List<Restaurant> res = new ArrayList<Restaurant>();
		res.add(r);
		return res;
	}
	
	@RequestMapping(value = "/about", method= RequestMethod.GET)
	public String getAboutPage() {
		return "about";
	}

}
