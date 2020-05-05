package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Hotel;
import com.example.demo.Repository.HotelRepo;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	@Autowired
	private HotelRepo repo;
	@Autowired
	MongoTemplate mongoTemplate;

	@GetMapping
	public List<Hotel> getAll() {
		return repo.findAll();
	}

	@PostMapping
	public Hotel insertHotel(@RequestBody Hotel hotel) {
		return repo.insert(hotel);
	}

	@PutMapping
	public Hotel updateHotel(@RequestBody Hotel hotel) {
		return repo.save(hotel);
	}

	@DeleteMapping("/{id}")
	public String deleteHotel(@PathVariable("id") String Id) {
		repo.deleteById(Id);
		return "Hotel record deleted successfully..";
	}

	@GetMapping("/{id}")
	public Hotel getHotelById(@PathVariable("id") String id) {
		return repo.findById(id).get();
	}

	@GetMapping("/price/{maxPrice}")
	public List<Hotel> findByPricePerNightLessThan(@PathVariable("maxPrice") int maxPrice) {
		return repo.findByPricePerNightLessThan(maxPrice);
	}

	@GetMapping("/city/{cityName}")
	public List<Hotel> getHotelsByCity(@PathVariable("cityName") String cityName) {
		return repo.getHotelsByCity(cityName);
	}

	/**
	 * Example of MongoTemplate using Criteria.
	 */
	@GetMapping("/recommended")
	public List<Hotel> getRecommendedHotels() {
		int maxPrice = 5000;
		int rating = 7;
		Query query = new Query(Criteria.where("pricePerNight").lt(maxPrice).and("reviews.rating").gt(rating));
		return mongoTemplate.find(query, Hotel.class);
	}

	@GetMapping("/average/{city}")
	public List<Hotel> getAverageByCity(@PathVariable("city") String city) {
		int maxPrice = 2000;
		int rating = 6;
		Query query = new Query(Criteria.where("pricePerNight").lte(maxPrice).and("address.city").regex(city)
				.and("reviews.rating").gte(rating)).with(Sort.by(Sort.Order.asc("pricePerNight"))).limit(5);
		return mongoTemplate.find(query, Hotel.class);

	};

	// use of findAll(Page) method for performing pagination.
	@GetMapping("/hotelsInPage/{pageNo}/{limit}/{sortBy}")
	public Map<String, Object> getAllEmployeesInPage(@PathVariable("pageNo") int pageNo,
			@PathVariable("limit") int limit, @PathVariable("sortBy") String sortBy) {
		Map<String, Object> pageObj = new HashMap<String, Object>();
		Sort sort = Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(pageNo, limit, sort);
		Page page = repo.findAll(pageable);
		pageObj.put("Total no of records : ", +page.getTotalElements());
		pageObj.put("Total no of pages :", +page.getTotalPages());
		pageObj.put("Data", page.getContent());
		return pageObj;
	}

}
