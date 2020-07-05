package com.example.twitter.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.example.twitter.domain.Follow;
import com.example.twitter.domain.FollowId;
import com.example.twitter.domain.User;
import com.example.twitter.functions.CreateCookie;
import com.example.twitter.functions.DateNow;
import com.example.twitter.mappers.UserMapper;
import com.example.twitter.model.RegisterDTO;
import com.example.twitter.model.UserDTO;
import com.example.twitter.repository.FollowRepository;
import com.example.twitter.repository.UserRepository;

@Service
public class UserService {
	
	final AtomicInteger counter = new AtomicInteger();
	final List<User> users = new CopyOnWriteArrayList<>();
	
    @Resource UserRepository userRepository;
    @Resource FollowRepository followRepository;

    @Resource UserMapper mapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return mapper.mapTo(userList);
    }
    public UserDTO getUserById(long id) {
    	User c = userRepository.getOne(id);
    	System.out.println(c.toString());
        return mapper.mapTo(c);
    }
    public RegisterDTO addUser(RegisterDTO userDTO) {
    	User u = userRepository.save(mapper.mapToNewUser(userDTO));
        return mapper.mapToNewUser(u);
    }
    
    public void deleteUser(UserDTO userDTO) {
    	userRepository.delete(mapper.mapTo(userDTO));
    }
    
    public UserDTO login(String username, HttpServletResponse response) {
    	User u = userRepository.getOneByUsername(username);
    	System.out.println(u.getId());
    	CreateCookie.setCookie(response, "username", u.getUsername());
    	CreateCookie.setCookie(response, "userid", u.getId().toString());
    	return mapper.mapTo(u);
    }
    
	public UserDTO followUser(Long idUser, Long idToFollow) {
		User following = userRepository.getOne(idUser);
		User followed = userRepository.getOne(idToFollow);
		System.out.println(idUser);
		
		FollowId fId = new FollowId();
		fId.setFollowed(followed);
		fId.setFollowing(following);
		
		Follow checkF = followRepository.findOneByFollowId(fId);
		
		if(checkF == null) {
			
			System.out.println("null");
			Follow f = new Follow();
			f.setFollowId(fId);
			f.setDateFollow(DateNow.getTimeNow());
			
			followed.getFollowers().add(f);
			
			
			userRepository.save(followed);	
		}
		System.out.println(following.getFollowers().size());
		return mapper.mapTo(following);	
	}

}
