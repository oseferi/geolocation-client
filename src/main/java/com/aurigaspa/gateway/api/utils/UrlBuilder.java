package com.aurigaspa.gateway.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlBuilder {

	private static final String GET_COUNTRIES = "[out:json];area[\"type\"=\"boundary\"][\"boundary\"=\"administrative\"][\"admin_level\"=\"2\"][\"name:en\" ~ \"^${countryName}\", i];convert country ::id = id(),name = t[\"name:en\"];out tags;";

	private static final String GET_CITIES_BY_COUNTRY = "[out:json];area[\"name:en\"=\"${countryName}\"][\"admin_level\"=\"2\"];node[\"place\"~\"^town|city$\"][\"name\" ~ \"^${cityName}\", i](area);out center tags;";

	private static final String GET_ADDRESSES_BY_CITY = "[out:json];area[name=\"${cityName}\"];way[\"addr:street\"~\"^${address}\",i](area);out center tags 10;";

	public static String buildCountryUrl(String country) {
		country = country != null ? country : "";
		String urlData = GET_COUNTRIES.replace("${countryName}", country);
		
		System.out.println(urlData.toString());
		String encodedUrlData = encodeUrl(urlData);

		// return the full path with the data encoded
		return OverpassApiConstants.BASE_URL + encodedUrlData;
	}

	public static String buildCityUrl(String country, String city) {
		city = city != null ? city : "";
		String urlData = GET_CITIES_BY_COUNTRY.replace("${countryName}", country).replace("${cityName}", city);
		System.out.println(urlData);
		String encodedUrlData = encodeUrl(urlData);
		
		// return the full path with the data encoded
		return OverpassApiConstants.BASE_URL + encodedUrlData;

	}

	public static String buildAddressUrl(String city, String query) {
		query = query != null ? query : "";
		String urlData = GET_ADDRESSES_BY_CITY.replace("${cityName}", city).replace("${address}", query);
		System.out.println(urlData);
		String encodedUrlData = encodeUrl(urlData);

		// return the full path with the data encoded
		return OverpassApiConstants.BASE_URL + encodedUrlData;
	}

	private static String encodeUrl(String urlData) {
		String encodedUrlData = null;
		try {
			encodedUrlData = URLEncoder.encode(urlData, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedUrlData;
	}
}
