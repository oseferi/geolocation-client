package com.aurigaspa.gateway.api.client.impl;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.aurigaspa.gateway.api.model.CityModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


public class MapClientImpl {
	private final static String SAMPLE_URL = "http://overpass-api.de/api/interpreter?data=%5Bout%3Ajson%5D%3Barea%5B%22ISO3166-1%22%3D%22AL%22%5D%5Badmin_level%3D2%5D%3B%28node%5B%22place%22%3D%22town%22%5D%28area%29%3B%29%3Bout%3B";

	public static void getCities() {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CityModel> locations = null;
		try {
			response = client.execute(new HttpGet(SAMPLE_URL));
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			JsonNode responseNode = mapper.readTree(responseString);
			JsonNode dataNode = responseNode.get("elements");
			TypeFactory typeFactory = mapper.getTypeFactory();
			locations = mapper.readValue(mapper.writeValueAsString(dataNode), typeFactory.constructCollectionType(List.class, CityModel.class));
			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status Code: " + statusCode);
		System.out.println("Response : " + responseString);
		System.out.println("Locations : "+ locations.toString());
	}

	public static void main(String args[]) {
		getCities();
	}
}
