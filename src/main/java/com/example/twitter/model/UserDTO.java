package com.example.twitter.model;

import java.util.List;

import com.example.twitter.domain.Follow;
import com.example.twitter.domain.Reaction;

import lombok.Data;

@Data
public class UserDTO {

	public Long id;
	public String username;
	private List<Follow> followers;
	private List<Follow> following;
	private List<Reaction> favs;
	private List<Reaction> reposts;

}
