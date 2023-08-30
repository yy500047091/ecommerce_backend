package com.app.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="orders")
public class Orders  extends BaseEntity{

	private Date orderDate;
	private float totalAmount;
	
	@Enumerated(EnumType.STRING)
	private TransactionStatus status; 
	
//	@ManyToOne
//	@JoinColumn(name="fk_userId")
//	private User user;
	
	@ManyToOne
	@JoinColumn(name="fk_address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="fk_product_id")
	private Product product;
}
