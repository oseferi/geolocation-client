package com.aurigaspa.gateway.api.controller;

import javax.ws.rs.core.Response;

/**
 * @author oseferi
 *
 */
public interface ApiGateway {

	/**
	 * @return
	 */
	public Response getCountries();
	
	
	/**
	 * @return
	 */
	public Response getCities();
	
	
	/**
	 * @return
	 */
	public Response getAddresses();
}
