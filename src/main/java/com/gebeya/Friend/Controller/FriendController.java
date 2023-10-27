package com.gebeya.Friend.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gebeya.Friend.Model.Friend;
import com.gebeya.Friend.Service.FriendService;

@RestController
public class FriendController {

	@Autowired
	FriendService friendService;

	@GetMapping("/friend")
	public Iterable<Friend> getAllFriend() {
		return friendService.findAll();

	}

	@GetMapping("/friend/{Id}")
	public Optional<Friend> getFriend(@PathVariable int Id) {
		return friendService.findById(Id);

	}

	@PostMapping("/friend")
	public void addFriend(@RequestBody Friend friend) {
		friendService.save(friend);

	}

	@PutMapping("/friend")
	public void updateFriend(@RequestBody Friend friend) {
		friendService.save(friend);

	}

	@DeleteMapping("/friend/{Id}")
	public void removeFriend(@PathVariable int Id) {
		friendService.deleteById(Id);

	}

	@PatchMapping("/friend/")
	public void updateFriend2(@PathVariable int Id) {
		friendService.deleteById(Id);

	}

	@GetMapping("/search")
	public Iterable<Friend> getByFirsNameAndLastName(@RequestParam(name = "first", required = false) String firstName,
			@RequestParam(name = "last", required = false) String lastName) {
		if (firstName != null && lastName != null)
			return friendService.findByFirstNameAndLastName(firstName, lastName);
		else if (firstName == null)
			return friendService.findByLastName(lastName);
		else if (lastName == null)
			return friendService.findByFirstName(firstName);
		else
			return friendService.findAll();

	}

}
