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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/guest")
public class GuestController {

	@Autowired
	private GuestRepo guestRepo;
	
	@PostMapping("/save")
	public Guest saveGuest(@RequestBody Guest guest) {
		return guestRepo.save(guest);
	}
	
	@PutMapping("/{id}")
	public Guest updateGuest(@PathVariable Integer id, @RequestBody Guest guest) {
		guest.setId(id);
		return guestRepo.save(guest);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGuest(@PathVariable Integer id) {
		guestRepo.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Guest> getById(@PathVariable Integer id) {
		return guestRepo.findById(id);
	}
	
	@GetMapping("/page")
	public Page<Guest> getByPage(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		return guestRepo.findAll(pageable);
	}
	
	@GetMapping("/sortBy")
	public List<Guest> getBySort(@RequestParam String sortby) {
		return guestRepo.findAll(Sort.by(sortby));
	}
	
}
