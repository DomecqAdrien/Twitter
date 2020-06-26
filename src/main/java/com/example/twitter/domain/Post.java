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

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name="Post")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private User user;
	
	private String message;
	private int origin;
	private Timestamp timestamp;
	
	@OneToMany
	@JoinColumn(name="post_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 1")
	@JsonBackReference
	private List<Reaction> favs;
	
	
	
	@OneToMany
	@JoinColumn(name="post_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 0")
	@JsonBackReference
	private List<Reaction> reposts;
}
