package com.example.twitter.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.twitter.domain.Post;
import com.example.twitter.model.PostDTO;


@Component
public class PostMapper {
	
	public Post mapTo(PostDTO pDTO) {
		Assert.notNull(pDTO, "The user must not be null");
		Post p = new Post();
        // must not set id !
        BeanUtils.copyProperties(pDTO, p);
        return p;
    }

    public PostDTO mapTo(Post p) {
        Assert.notNull(p, "The user must not be null");
        PostDTO pDTO = new PostDTO();
        
        BeanUtils.copyProperties(p, pDTO);
        return pDTO;
    }

    public List<PostDTO> mapTo(List<Post> post) {
        Assert.notNull(post, "The userList must not be null");
        return post.stream().map(Post -> this.mapTo(Post)).collect(Collectors.toList());
    }

}
