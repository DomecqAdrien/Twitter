package com.example.twitter.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.twitter.domain.Follow;
import com.example.twitter.domain.Post;
import com.example.twitter.domain.Reaction;
import com.example.twitter.domain.ReactionId;
import com.example.twitter.domain.User;
import com.example.twitter.functions.DateNow;
import com.example.twitter.mappers.PostMapper;
import com.example.twitter.model.PostDTO;
import com.example.twitter.model.NewTweetDTO;
import com.example.twitter.repository.PostRepository;
import com.example.twitter.repository.ReactionRepository;
import com.example.twitter.repository.UserRepository;

@Service
public class PostService {
	
	final AtomicInteger counter = new AtomicInteger();
	final List<Post> posts = new CopyOnWriteArrayList<>();
	
	@Resource private PostRepository postRepository;
	@Resource private UserRepository userRepository;
	@Resource private ReactionRepository reactionRepository;

    @Resource private PostMapper mapper;


    public List<PostDTO> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return mapper.mapTo(postList);
    }
    public PostDTO getPostById(long id) {
    	Post p = postRepository.getOne(id);
        return mapper.mapTo(p);
    }
    public NewTweetDTO addPost(NewTweetDTO post, long id) {
    	System.out.println(post.toString());
    	Post p = mapper.mapToNewTweet(post);
    	p.setTimestamp(DateNow.getTimeNow());
    	System.out.println(id);
    	p.setUser(userRepository.getOne(id));
    	postRepository.save(p);
        return mapper.mapToNewTweet(p);
    }
    
    
    public void deletePost(PostDTO postDTO) {
    	postRepository.delete(mapper.mapTo(postDTO));
    }
	public List<PostDTO> getPostsByAuthor(Long id) {
		List<Post> ps = postRepository.getAllPostsByUserId(id);
		return mapper.mapTo(ps);
	}
	
	public PostDTO fav(long idPost, long idUser) {
		return newFav(idPost, idUser, true);
	}
	public PostDTO repost(long idPost, long idUser) {
		return newFav(idPost, idUser, false);
	}
	
	public PostDTO newFav(long idPost, long idUser, boolean isFav) {
		Post p = postRepository.getOne(idPost);
		
		ReactionId rId = new ReactionId();
		rId.setFav(isFav);
		rId.setPost(p);
		System.out.println(rId.getPost().getMessage());
		rId.setUser(userRepository.getOne(idUser));
		
		Reaction checkR = reactionRepository.findOneByReactionId(rId);
		
		if(checkR == null) {
			System.out.println("null");
			Reaction r = new Reaction();
			r.setDateReaction(DateNow.getTimeNow());
			r.setReactionId(rId);
			if(isFav)
				p.getFavs().add(r);
			else
				p.getReposts().add(r);
			reactionRepository.save(r);
			postRepository.save(p);
		}
		System.out.println(p.getReposts().size());
		return mapper.mapTo(p);
	}
	public PostDTO getOne(long id) {
		Post post = postRepository.getOne(id);
		return mapper.mapTo(post);
	}
	
	public List<Reaction> reactions(){
		return reactionRepository.findAll();
	}
	public List<PostDTO> getTimeline(long userId) {
		List<Post> timeline = new ArrayList<>();
		User u = userRepository.getOne(userId);
		System.out.println("nb follows :"+u.getFollowing().size());
		 for (Follow f: u.getFollowing()) {
			 timeline.addAll(postRepository.getAllPostsByUserId(f.getFollowId().getFollowed().getId()));
			 
		 }
		
		return mapper.mapTo(timeline);
	}
	
}
