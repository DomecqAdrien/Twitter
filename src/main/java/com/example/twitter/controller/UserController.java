package com.example.twitter.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.twitter.model.UserDTO;
import com.example.twitter.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
		
	@Resource UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<UserDTO> getAll(){
		return userService.getAllUsers();
	}
	
	@RequestMapping(method = RequestMethod.POST, headers = {"Content-type=application/json"}, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO register(@RequestBody UserDTO user) {
		System.out.println(user.toString());
		return userService.addUser(user);	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/login/{userName}")
	public UserDTO login(@PathVariable String userName, HttpServletResponse response){
		return userService.login(userName, response);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public UserDTO getUser(@PathVariable Long id){
		return userService.getUserById(id);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, headers = {"Content-type=application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUser(@RequestBody UserDTO user) {
		userService.deleteUser(user);
		return "User supprimée";
	}
	
	@RequestMapping(value = "/follow/{idToFollow}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO followUser(@CookieValue(value = "userid") String idUser, @PathVariable Long idToFollow) {
		return userService.followUser(Long.parseLong(idUser), idToFollow);
	}
}
