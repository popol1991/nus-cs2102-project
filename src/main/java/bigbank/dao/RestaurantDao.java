package bigbank.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import bigbank.bean.Restaurant;

@Repository
public class RestaurantDao extends BasicDao {

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
		sql.append("select * from restaurant").append(" where owner=:ownerId");
		parameters.put("ownerId", ownerId);

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList;
	}

	public Restaurant getRestaurantById(int restId) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("select * from restaurant").append(" where id=:restId");
		parameters.put("restId", restId);

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList.get(0);
	}

	public boolean updateRestaurantById(int restId, Restaurant rest) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("update restaurant")
				.append(" set `id`=:id, `name`=:name, `address`=:address, `category`=:category,")
				.append(" `isApproved`=:isApproved, `avg_price`=:avgPrice, `area`=:area,")
				.append(" `create_time`=:createTime, `owner`=:owner")
				.append(" where id=:restId");

		parameters.put("id", rest.getId());
		parameters.put("name", rest.getName());
		parameters.put("address", rest.getAddress());
		parameters.put("category", rest.getCategory());
		parameters.put("isApproved", rest.getIsApproved());
		parameters.put("avgPrice", rest.getAvgPrice());
		parameters.put("area", rest.getArea());
		parameters.put("createTime", rest.getCreateTime());
		parameters.put("owner", rest.getOwner());
		parameters.put("restId", restId);

		int rows = jdbcTemplate.update(sql.toString(), parameters);

		return rows > 0;
	}

	public List<Restaurant> getAllRestaurants() {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("select * from restaurant");

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList;
	}

	public boolean removeRestaurantById(int restId) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("delete from restaurant").append(" where id=:restId");
		parameters.put("restId", restId);

		int rows = jdbcTemplate.update(sql.toString(), parameters);

		return rows > 0;
	}
	
	public int getIdByNameNAddress(String name, String address) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("select id from restaurant")
			.append(" where name=:name and address=:address");
		parameters.put("name", name);
		parameters.put("address", address);

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList.get(0).getId();
	}

	public int createRestaurant(Restaurant rest) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("insert into restaurant values (")
				.append(" :id, :name, :address, :category,")
				.append(" :isApproved, :avgPrice, :area,")
				.append(" :createTime, :owner)");

		parameters.put("id", rest.getId());
		parameters.put("name", rest.getName());
		parameters.put("address", rest.getAddress());
		parameters.put("category", rest.getCategory());
		parameters.put("isApproved", rest.getIsApproved());
		parameters.put("avgPrice", rest.getAvgPrice());
		parameters.put("area", rest.getArea());
		parameters.put("createTime", rest.getCreateTime());
		parameters.put("owner", rest.getOwner());

		int rows = jdbcTemplate.update(sql.toString(), parameters);

		if (rows > 0) {
			return getIdByNameNAddress(rest.getName(), rest.getAddress());
		} else {
			return -1;
		}
	}

	public List<Restaurant> getRestWithMostReviews(int topN) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("select * from restaurant r")
			.append(" where r.isApproved = 1")
			.append(" order by (select count(*) from evaluate e")
			.append(" where e.restaurant_id = r.id)")
			.append(" desc limit :topN");
		parameters.put("topN", topN);

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList;
	}

	public List<Restaurant> getRestaurantsByCategory(String category) {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		sql.append("select * from restaurant").append(" where category=:category");
		parameters.put("category", category);

		List<Restaurant> resultList = jdbcTemplate.query(sql.toString(),
				parameters, new BeanPropertyRowMapper<Restaurant>(
						Restaurant.class));

		return resultList;
	}

}
