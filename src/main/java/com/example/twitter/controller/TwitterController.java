package com.example.twitter.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.twitter.model.PostDTO;
import com.example.twitter.model.TweetIdDTO;
import com.example.twitter.services.PostService;

@RestController
public class TwitterController {
	
	PostService service;
	
	public TwitterController(PostService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("")
	public TweetIdDTO helloWorld() {
		return TweetIdDTO.create(32);
	}
	@RequestMapping(method = RequestMethod.POST, value="/post", headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PostDTO post(@RequestBody PostDTO post, @CookieValue(value = "userid") String id){
        return service.addPost(post, Long.parseLong(id));
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
	public List<PostDTO> historique(@PathVariable long id){
		return service.getPostsByAuthor(id);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/timeline")
	public List<PostDTO> timeline(@CookieValue(value = "userid") String id){
		System.out.println(id);
		return service.getPostsByAuthor(Long.parseLong(id));
		
	}
	
}
