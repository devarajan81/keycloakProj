package com.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.model.Client;
import com.model.ClientMapper;
import com.model.FederatedIdentifyMapper;
import com.model.Federated_Identity;

//@Component
public class FederatedIdentityDAOImpl implements FederatedIdentityDAO {

	private final String SQL_GET_ALL = "select * from federated_identity";

	private final String SQL_GET_FED_ENTITY = "select * from federated_identity where user_id=?";

	private final String SQL_GET_ALL_CLIENTS = "select id from client";

	JdbcTemplate jdbcTemplate;

	@Autowired
	public FederatedIdentityDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Federated_Identity> getAllFederatedIdentity() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(SQL_GET_ALL, new FederatedIdentifyMapper());
	}

	@Override
	public List<Client> getAllClients() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(SQL_GET_ALL_CLIENTS, new ClientMapper());
	}

	@Override
	public Federated_Identity getFederatedIdentityByUserId(String userId) {
		// TODO Auto-generated method stub
		try {
			return DataAccessUtils.singleResult(jdbcTemplate.query(SQL_GET_FED_ENTITY, new FederatedIdentifyMapper(),
					new Object[] { userId }));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
