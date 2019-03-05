package com.aurigaspa.gateway.api.client.impl;

import java.util.List;

import org.geonames.InvalidParameterException;
import org.geonames.Style;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.aurigaspa.gateway.api.client.MapClient;
import com.aurigaspa.gateway.api.model.AddressModel;
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CountryModel;

public class GeoNamesCllientImpl implements MapClient {
//	public ToponymSearchCriteria searchCriteria;
	
	public GeoNamesCllientImpl() {
		WebService.setUserName("oseferi");
//		searchCriteria = new ToponymSearchCriteria();
	}
	
	@Override
	public List<CountryModel> getCountries(String country) {
		// TODO Auto-generated method stub
//		ToponymSearchResult searchResult = WebService.children(6255148, "en", Style.FULL);

		return null;
	}

//	public List<RegionModel> getRegions(){
//		searchCriteria.setFeatureCode("ADM2");
//		searchCriteria.setStyle(Style.FULL);
//		searchCriteria.setCountryCode("AL");
//	}
	
	@Override
	public List<CityModel> getCities(String country, String city) {
//		searchCriteria.setFeatureCode("ADM3");
//		searchCriteria.setStyle(Style.FULL);
//		searchCriteria.setCountryCode("AL");
//		ToponymSearchResult searchResult = WebService.search(searchCriteria);
		return null;
	}

	@Override
	public List<AddressModel> getAddresses(String city, String address) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		WebService.setUserName("oseferi"); // add your username here
		ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
//		searchCriteria.setCountryCode("IT");
//		searchCriteria.setNameStartsWith("Ba");
		searchCriteria.setNameEquals("Vorës");
//		searchCriteria.setAdminCode1("865730");
		searchCriteria.setFeatureCode("ADM3");
		searchCriteria.setCountryCode("AL");
		searchCriteria.setStyle(Style.FULL);
		searchCriteria.setMaxRows(500);
//		ToponymSearchResult searchResult = WebService.children(6255148, "en", Style.FULL);
		ToponymSearchResult searchResult = WebService.search(searchCriteria);
		for (Toponym toponym : searchResult.getToponyms()) {
			System.out.println(toponym.getGeoNameId()+ "| " +toponym.getName() + ", " + toponym.getCountryName() + ", "+ toponym.getAlternateNames());
			
//			System.out.print("Id : " +toponym.getGeoNameId());
//			System.out.print(", Name : " +toponym.getName());
//			System.out.print(", Latitude : " +toponym.getLatitude());
//			System.out.print(", Longitude : " +toponym.getLongitude());
//			System.out.print("Feature Code : "+toponym.getFeatureCode());
//			System.out.println("Feature Code Name: "+toponym.getFeatureCodeName());
//			System.out.print(", AdminCode1 : " +toponym.getAdminCode1());
//			System.out.print(", AdminName1 : " +toponym.getAdminName1());
//			System.out.print(", AdminCode2 : " +toponym.getAdminCode2());
//			System.out.print(", AdminName2 : " +toponym.getAdminName2());
//			System.out.print(", AdminCode3 : " +toponym.getAdminCode3());
//			System.out.print(", AdminName3 : " +toponym.getAdminName3());
//			System.out.print(", AdminCode4 : " +toponym.getAdminCode4());
//			System.out.print(", AdminName4 : " +toponym.getAdminName4());
//			System.out.print(", AdminCode5 : " +toponym.getAdminCode5());
//			System.out.println(", AdminName5 : " +toponym.getAdminName5());
			
		}
	}
}
