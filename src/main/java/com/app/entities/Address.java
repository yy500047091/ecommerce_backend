package com.app.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@Entity
@Table(name="addresses")
public class Address extends BaseEntity {
	
	
	private String address;
	private String city;
	private int pinCode;
	private String state;
	private String country;
	
	@ManyToOne
	@JoinColumn(name="fk_user_id")
	private User user;
}
