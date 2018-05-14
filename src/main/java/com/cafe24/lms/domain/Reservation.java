package com.cafe24.lms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_no")
	private Long no;
	@Column(name = "order_no")
	private Integer orderNo;

	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	@ManyToOne
	@JoinColumn(name = "rent_no")
	private Rent rent;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {

		if (this.rent != null) {
			this.rent.getReservations().remove(this);
		}

		this.rent = rent;

		if (rent != null) {
			rent.getReservations().add(this);
		}
	}

	@Override
	public String toString() {
		return "Reservation [no=" + no + ", orderNo=" + orderNo + "]";
	}

}
