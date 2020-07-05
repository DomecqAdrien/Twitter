package com.example.twitter.domain;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="Post")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JsonIgnoreProperties({"favs","reposts","followers","following"})
	private User user;
	
	private String message;
	private int origin;
	private Timestamp timestamp;
	
	@OneToMany
	@JoinColumn(name="origin",referencedColumnName = "id")
	private List<Post> reponses;
	
	@OneToMany
	@JoinColumn(name="post_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 1")
	private List<Reaction> favs;
	
	
	@OneToMany
	@JoinColumn(name="post_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 0")
	private List<Reaction> reposts;
}
