package com.demo.freelancer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.freelancer.model.Users;

public class UserRowMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

		Users u = new Users();
		u.setFreelancerid(rs.getLong("freelancerid"));
		u.setFirst_name(rs.getString("first_name"));
		u.setLast_name(rs.getString("last_name"));
		u.setEmail(rs.getString("email"));
		return u;
	}

}
