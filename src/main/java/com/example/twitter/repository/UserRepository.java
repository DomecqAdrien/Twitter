package com.example.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User getOneByUsername(String username);
}
