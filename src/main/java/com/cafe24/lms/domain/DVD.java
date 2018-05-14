package com.cafe24.lms.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("D")
@PrimaryKeyJoinColumn(name = "dvd_no")
public class DVD extends Item {

	private String distributor;

	public String getDistributor() {
		return distributor;
	}

	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}

	@Override
	public String toString() {
		return "DVD [distributor=" + distributor + ", getNo()=" + getNo() + ", getTitle()=" + getTitle()
				+ ", getCategory()=" + getCategory() + ", getStatus()=" + getStatus() + "]";
	}

}
