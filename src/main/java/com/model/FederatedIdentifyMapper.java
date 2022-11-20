package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FederatedIdentifyMapper implements RowMapper<Federated_Identity> {

	public Federated_Identity mapRow(ResultSet resultSet, int i) throws SQLException {

		Federated_Identity federatedIdentity = new Federated_Identity();
		federatedIdentity.setUser_id(resultSet.getString("user_id"));
		federatedIdentity.setToken(resultSet.getString("token"));
		federatedIdentity.setFederated_user_id(resultSet.getString("federated_username"));
		federatedIdentity.setRealm_id("realm_id");
		federatedIdentity.setFederated_username(resultSet.getString("federated_username"));
		federatedIdentity.setIndentity_Provider(resultSet.getString("identity_provider"));
		return federatedIdentity;
	}
}
