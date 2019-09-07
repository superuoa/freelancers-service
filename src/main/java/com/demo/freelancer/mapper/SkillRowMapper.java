package com.demo.freelancer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.freelancer.model.Skills;

public class SkillRowMapper implements RowMapper<Skills> {

	@Override
	public Skills mapRow(ResultSet rs, int rowNum) throws SQLException {

		Skills s = new Skills();
		s.setId(rs.getInt("id"));
		s.setSkill_name(rs.getString("skill_name"));
		return s;
	}

}
