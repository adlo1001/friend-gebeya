package com.gebeya.Friend.Controller;

import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gebeya.Friend.Exceptions.ErrorMessage;
import com.gebeya.Friend.Model.Friend;
import com.gebeya.Friend.Service.FriendService;

@RestController
public class FriendController {

	private static final Logger LOGGER = Logger.getLogger(FriendController.class);

	@Autowired
	FriendService friendService;

	@Autowired
	MessageSource messageResource;

	@Value("${lang}")
	private String lang;

	@ResponseBody
	@GetMapping("/i18n")
	public String getI18n(@RequestHeader(name = "accept-language", required = false) Locale locale) {
		return messageResource.getMessage("welcome", null, locale);

	}

	@ResponseBody
	@GetMapping("/lang")
	public String getLang() {
		LOGGER.error("Hi Lang!");
		return lang;

	}

	@GetMapping("/friends")
	public Iterable<Friend> getAllFriend() {
		return friendService.findAll();

	}

	@GetMapping("/friend")
	public Page<Friend> getAllFriendPages(@RequestParam("p") int page, @RequestParam("s") int size) {
		Pageable paging = PageRequest.of(page, size);
		return friendService.findAll(paging);

	}

	@GetMapping("/friend/{Id}")
	public Optional<Friend> getFriend(@PathVariable int Id) {
		return friendService.findById(Id);

	}

	@PostMapping("/friend")
	public void addFriend(@Valid @RequestBody Friend friend) {
		if (!friendService.existsById(friend.getId()) && friend.getFirstName() != null && friend.getLastName() != null)
			friendService.save(friend);
		else
			throw new RuntimeException("Wrong input!");

	}
	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(RuntimeException.class) ErrorMessage
	 * exceptionHandler(RuntimeException e) { return new
	 * ErrorMessage(e.getMessage(), "400"); }
	 */

	@PutMapping("/friend")
	public ResponseEntity<Friend> updateFriend(@RequestBody Friend friend) {
		if (friendService.existsById(friend.getId()))
			return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
		else
			return new ResponseEntity(friend, HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/friend/{Id}")
	public void removeFriend(@PathVariable int Id) {
		friendService.deleteById(Id);

	}

	@PatchMapping("/friend")
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
