package com.pcelta.foobar.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 8297144483906298344L;

	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="created_at")
	private Calendar createdAt;
	
	@ManyToOne
	@JoinColumn(name="credit_card_id", referencedColumnName="id")
	private CreditCard creditCard;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Calendar getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
}
