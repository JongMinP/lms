package com.cafe24.lms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rent_no")
	private Long no;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "rent_date")
	private Date rentDate;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "return_date")
	private Date returnDate;

	@ManyToOne
	@JoinColumn(name = "user_no")
	private User user;

	@ManyToOne
	@JoinColumn(name = "item_no")
	private Item item;

	@OneToMany(mappedBy = "rent", fetch = FetchType.LAZY)
	private List<Reservation> reservations;

	public Rent() {
		reservations = new ArrayList<Reservation>();
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {

		if (this.user != null) {
			this.user.getRents().remove(this);
		}

		this.user = user;

		if (user != null) {
			user.getRents().add(this);
		}
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "Rent [no=" + no + ", rentDate=" + rentDate + ", returnDate=" + returnDate + ", user=" + user + ", item="
				+ item + ", reservations=" + reservations + "]";
	}

}
