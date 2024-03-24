package com.friendzone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.friendzone.model.User;
import com.friendzone.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {

		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());

		User savedUser = userRepository.save(newUser);

		return savedUser;

	}

	@GetMapping("/users")
	public List<User> getUsers() {

		List<User> users = userRepository.findAll();

		return users;
	}

	@GetMapping("/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception {

		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		}

		throw new Exception("user not found on id " + id);
	}

	@PutMapping("/users/{userId}")
	public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {

		Optional<User> userUp = userRepository.findById(userId);

		if (userUp.isEmpty()) {

			throw new Exception("user not found on id " + userId);
		}

		User oldUser = userUp.get();

		if (user.getFirstname() != null) {
			oldUser.setFirstname(user.getFirstname());
		}
		if (user.getLastname() != null) {
			oldUser.setLastname(user.getLastname());
		}
		if (user.getPassword() != null) {
			oldUser.setPassword(user.getPassword());
		}

		User updatedUser = userRepository.save(oldUser);

		return updatedUser;
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Integer userId) throws Exception {

		Optional<User> userDel = userRepository.findById(userId);

		if (userDel.isEmpty()) {

			throw new Exception("user not found on id " + userId);
		}

		userRepository.delete(userDel.get());
		
		return "user deleted with id " + userId;
	}
}
