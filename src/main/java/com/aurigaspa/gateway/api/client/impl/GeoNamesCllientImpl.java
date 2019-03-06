package com.aurigaspa.gateway.api.client.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.geonames.InsufficientStyleException;
import org.geonames.InvalidParameterException;
import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.aurigaspa.gateway.api.client.MapClient;
import com.aurigaspa.gateway.api.model.AddressModel;
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CityModel1;
import com.aurigaspa.gateway.api.model.CountryModel1;
import com.aurigaspa.gateway.api.model.RegionModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class GeoNamesCllientImpl implements MapClient {

	public GeoNamesCllientImpl() {
		WebService.setUserName("oseferi");
		// searchCriteria = new ToponymSearchCriteria();
	}

	@Override
	public List<CountryModel1> getCountries(String countryName) {
		
		String responseString = buildRequest("http://api.geonames.org/countryInfoJSON?username=oseferi");
		List<CountryModel1> locations = extractData(CountryModel1.class, responseString);
		if(countryName!=null && !countryName.isEmpty()) {
			locations = locations.stream().filter(loc -> loc.getCountryName().toLowerCase().contains(countryName.toLowerCase())).collect(Collectors.toList());
		}
	
		return locations;
	}
	
	public List<RegionModel> getRegions(CountryModel1 country, String regionName) throws Exception {
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setCountryCode(country.getCountryCode());
		searchCriteria.setFeatureCode("ADM1");
		searchCriteria.setMaxRows(100);
		searchCriteria.setStyle(Style.FULL);
		ToponymSearchResult searchResult = WebService.search(searchCriteria);
		List<RegionModel> regions = searchResult.getToponyms().stream().map(toponym -> fromToponymToModel(toponym)).collect(Collectors.toList());
		if(regionName!=null && !regionName.isEmpty()) {
			regions = regions.stream().filter(region -> region.getName().toLowerCase().contains(regionName.toLowerCase())).collect(Collectors.toList());
		}
		return regions;
	}
	
	private RegionModel fromToponymToModel(Toponym toponym){
		try {
			return new RegionModel(toponym.getGeoNameId()+"", toponym.getName(), toponym.getLatitude(), toponym.getLatitude(), toponym.getAdminCode1(), toponym.getCountryCode());
		} catch (InsufficientStyleException e) {
			// TODO Auto-generated catch block
			return new RegionModel(toponym.getGeoNameId()+"", toponym.getName(), toponym.getLatitude(), toponym.getLatitude(), null, toponym.getCountryCode());
		}
		
	}
	
	public List<RegionModel> getRegions1(String countryCode, String regionName) throws Exception {
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setCountryCode(countryCode);
		searchCriteria.setFeatureCode("ADM1");
		searchCriteria.setName(regionName);
		searchCriteria.setStyle(Style.FULL);
		searchCriteria.setMaxRows(100);
		ToponymSearchResult searchResult = WebService.search(searchCriteria);
		
	    searchResult.getToponyms().stream().forEach(System.out::println);
		
		
		return null;
	}

	// public List<RegionModel> getRegions(){
	// searchCriteria.setFeatureCode("ADM2");
	// searchCriteria.setStyle(Style.FULL);
	// searchCriteria.setCountryCode("AL");
	// }

	public List<CityModel1> getCities1(RegionModel region, String city) throws Exception {
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		searchCriteria.setCountryCode(region.getCountryCode());
		searchCriteria.setAdminCode1(region.getAdminCode1());
		searchCriteria.setFeatureCode("ADM3");
		searchCriteria.setMaxRows(100);
		searchCriteria.setStyle(Style.FULL);
		ToponymSearchResult searchResult = WebService.search(searchCriteria);
		
	    List<CityModel1> cities = searchResult.getToponyms().stream().map(toponym -> fromToponymToCityModel(toponym)).collect(Collectors.toList());
		return cities;
	}

	private CityModel1 fromToponymToCityModel(Toponym toponym) {
		// TODO Auto-generated method stub
		return new CityModel1(toponym.getName(), toponym.getCountryName(), toponym.getCountryCode(), toponym.getLatitude(), toponym.getLongitude());
	}

	@Override
	public List<AddressModel> getAddresses(String city, String address) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		/*WebService.setUserName("oseferi"); // add your username here
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
		 searchCriteria.setCountryCode("IT");
//		 searchCriteria.setNameStartsWith("Ba");
//		searchCriteria.setNameEquals("Vorës");
		 searchCriteria.setAdminCode1("13");
		searchCriteria.setFeatureCode("ADM3");
//		searchCriteria.setCountryCode("AL");
		searchCriteria.setStyle(Style.FULL);
		searchCriteria.setMaxRows(1000);
		// ToponymSearchResult searchResult = WebService.children(6255148, "en",
		// Style.FULL);
		ToponymSearchResult searchResult = WebService.search(searchCriteria);
		for (Toponym toponym : searchResult.getToponyms()) {
//			System.out.println(toponym.getGeoNameId() + "| " + toponym.getName() + ", " + toponym.getCountryName()
//					+ ", " + toponym.getAlternateNames());

			 System.out.print("Id : " +toponym.getGeoNameId());
			 System.out.print(", Name : " +toponym.getName());
			 System.out.print(", Latitude : " +toponym.getLatitude());
			 System.out.print(", Longitude : " +toponym.getLongitude());
			 System.out.print("Feature Code : "+toponym.getFeatureCode());
			 System.out.print("Feature Code Name: "+toponym.getFeatureCodeName());
			 System.out.print(", acode1 : " +toponym.getAdminCode1());
			 System.out.print(", aname1 : " +toponym.getAdminName1());
			 System.out.print(", acode2 : " +toponym.getAdminCode2());
			 System.out.print(", aname2 : " +toponym.getAdminName2());
			 System.out.print(", acode3 : " +toponym.getAdminCode3());
			 System.out.println(", aname3 : " +toponym.getAdminName3());
//			 System.out.print(", AdminCode4 : " +toponym.getAdminCode4());
//			 System.out.print(", AdminName4 : " +toponym.getAdminName4());
//			 System.out.print(", AdminCode5 : " +toponym.getAdminCode5());
//			 System.out.println(", AdminName5 : " +toponym.getAdminName5());

		}*/
		/*long startTime = System.currentTimeMillis();
		GeoNamesCllientImpl impl = new GeoNamesCllientImpl();
		//impl.getCountries("alb").stream().forEach(System.out::println);
		impl.getRegions1("IT", "");
		 long stopTime = System.currentTimeMillis();
	      long elapsedTime = stopTime - startTime;
	      System.out.println(elapsedTime);*/
//	      
//	  	long startTime1 = System.currentTimeMillis();
//		//impl.getCountries("alb").stream().forEach(System.out::println);
//		 impl.getRegions1("US", "new");
//		 long stopTime1 = System.currentTimeMillis();
//	      long elapsedTime1 = stopTime1 - startTime1;
//	      System.out.println(elapsedTime1);
//	      
//	  	long startTime2 = System.currentTimeMillis();
//		//impl.getCountries("alb").stream().forEach(System.out::println);
//		 impl.getRegions("US", "Ala");
//		 long stopTime2 = System.currentTimeMillis();
//	      long elapsedTime2 = stopTime2 - startTime2;
//	      System.out.println(elapsedTime2);
		
		GeoNamesCllientImpl impl = new GeoNamesCllientImpl();
		impl.getCountries("alb").stream().forEach(System.out::println);
		
		CountryModel1 country = new CountryModel1();
		country.setCountryCode("IT");
		country.setCountryName("Italy");
		country.setGeonameId("3175395");
		
		List<RegionModel> regions = impl.getRegions(country, "Apulia");
		
		regions.stream().forEach(System.out::println);
		
		impl.getCities1(regions.get(0), "").forEach(System.out::println);
		
		
		
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

			//logger.debug("Status Code: " + statusCode);
			//logger.debug("Response : " + responseString);

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
			JsonNode dataNode = responseNode.get("geonames");
			TypeFactory typeFactory = mapper.getTypeFactory();
			return mapper.readValue(mapper.writeValueAsString(dataNode),
					typeFactory.constructCollectionType(List.class, clazz));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CityModel> getCities(String country, String city) {
		// TODO Auto-generated method stub
		return null;
	}

}
