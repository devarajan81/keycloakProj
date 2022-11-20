package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ClientMapper implements RowMapper<Client> {

	public Client mapRow(ResultSet resultSet, int i) throws SQLException {

		Client client = new Client();
		client.setId(resultSet.getString("id"));
		return client;
	}
}
