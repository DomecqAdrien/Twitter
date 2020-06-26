package com.example.twitter.model;

public class TweetIdDTO {
	public int tweetId;
	
	public static TweetIdDTO create(int tweetid) {
		TweetIdDTO res = new TweetIdDTO();
		res.tweetId = tweetid;
		return res;
	}
}
