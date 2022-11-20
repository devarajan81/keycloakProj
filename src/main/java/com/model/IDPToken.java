package com.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IDPToken implements java.io.Serializable{
	private String access_token;
	private String expires_in;
	private String refresh_expires_in;
	private String id_token;
	private Long accessTokenExpiration;
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	/**
	 * @return the expires_in
	 */
	public String getExpires_in() {
		return expires_in;
	}
	/**
	 * @param expires_in the expires_in to set
	 */
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	/**
	 * @return the refresh_expires_in
	 */
	public String getRefresh_expires_in() {
		return refresh_expires_in;
	}
	/**
	 * @param refresh_expires_in the refresh_expires_in to set
	 */
	public void setRefresh_expires_in(String refresh_expires_in) {
		this.refresh_expires_in = refresh_expires_in;
	}
	/**
	 * @return the id_token
	 */
	public String getId_token() {
		return id_token;
	}
	/**
	 * @param id_token the id_token to set
	 */
	public void setId_token(String id_token) {
		this.id_token = id_token;
	}
	/**
	 * @return the accessTokenExpiration
	 */
	public Long getAccessTokenExpiration() {
		return accessTokenExpiration;
	}
	/**
	 * @param accessTokenExpiration the accessTokenExpiration to set
	 */
	public void setAccessTokenExpiration(Long accessTokenExpiration) {
		this.accessTokenExpiration = accessTokenExpiration;
	}
}
