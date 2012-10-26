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
		//TODO return top N restaurants
		return restDao.getAllRestaurants();
	}

	public List<Restaurant> getRestaurantsByQuery(Map<String, String> query) {
		return restDao.getRestaurantsByQuery(query);
	}

	public List<Restaurant> getRestaurantsByOwnerId(int ownerId) {
		return restDao.getRestaurantsByOwnerId(ownerId);
	}

	public boolean approveRestaurantWithId(int restId) {
		Restaurant rest = restDao.getRestaurantById(restId);
		rest.setIsApproved(1);
		return restDao.updateRestaurantById(restId, rest);
	}

	public boolean removeRestaurantById(int restId) {
		return restDao.removeRestaurantById(restId);
	}

	public boolean updateRestaurant(Restaurant rest) {
		Restaurant old = restDao.getRestaurantById(rest.getId());
		if (!old.getName().equals(rest.getName())) {
			old.setName(rest.getName());
		}
		if (!old.getAddress().equals(rest.getAddress())) {
			old.setAddress(rest.getAddress());
		}
		if (!old.getCategory().equals(rest.getCategory())) {
			old.setCategory(rest.getCategory());
		}
		if (old.getAvgPrice() != rest.getAvgPrice()) {
			old.setAvgPrice(rest.getAvgPrice());
		}
		if (old.getArea() != rest.getArea()) {
			old.setArea(rest.getArea());
		}
		return restDao.updateRestaurantById(rest.getId(), old);
	}
	
}
