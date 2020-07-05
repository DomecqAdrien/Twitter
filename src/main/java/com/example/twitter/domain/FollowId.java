package com.example.twitter.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Embeddable
public class FollowId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3030475631573445635L;

	@OneToOne
	@JsonManagedReference
	@JsonIgnoreProperties({"favs","reposts","followers","following"})
	private User following;
	
	@OneToOne
	@JsonManagedReference
	@JsonIgnoreProperties({"favs","reposts","followers","following"})
	private User followed; 

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FollowId other = (FollowId) obj;
		if (followed == null) {
			if (other.followed != null)
				return false;
		} else if (!followed.equals(other.followed))
			return false;
		if (following == null) {
			if (other.following != null)
				return false;
		} else if (!following.equals(other.following))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((followed == null) ? 0 : followed.hashCode());
		result = prime * result + ((following == null) ? 0 : following.hashCode());
		return result;
	}

}
