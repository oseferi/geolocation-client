package com.aurigaspa.gateway.api.utils;

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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class NominatimClientTest {

	private static final Logger logger = LogManager.getLogger(NominatimClientTest.class);
	private static final String GET_ADDRESSES_BY_CITY_COUNTRY = "street=${address}&city=${cityName}&country=${countryName}&addressdetails=1&format=json";
	private static final String BASE_URL = "https://nominatim.openstreetmap.org/search?";
	
	
	public static void main(String[] args) {
		String url = buildAddressUrl("b","Tirana","Albania");
		System.out.println("URL : "+ url);
		String responseString = buildRequest(url);
		System.out.println(responseString);
		List<AddressNominatimModel> locations = extractData(AddressNominatimModel.class, responseString);
		System.out.println("Address: "+ locations.toString());
	}
	
	private static String buildRequest(String url) {
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
//			JsonNode dataNode = responseNode.get("elements");
			TypeFactory typeFactory = mapper.getTypeFactory();
			return mapper.readValue(mapper.writeValueAsString(responseNode),
					typeFactory.constructCollectionType(List.class, clazz));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public static String buildAddressUrl(String address, String city, String country) {
		if(address.isEmpty() || city.isEmpty() || country.isEmpty()){
			return null;
		}
		String urlData = GET_ADDRESSES_BY_CITY_COUNTRY.replace("${address}", address).replace("${cityName}", city).replace("${countryName}", country);
		System.out.println(urlData);
//		String encodedUrlData = UrlBuilder.encodeUrl(urlData);

		// return the full path with the data encoded
		return BASE_URL + urlData;
	}
	
	
}
