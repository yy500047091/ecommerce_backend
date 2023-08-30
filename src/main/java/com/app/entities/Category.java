package com.app.entities;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@Entity
@Table(name="category")
public class Category extends BaseEntity{

	private String name;
	private String description;
	 public void setDescription(String description) {
	        this.description = description;
	    }
	
	

}
