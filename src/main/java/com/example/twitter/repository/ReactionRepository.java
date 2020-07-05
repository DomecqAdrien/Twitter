package com.example.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.domain.Reaction;
import com.example.twitter.domain.ReactionId;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, ReactionId> {

	Reaction findOneByReactionId(ReactionId rId);

}
