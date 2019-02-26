package com.aurigaspa.gateway.api.client.impl;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aurigaspa.gateway.api.client.MapClient;
import com.aurigaspa.gateway.api.model.AddressModel;
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CountryModel;
import com.aurigaspa.gateway.api.utils.UrlBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class MapClientImpl implements MapClient {

	private static final Logger logger = LogManager.getLogger(MapClientImpl.class);

	@Override
	public List<CountryModel> getCountries(String query) {

		String url = UrlBuilder.buildCountryUrl(query);
		String responseString = buildRequest(url);
		List<CountryModel> locations = extractData(CountryModel.class, responseString);
		return locations;
	}

	@Override
	public List<CityModel> getCities(String country) {

		String url = UrlBuilder.buildCityUrl(country);
		String responseString = buildRequest(url);
		List<CityModel> locations = extractData(CityModel.class, responseString);
		return locations;

	}

	@Override
	public List<AddressModel> getAddresses(String city, String query) {
		String url = UrlBuilder.buildAddressUrl(city, query);
		String responseString = buildRequest(url);
		List<AddressModel> locations = extractData(AddressModel.class, responseString);

		return locations;

	}

	private String buildRequest(String url) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		HttpEntity entity = null;
		String responseString = "";
		try {
			response = client.execute(new HttpGet(url));
			entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			int statusCode = response.getStatusLine().getStatusCode();

			logger.debug("Status Code: " + statusCode);
			logger.debug("Response : " + responseString);
//			System.out.println("Status Code: " + statusCode);
//			System.out.println("Response : " + responseString);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseString;

	}

	private static <T> List<T> extractData(Class<T> clazz, String jsonData) {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode responseNode;
		try {
			responseNode = mapper.readTree(jsonData);
			JsonNode dataNode = responseNode.get("elements");
			TypeFactory typeFactory = mapper.getTypeFactory();
			return mapper.readValue(mapper.writeValueAsString(dataNode),
					typeFactory.constructCollectionType(List.class, clazz));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

//	public static void main(String args[]) {
//		getCities("Italy");
//
////		getAddresses("Tiranë","papa");
////		getCountries(null);
//	}
}
