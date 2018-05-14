package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("C")
@PrimaryKeyJoinColumn(name = "cd_no")
public class CD extends Item {

	private String artist;

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "CD [artist=" + artist + ", getNo()=" + getNo() + ", getTitle()=" + getTitle() + ", getCategory()="
				+ getCategory() + ", getStatus()=" + getStatus() + "]";
	}

}
