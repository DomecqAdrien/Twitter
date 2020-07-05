package com.example.twitter.domain;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Follow {
	
	@EmbeddedId
	private FollowId followId;
	
	private Timestamp dateFollow;
}
