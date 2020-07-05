package com.example.twitter.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.twitter.domain.User;
import com.example.twitter.model.RegisterDTO;
import com.example.twitter.model.UserDTO;


@Component
public class UserMapper {
	
	public User mapTo(UserDTO uDTO) {
		Assert.notNull(uDTO, "The user must not be null");
		User u = new User();
		//u.setUserName(uDTO);
        // must not set id !
        BeanUtils.copyProperties(uDTO, u);
        return u;
    }

    public UserDTO mapTo(User u) {
        Assert.notNull(u, "The user must not be null");
        UserDTO uDTO = new UserDTO();
        
        BeanUtils.copyProperties(u, uDTO);
        return uDTO;
    }
    
    public User mapToNewUser(RegisterDTO uDTO) {
		Assert.notNull(uDTO, "The user must not be null");
		User u = new User();
		//u.setUserName(uDTO);
        // must not set id !
        BeanUtils.copyProperties(uDTO, u);
        return u;
    }

    public RegisterDTO mapToNewUser(User u) {
        Assert.notNull(u, "The user must not be null");
        RegisterDTO uDTO = new RegisterDTO();
        
        BeanUtils.copyProperties(u, uDTO);
        return uDTO;
    }

    public List<UserDTO> mapTo(List<User> user) {
        Assert.notNull(user, "The userList must not be null");
        return user.stream().map(User -> this.mapTo(User)).collect(Collectors.toList());
    }

}
