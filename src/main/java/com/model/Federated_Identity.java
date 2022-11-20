package com.model;

public class Federated_Identity {


	
private String indentity_Provider;
private String realm_id;
private String federated_user_id;
private String federated_username;
private String token;
private String user_id;
/**
 * @return the indentityProvider
 */
public String getIndentity_Provider() {
	return indentity_Provider;
}
/**
 * @param indentityProvider the indentityProvider to set
 */
public void setIndentity_Provider(String indentity_Provider) {
	this.indentity_Provider = indentity_Provider;
}
/**
 * @return the realmId
 */
public String getRealm_id() {
	return realm_id;
}
/**
 * @param realmId the realmId to set
 */
public void setRealm_id(String realm_id) {
	this.realm_id = realm_id;
}
/**
 * @return the federatedUserId
 */
public String getFederated_user_id() {
	return federated_user_id;
}
/**
 * @param federatedUserId the federatedUserId to set
 */
public void setFederated_user_id(String federated_user_id) {
	this.federated_user_id = federated_user_id;
}
/**
 * @return the federatedUserName
 */
public String getFederated_username() {
	return federated_username;
}
/**
 * @param federatedUserName the federatedUserName to set
 */
public void setFederated_username(String federatedUserName) {
	this.federated_username = federatedUserName;
}
/**
 * @return the token
 */
public String getToken() {
	return token;
}
/**
 * @param token the token to set
 */
public void setToken(String token) {
	this.token = token;
}
/**
 * @return the userId
 */
public String getUser_id() {
	return user_id;
}
/**
 * @param userId the userId to set
 */
public void setUser_id(String user_id) {
	this.user_id = user_id;
}

@Override
public String toString() {
	return "Federated_Identity{" + "user_id=" + user_id + ", federated_user_id=" + federated_user_id + 
			", realm_id='" + realm_id + '\'' + ", federated_username='" + federated_username + ", indentity_Provider='" + indentity_Provider
			+  ", token='" + token+ '\'' + '}';
}

}
