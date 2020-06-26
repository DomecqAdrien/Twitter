package com.example.twitter.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="reaction")
public class Reaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
    @OneToOne
    @JsonIgnore
    private User user;
 
    @OneToOne
    private Post post;
    
    private Timestamp dateReaction;
    
    @JsonIgnore
    private boolean isFav;
}
