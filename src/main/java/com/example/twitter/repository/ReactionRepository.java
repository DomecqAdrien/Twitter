package com.example.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.domain.Reaction;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {

}
