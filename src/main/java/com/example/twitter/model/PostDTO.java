package com.example.twitter.model;

import java.sql.Timestamp;
import java.util.List;

import com.example.twitter.domain.Post;
import com.example.twitter.domain.Reaction;
import com.example.twitter.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostDTO {
	
	public long id;
	@JsonIgnoreProperties({"favs","reposts","followers","following"})
	public User user;
	public String message;
	public int origin;
	public Timestamp timestamp;
	private List<Post> reponses;
	@JsonIgnoreProperties({"User.favs","User.reposts"})
	private List<Reaction> favs;
	@JsonIgnoreProperties({"User.favs","User.reposts"})
	private List<Reaction> reposts;
}