package com.example.twitter.model;

import java.sql.Timestamp;
import java.util.List;

import com.example.twitter.domain.Reaction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostDTO {
	
	public UserDTO userId;
	public String message;
	public int origin;
	public Integer postId;
	public Timestamp timeStamp;
	private List<Reaction> favs;
	private List<Reaction> reposts;
}