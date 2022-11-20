package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dao.FederatedIdentityDAO;
import com.model.Federated_Identity;


public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		FederatedIdentityDAO federatedIdentityDAO = context.getBean(FederatedIdentityDAO.class);
		

		System.out.println("List of Federated Identity is:");

		for (Federated_Identity p : federatedIdentityDAO.getAllFederatedIdentity()) {
			System.out.println(p);
		}


		context.close();
	}
}
