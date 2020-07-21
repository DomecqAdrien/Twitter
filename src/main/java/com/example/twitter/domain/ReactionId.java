package com.example.twitter.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Embeddable
public class ReactionId implements Serializable {
	
	private static final long serialVersionUID = 8838333218237389453L;

	@OneToOne
	@JsonIgnoreProperties({"favs","reposts","followers","following"})
    private User user;
 
    @OneToOne
    @JsonIgnoreProperties({"favs","reposts","user","reponses"})
    private Post post;
    
    private boolean isFav;

	

}
