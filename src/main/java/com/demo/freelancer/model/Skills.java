package com.demo.freelancer.model;

import java.io.Serializable;

public class Skills implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6337984537347165331L;
	private int id;
	private String skill_name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	
	
	
}
