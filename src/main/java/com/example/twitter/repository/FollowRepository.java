package com.example.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.domain.Follow;
import com.example.twitter.domain.FollowId;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	
	Follow findOneByFollowId(FollowId id); 

}
