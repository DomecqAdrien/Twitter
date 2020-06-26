package com.example.twitter.model;

import java.util.List;

import com.example.twitter.domain.Follow;
import com.example.twitter.domain.Reaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserDTO {

	public Long id;
	public String userName;
	public String profileName;
	private List<Follow> followers;
	private List<Follow> following;
	private List<Reaction> favs;
	private List<Reaction> reposts;

}
