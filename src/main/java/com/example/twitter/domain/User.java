package com.example.twitter.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name="User")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String userName;
	private String profileName;
	
	@OneToMany
	@JoinColumn(name="followed_id", referencedColumnName="id")
	private List<Follow> followers;
	
	@OneToMany
	@JoinColumn(name="following_id", referencedColumnName="id")
	private List<Follow> following;
	
	@OneToMany
	@JoinColumn(name="user_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 1")
	@JsonManagedReference
	private List<Reaction> favs;
	
	@OneToMany
	@JoinColumn(name="user_id",referencedColumnName = "id")
	@Where(clause = "is_fav = 0")
	@JsonManagedReference
	private List<Reaction> reposts;

}
