package com.example.springbootproject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelRepo hotelRepo;
	
	@PostMapping("/save")
	public Hotel savehotel(@RequestBody Hotel hotel) {
		return hotelRepo.save(hotel);
	}
	
	@PutMapping("/{id}")
	public Hotel updatehotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
		hotel.setId(id);
		return hotelRepo.save(hotel);
	}
	
	@DeleteMapping("/{id}")
	public void deletehotel(@PathVariable Integer id) {
		hotelRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Hotel> getById(@PathVariable Integer id) {
		return hotelRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<Hotel> getByPage(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return hotelRepo.findAll(pageable);
	}
	
	@GetMapping("/sortBy")
	public List<Hotel> getBySort(@RequestParam String sortby) {
		return hotelRepo.findAll(Sort.by(sortby));
	}
	
}
