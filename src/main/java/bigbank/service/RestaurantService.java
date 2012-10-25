package bigbank.service;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bigbank.bean.Restaurant;
import bigbank.dao.RestaurantDao;
import bigbank.utils.Constants;

@Service
public class RestaurantService {
	@Autowired 
	RestaurantDao restDao;

	public List<Restaurant> getTopRestaurants() {
		return restDao.getTopNReviewedRestaurants(Constants.TOP_N);
	}

	public List<Restaurant> getRestaurantsByQuery(Map<String, String> query) {
		return restDao.getRestaurantsByQuery(query);
	}

	public List<Restaurant> getRestaurantsByOwnerId(int ownerId) {
		return restDao.getRestaurantsByOwnerId(ownerId);
	}
	
}
