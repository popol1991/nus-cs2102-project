package bigbank.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import bigbank.bean.Restaurant;

@Repository
public class RestaurantDao extends BasicDao {

	public List<Restaurant> getTopNReviewedRestaurants(int topN) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		// TODO select topN restaurants which are most reviewed by customers
		sql.append("select * from restaurant");

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList;
	}

	public List<Restaurant> getRestaurantsByQuery(Map<String, String> query) {
		StringBuilder sql = new StringBuilder();
		/*
		 * sql.append("select * from restaurants")
		 * .append("where category=:category") .append("and area=:area")
		 * .append("and name %like% :keyword") .append("and avgprice>:lowprice")
		 * .append("and avgprice<:highprice");
		 */
		sql.append("select * from restaurant");

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(), query,
				new BeanPropertyRowMapper<Restaurant>(Restaurant.class));

		return resultList;
	}

	public List<Restaurant> getRestaurantsByOwnerId(int ownerId) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("select * from restaurant")
			.append(" where owner=:ownerId");
		parameters.put("ownerId", ownerId);

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList;
	}

}
