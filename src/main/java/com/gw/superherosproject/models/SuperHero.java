package com.gw.superherosproject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="superheros")
public class SuperHero {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min= 2, max = 30, message="Name must be between 3-30 characters.")
	private String name;

	@NotNull
	@Size(min= 2, max = 30, message="Super Power must be between 3-30 characters.")
	private String superPower;
	
	@NotNull
	@Size(min= 2, max = 30, message="Weakness must be between 3-30 characters.")
	private String weakness;
	
	@NotNull
	@Size(min= 2, max=1000, message="Origin must be between 3-1000 characters.")
	private String origin;

	@NotNull
	@Max(value=10, message="Strength is on a scale of 1-10!")
	@Min(value= 1, message="Strength is on a scale of 1-10!")
	private Integer strength;
	
	@NotNull(message="Please provide an image link.")
	@Size(max=1000,  message="Origin must be below 1000 characters.")
	private String imageLink;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	
	public SuperHero() {
		super();
	}
	
	
	
	
	
	
	

	





	public String getOrigin() {
		return origin;
	}














	public void setOrigin(String origin) {
		this.origin = origin;
	}














	public String getWeakness() {
		return weakness;
	}














	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}














	public String getImageLink() {
		return imageLink;
	}














	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}














	public Long getId() {
		return id;
	}














	public void setId(Long id) {
		this.id = id;
	}














	public String getName() {
		return name;
	}














	public void setName(String name) {
		this.name = name;
	}














	public String getSuperPower() {
		return superPower;
	}














	public void setSuperPower(String superPower) {
		this.superPower = superPower;
	}














	public Integer getStrength() {
		return strength;
	}














	public void setStrength(Integer strength) {
		this.strength = strength;
	}














	public User getUser() {
		return user;
	}














	public void setUser(User user) {
		this.user = user;
	}














	public Date getCreatedAt() {
		return createdAt;
	}














	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}














	public Date getUpdatedAt() {
		return updatedAt;
	}














	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}














	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
}
