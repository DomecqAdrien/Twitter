package com.example.twitter.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.example.twitter.domain.Post;
import com.example.twitter.functions.DateNow;
import com.example.twitter.mappers.PostMapper;
import com.example.twitter.model.PostDTO;
import com.example.twitter.repository.PostRepository;
import com.example.twitter.repository.UserRepository;

@Service
public class PostService {
	
	final AtomicInteger counter = new AtomicInteger();
	final List<Post> posts = new CopyOnWriteArrayList<>();
	
	@Resource
    private PostRepository postRepository;
	private UserRepository userRepisitory;

    @Resource
    private PostMapper mapper;

    public List<PostDTO> getAllPosts() {
        List<Post> postList = postRepository.findAll();
        return mapper.mapTo(postList);
    }
    public PostDTO getPostById(long id) {
    	Post p = postRepository.getOne(id);
        return mapper.mapTo(p);
    }
    public PostDTO addPost(PostDTO postDTO, long id) {
    	System.out.println(postDTO.toString());
    	Post p = mapper.mapTo(postDTO);
    	p.setTimestamp(DateNow.getTimeNow());
    	p.setUser(userRepisitory.getOne(id));
    	postRepository.save(p);
        return mapper.mapTo(p);
    }
    public void deletePost(PostDTO postDTO) {
    	postRepository.delete(mapper.mapTo(postDTO));
    }
	public List<PostDTO> getPostsByAuthor(Long id) {
		return mapper.mapTo(postRepository.getAllPostsByUserId(id));
	}
	
}
