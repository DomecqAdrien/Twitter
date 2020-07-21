package com.example.twitter.domain;

import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Reaction {
	
	@EmbeddedId
	@JsonIgnoreProperties({"reponses", "fav"})
 	private ReactionId reactionId;
    
    private Timestamp dateReaction;
    
  
}
