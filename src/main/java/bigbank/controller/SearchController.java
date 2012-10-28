package bigbank.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bigbank.bean.Restaurant;
import bigbank.service.RestaurantService;

@Controller
@RequestMapping(value = "search")
public class SearchController {
	@Autowired
	RestaurantService restService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Restaurant> search(HttpServletRequest request, Model model) {
		Map<String, String> query = request.getParameterMap();
		List<Restaurant> result = restService.getRestaurantsByQuery(query);
		return result;
	}
}
