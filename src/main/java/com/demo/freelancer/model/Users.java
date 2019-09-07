package com.demo.freelancer.model;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5889847590321084275L;
	private long freelancerid;
	private String first_name;
	private String last_name;
	private String email;
	private List<Skills> skills;
	
	public long getFreelancerid() {
		return freelancerid;
	}
	public void setFreelancerid(long freelancerid) {
		this.freelancerid = freelancerid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Skills> getSkills() {
		return skills;
	}
	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}
	
	
}
