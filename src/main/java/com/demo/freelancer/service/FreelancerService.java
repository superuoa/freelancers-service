package com.demo.freelancer.service;

import java.util.List;

import com.demo.freelancer.model.Skills;
import com.demo.freelancer.model.Users;

public interface FreelancerService {

	List<Users> findAll();
	Users findById(long id);
	List<Skills> getSkillAll();
	void insertUser(Users us);
	void insertUserSkill(long freelancerid,int skill_id);
	void updateUser(Users us);
	void executeUpdateUser(Users us);
	public void deleteUserById(Users us);
	
}
