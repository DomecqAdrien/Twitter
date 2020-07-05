package com.example.twitter.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@Entity
@Table(name="User")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String username;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="followed_id", referencedColumnName="id")
	@JsonIgnoreProperties({"following"})
	private List<Follow> followers;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="following_id", referencedColumnName="id")
	@JsonIgnoreProperties({"followed"})
	private List<Follow> following;
	
	@OneToMany
	@JoinColumn(name="user_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 1")
	private List<Reaction> favs;
	
	@OneToMany
	@JoinColumn(name="user_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 0")
	private List<Reaction> reposts;

}
