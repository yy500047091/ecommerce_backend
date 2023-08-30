package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name="users")
public class User extends BaseEntity {
	
	private String username;
	@Column(name="mobileno",length = 15)
	private long mobileno;
	private String email;
    private	String password;
    private String gender;
    
    @Enumerated(EnumType.STRING)
    private Role role;


    
}
