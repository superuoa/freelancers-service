package com.demo.freelancer.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.demo.freelancer.mapper.SkillRowMapper;
import com.demo.freelancer.mapper.UserRowMapper;
import com.demo.freelancer.model.Skills;
import com.demo.freelancer.model.Users;

@Repository
public class FreelancerServiceImpl implements FreelancerService {

	NamedParameterJdbcTemplate template;

	public FreelancerServiceImpl(NamedParameterJdbcTemplate template) {  
        this.template = template;  
	}

	@Override
	public List<Users> findAll() {
		
		List<Users> list = template.query(
				"select * from users u", new UserRowMapper());
		
		for(Users u:list) {
			
			MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("freelancerid",u.getFreelancerid());
	        
			List<Skills> skills = template.query(
					"select * from skill_user su "
					+" INNER JOIN skill s on s.id = su.skill_id"
					+" where freelancerid=:freelancerid",param, new SkillRowMapper());
			u.setSkills(skills);
		
		}
		
		return list;
	}

	@Override
	public List<Skills> getSkillAll() {
		
		List<Skills> list = template.query(
				"select * from skill", new SkillRowMapper());

		return list;
	}
	
	@Override
	public void insertUser(Users us) {
		System.out.println("create first_name: "+us.getFirst_name());
		final String sql = "insert into users(freelancerid,first_name,last_name,email) values(:freelancerid,:first_name,:last_name,:email)";
        KeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource param = new MapSqlParameterSource();
        	param.addValue("freelancerid", us.getFreelancerid());
        	param.addValue("first_name", us.getFirst_name());
        	param.addValue("last_name", us.getLast_name());
        	param.addValue("email", us.getEmail());
        template.update(sql,param, holder);

	}

	@Override
	public void insertUserSkill(long freelancerid,int skill_id) {
		
		final String sql = "insert into skill_user(freelancerid,skill_id) values(:freelancerid,:skill_id)";
        KeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource param = new MapSqlParameterSource();
        	param.addValue("freelancerid", freelancerid);
        	param.addValue("skill_id", skill_id);
        	
        template.update(sql,param, holder);

	}
	
	@Override
	public void updateUser(Users us) {
		final String sql = "update users set first_name=:first_name, last_name=:last_name, email=:email where freelancerid=:freelancerid";
        KeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource param = new MapSqlParameterSource();
        	param.addValue("first_name", us.getFirst_name());
        	param.addValue("last_name", us.getLast_name());
        	param.addValue("email", us.getEmail());
        	param.addValue("freelancerid",us.getFreelancerid());
        template.update(sql,param, holder);

	}

	@Override
	public void executeUpdateUser(Users us) {
		final String sql = "update users set first_name=:first_name, last_name=:last_name, email=:email where freelancerid=:freelancerid";
	        

		 Map<String,Object> map=new HashMap<String,Object>();  
		 map.put("first_name", us.getFirst_name());
		 map.put("last_name", us.getLast_name());
		 map.put("email", us.getEmail());
		 map.put("freelancerid",us.getFreelancerid());
	
		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  

	}

	@Override
	public void deleteUserById(Users us) {
		 final String sql = "delete from users where freelancerid=:freelancerid";
		 

		 Map<String,Object> map=new HashMap<String,Object>();  
		 map.put("freelancerid", us.getFreelancerid());
	
		 template.execute(sql,map,new PreparedStatementCallback<Object>() {  
			    @Override  
			    public Object doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			        return ps.executeUpdate();  
			    }  
			});  

	}


	@Override
	public Users findById(long id) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("freelancerid",id);
        
		Users u = template.queryForObject("select * from users where freelancerid=:freelancerid", param, new UserRowMapper());
		
		List<Skills> skills = template.query(
				"select * from skill_user su "
				+" INNER JOIN skill s on s.id = su.skill_id"
				+" where freelancerid=:freelancerid",param, new SkillRowMapper());
		u.setSkills(skills);
		
		return u;
	}

}
