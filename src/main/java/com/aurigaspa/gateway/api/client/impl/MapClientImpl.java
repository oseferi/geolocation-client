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
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CountryModel;
import com.aurigaspa.gateway.api.utils.OverpassApiConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


public class MapClientImpl {
	
	
	private static final Logger logger = LogManager.getLogger(MapClientImpl.class);
	
	public static void getCities() {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CityModel> locations = null;
		try {
			response = client.execute(new HttpGet(OverpassApiConstants.SAMPLE_URL));
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			JsonNode responseNode = mapper.readTree(responseString);
			JsonNode dataNode = responseNode.get("elements");
			TypeFactory typeFactory = mapper.getTypeFactory();
			locations = mapper.readValue(mapper.writeValueAsString(dataNode), typeFactory.constructCollectionType(List.class, CityModel.class));
			
			
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		int statusCode = response.getStatusLine().getStatusCode();
		logger.debug("Status Code: " + statusCode);
		logger.debug("Response : " + responseString);
		logger.debug("Locations : "+ locations.toString());
		
	}

	public static void getCountries(String Query) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CountryModel> locations = null;
		String url = buildCountryUrl(Query);
		
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
		logger.debug("Locations : "+ locations.toString());
		System.out.println("Status Code: " + statusCode);
		System.out.println("Response : " + responseString);
		System.out.println("Row Count : " + locations.size());
		System.out.println("Countries : "+ locations.toString());
	}
	
	private static String buildCountryUrl(String query) {
		StringBuilder urldata = new StringBuilder();
		String encodedUrlData = "";
		urldata.append(OverpassApiConstants.OUTPUT_FORMAT);
		urldata.append(OverpassApiConstants.OBJECT_TYPE_RELATION);
		urldata.append(OverpassApiConstants.TAG_TYPE_BOUNDARY);
		urldata.append(OverpassApiConstants.TAG_ADMINISTRATIVE_BOUNDARY);
		urldata.append(OverpassApiConstants.TAG_ADMIN_LEVEL_2);
		
		if(query != null && !query.isEmpty()) {
			urldata.append("[\"name:en\" ~ \"^"+query+"\", i];");	
		}else {
			urldata.append("[\"name:en\" ~ \"^\", i];");	
		}
		urldata.append(OverpassApiConstants.CONVERT_OUTPUT_FOR_COUNTRY);
		urldata.append(OverpassApiConstants.OUTPUT_STATEMENT);
		
		System.out.println(urldata.toString());
		 try {
			 encodedUrlData = URLEncoder.encode(urldata.toString(), "UTF-8");
			 System.out.println(encodedUrlData);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 //return the full path with the data encoded
		return OverpassApiConstants.BASE_URL + encodedUrlData;
	}
	
	public static void main(String args[]) {
//		getCities();
		getCountries("a");
	}
}
