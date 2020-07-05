package com.example.twitter.model;

import java.sql.Timestamp;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class NewTweetDTO {
	
	public long id;
	public String message;
	public int origin;
	public Timestamp timestamp;
}
