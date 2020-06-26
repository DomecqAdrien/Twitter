package com.example.twitter.services;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.example.twitter.domain.Follow;
import com.example.twitter.domain.User;
import com.example.twitter.functions.CreateCookie;
import com.example.twitter.mappers.UserMapper;
import com.example.twitter.model.UserDTO;
import com.example.twitter.repository.UserRepository;

@Service
public class UserService {
	
	final AtomicInteger counter = new AtomicInteger();
	final List<User> users = new CopyOnWriteArrayList<>();
	
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper mapper;

    public List<UserDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return mapper.mapTo(userList);
    }
    public UserDTO getUserById(long id) {
    	User c = userRepository.getOne(id);
        return mapper.mapTo(c);
    }
    public UserDTO addUser(UserDTO userDTO) {
    	User u = userRepository.save(mapper.mapTo(userDTO));
        return mapper.mapTo(u);
    }
    public void deleteUser(UserDTO userDTO) {
    	userRepository.delete(mapper.mapTo(userDTO));
    }
    
    public UserDTO login(String userName, HttpServletResponse response) {
    	User u = userRepository.getOneByUserName(userName);
    	CreateCookie.setCookie(response, "username", u.getUserName());
    	CreateCookie.setCookie(response, "userid", u.getId().toString());
    	return mapper.mapTo(u);
    }
    
	public UserDTO followUser(Long idUser, Long idToFollow) {
		User u = userRepository.getOne(idUser);
		User u2 = userRepository.getOne(idToFollow);
		System.out.println(u2.getProfileName());
		Follow f = new Follow();
		f.setFollowing(u);
		f.setFollowed(u2);
		u.getFollowers().add(f);
		System.out.println(u.getFollowers().size());
		userRepository.save(u);
		return mapper.mapTo(u);
		// TODO Auto-generated method stub
		
	}

}
