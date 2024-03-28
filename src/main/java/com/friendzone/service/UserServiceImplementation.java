package com.friendzone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.friendzone.model.User;
import com.friendzone.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstname(user.getFirstname());
		newUser.setLastname(user.getLastname());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());

		User savedUser = userRepository.save(newUser);
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {

		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		}

		throw new Exception("user not found on id " + userId);
	}

	@Override
	public User findUserByEmail(String email) {
		
		User user = userRepository.findByEmail(email);
		
		return user;
	}

	@Override
	public User followUser(Integer userId1, Integer userId2) throws Exception {
		
		//user1 will follow user2
		
		User user1 = findUserById(userId1);
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowing().add(user2.getId());
		userRepository.save(user1);
		userRepository.save(user2);
		
		return user1;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		
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

	@Override
	public List<User> searchUser(String query) {
		
		
		return  userRepository.searchUser(query);
	}

}
