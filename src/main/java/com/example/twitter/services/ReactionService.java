package com.example.twitter.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.twitter.repository.PostRepository;
import com.example.twitter.repository.ReactionRepository;
import com.example.twitter.repository.UserRepository;

@Service
public class ReactionService {
	
	@Resource
    private PostRepository postRepository;
	@Resource
	private UserRepository userRepository;
	@Resource
	private ReactionRepository reactionRepository;
	


}
