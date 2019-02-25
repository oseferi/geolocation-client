package com.aurigaspa.gateway.api.client.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.aurigaspa.gateway.api.model.AddressModel;
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CountryModel;
import com.aurigaspa.gateway.api.utils.OverpassApiConstants;
import com.aurigaspa.gateway.api.utils.UrlBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


public class MapClientImpl {
	
	
	private static final Logger logger = LogManager.getLogger(MapClientImpl.class);
	
//	public static void getCities(String country, String query) {
//		HttpClient client = HttpClientBuilder.create().build();
//		HttpResponse response = null;
//		HttpEntity entity = null;
//		String responseString = "";
//		ObjectMapper mapper = new ObjectMapper();
//		List<CityModel> locations = null;
//		String url = UrlBuilder.buildCityUrl(country, query);
//		try {
//			response = client.execute(new HttpGet(OverpassApiConstants.SAMPLE_URL));
//			entity = response.getEntity();
//			responseString = EntityUtils.toString(entity, "UTF-8");
//			JsonNode responseNode = mapper.readTree(responseString);
//			JsonNode dataNode = responseNode.get("elements");
//			TypeFactory typeFactory = mapper.getTypeFactory();
//			locations = mapper.readValue(mapper.writeValueAsString(dataNode), typeFactory.constructCollectionType(List.class, CityModel.class));
//			
//			
//		} catch (ClientProtocolException e) {
//			logger.error(e.getMessage());
//		} catch (IOException e) {
//			logger.error(e.getMessage());
//		}
//		int statusCode = response.getStatusLine().getStatusCode();
//		logger.debug("Status Code: " + statusCode);
//		logger.debug("Response : " + responseString);
//		logger.debug("Cities : "+ locations.toString());
//		
//	}

	public static void getCities(String country) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CityModel> locations = null;
		String url = UrlBuilder.buildCityUrl(country);
		try {
			response = client.execute(new HttpGet(url));
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			JsonNode responseNode = mapper.readTree(responseString);
			JsonNode dataNode = responseNode.get("elements");
			TypeFactory typeFactory = mapper.getTypeFactory();
			locations = mapper.readValue(mapper.writeValueAsString(dataNode), typeFactory.constructCollectionType(List.class, CityModel.class));
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		int statusCode = response.getStatusLine().getStatusCode();
		logger.debug("Status Code: " + statusCode);
		logger.debug("Response : " + responseString);
//		logger.debug("Cities : "+ locations.toString());
		System.out.println("Status Code: " + statusCode);
		System.out.println("Response : " + responseString);
		System.out.println("Cities : "+ locations.toString());
		System.out.println("Number of rows: "+locations.size());
		
	}
	
	public static void getCountries(String query) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CountryModel> locations = null;
		String url = UrlBuilder.buildCountryUrl(query);
		
		try {
			response = client.execute(new HttpGet(url));
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			JsonNode responseNode = mapper.readTree(responseString);
			JsonNode dataNode = responseNode.get("elements");
			TypeFactory typeFactory = mapper.getTypeFactory();
			locations = mapper.readValue(mapper.writeValueAsString(dataNode), typeFactory.constructCollectionType(List.class, CountryModel.class));
			
			
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		int statusCode = response.getStatusLine().getStatusCode();
		
		logger.debug("Status Code: " + statusCode);
		logger.debug("Response : " + responseString);
		logger.debug("Countriess : "+ locations.toString());
		System.out.println("Countriess : "+ locations.toString());
		System.out.println("Number of rows : "+ locations.size());
		
	}
	
	public static void getAddresses(String city,String query) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = "";
		ObjectMapper mapper = new ObjectMapper();
		List<AddressModel> locations = null;
		String url = UrlBuilder.buildAddressUrl(city,query);
		
		try {
			response = client.execute(new HttpGet(url));
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			JsonNode responseNode = mapper.readTree(responseString);
			JsonNode dataNode = responseNode.get("elements");
			TypeFactory typeFactory = mapper.getTypeFactory();
			locations = mapper.readValue(mapper.writeValueAsString(dataNode), typeFactory.constructCollectionType(List.class, AddressModel.class));
			
			
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		int statusCode = response.getStatusLine().getStatusCode();
		
		logger.debug("Status Code: " + statusCode);
		logger.debug("Response : " + responseString);
		logger.debug("Countriess : "+ locations.toString());
		System.out.println("Status Code: " + statusCode);
		System.out.println("Response : " + responseString);
		System.out.println("Countriess : "+ locations.toString());
		System.out.println("Number of Rows : "+ locations.size());
		
	}
	
	
	public static void main(String args[]) {
//		getCities("Italy");
//		getAddresses("Tiranë","papa");
		getCountries(null);
	}
}
