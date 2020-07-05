package com.example.twitter.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.twitter.model.PostDTO;
import com.example.twitter.domain.Reaction;
import com.example.twitter.model.NewTweetDTO;
import com.example.twitter.services.PostService;
import com.example.twitter.services.ReactionService;

@RestController
public class TwitterController {
	
	@Resource PostService postService;
	@Resource ReactionService reactionService;

	@RequestMapping(method = RequestMethod.POST, value="/post", headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NewTweetDTO post(@RequestBody NewTweetDTO post, @CookieValue(value = "userid") String id){
        return postService.addPost(post, Long.parseLong(id));
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/historique/{id}")
	public List<PostDTO> historique(@PathVariable long id){
		return postService.getPostsByAuthor(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/timeline")
	public List<PostDTO> timeline(@CookieValue(value = "userid", defaultValue = "empty") String id) throws Exception{
		if(id.equals("empty")) {
			throw new Exception("Cookie userid absent");
		}
		System.out.println(id);
		return postService.getTimeline(Long.parseLong(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/post/{id}")
	public PostDTO getOne(@PathVariable long id){
		System.out.println(id);
		return postService.getOne(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/fav/{idPost}")
	public PostDTO fav(@CookieValue(value = "userid") String id, @PathVariable long idPost){
		System.out.println(id);
		System.out.println(idPost);
		return postService.fav(idPost, Long.parseLong(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/repost/{idPost}")
	public PostDTO repost(@CookieValue(value = "userid") String id, @PathVariable long idPost){
		System.out.println(id);
		return postService.repost(idPost, Long.parseLong(id));
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/reactions")
	public List<Reaction> repost(){
		return postService.reactions();
	}
	
}
