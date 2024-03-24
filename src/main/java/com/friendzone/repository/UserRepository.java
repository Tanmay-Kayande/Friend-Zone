package com.friendzone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.friendzone.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
