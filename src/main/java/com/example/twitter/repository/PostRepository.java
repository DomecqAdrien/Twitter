package com.example.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.twitter.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	List<Post> getAllPostsByUserId(Long id);

}
