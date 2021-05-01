package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name =  "users")
public class Useraa {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String email;

	private String password;

	@Column(name="roles")
	private Long roleId;

	@ManyToOne
	@JoinColumn(name = "roles", insertable = false, updatable = false)
	private Roles roles;
//	public Useraa() {
//	}

//	public Useraa(String firstName, String lastName, String email, String password, Collection<Role> roles) {
//		super();
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;
//		this.roles = roles;
//	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public Collection<Role> getRoles() {
//		return roles;
//	}
//	public void setRoles(Collection<Role> roles) {
//		this.roles = roles;
//	}

}
