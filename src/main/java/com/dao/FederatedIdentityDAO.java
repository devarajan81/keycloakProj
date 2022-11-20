package com.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.model.Client;
import com.model.Federated_Identity;

@Component
public interface FederatedIdentityDAO {
	
	List<Federated_Identity> getAllFederatedIdentity();
	Federated_Identity getFederatedIdentityByUserId(String userId);
	List<Client> getAllClients();
	
}