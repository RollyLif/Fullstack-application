package com.learning.Learning.Rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.Learning.Exception.CustomErrorType;
import com.learning.Learning.dto.UserDTO;
import com.learning.Learning.repository.UserJpaRepository;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
	public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);
	private UserJpaRepository userJpaRepository;

	@Autowired
	public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
		this.userJpaRepository = userJpaRepository;
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userJpaRepository.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user) {
		if (userJpaRepository.findByName(user.getName()) != null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType(
							"Unable to create new user. A User with name " + user.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		userJpaRepository.save(user);
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {
		UserDTO user = userJpaRepository.findById(id).get();
		if (user == null) {
			return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody @Valid UserDTO user) {
		UserDTO currentuser = userJpaRepository.findById(id).get();
		if (currentuser == null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to upate. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		currentuser.setName(user.getName());
		currentuser.setAddress(user.getAddress());
		currentuser.setEmail(user.getEmail());

		userJpaRepository.saveAndFlush(currentuser);

		return new ResponseEntity<UserDTO>(currentuser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id) {
		UserDTO currentUser = userJpaRepository.findById(id).get();
		if (currentUser == null) {
			return new ResponseEntity<UserDTO>(
					new CustomErrorType("Unable to upate. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		userJpaRepository.delete(currentUser);
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}

}