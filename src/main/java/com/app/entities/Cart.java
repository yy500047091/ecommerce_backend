package com.app.entities;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cart")
public class Cart  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;
	private Date ordered_date;
	
	@ManyToOne
	@JoinColumn(name="fk_prod_id")
	private Product product;
	
	
	@ManyToOne
	@JoinColumn(name="fk_userid")
	private User user;
	
	
	
	
}
