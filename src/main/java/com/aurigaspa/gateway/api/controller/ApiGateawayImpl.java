package com.aurigaspa.gateway.api.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/api")
public class ApiGateawayImpl implements ApiGateway {

	private static final Logger logger = LogManager.getLogger(ApiGateawayImpl.class);

	@POST
	@Path("/map/city")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response getCities() {
		logger.info("Get Cities");
		return null;
	}

	@POST
	@Path("/map/country")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response getCountries() {
		logger.info("Get Countries");
		return null;
	}
	
	@POST
	@Path("/map/address")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response getAddresses() {
		logger.info("Get Addresses");
		return null;
	}
}
