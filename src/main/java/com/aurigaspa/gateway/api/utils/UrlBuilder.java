package com.aurigaspa.gateway.api.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlBuilder {
	public static String buildCountryUrl(String query) {
		StringBuilder urldata = new StringBuilder();
		String encodedUrlData = "";

		urldata.append(OverpassApiConstants.OUTPUT_FORMAT);

		// Country Conditions
		urldata.append(OverpassApiConstants.OBJECT_TYPE_RELATION);
		urldata.append(OverpassApiConstants.TAG_TYPE_BOUNDARY);
		urldata.append(OverpassApiConstants.TAG_ADMINISTRATIVE_BOUNDARY);
		urldata.append(OverpassApiConstants.TAG_ADMIN_LEVEL_2);

		if (query != null && !query.isEmpty()) {
			urldata.append("[\"name:en\" ~ \"^" + query + "\", i];");
		} else {
			urldata.append("[\"name:en\" ~ \"^\", i];");
		}
		urldata.append(OverpassApiConstants.CONVERT_OUTPUT_FOR_COUNTRY);
		urldata.append(OverpassApiConstants.OUTPUT_STATEMENT);

		System.out.println(urldata.toString());
		try {
			encodedUrlData = URLEncoder.encode(urldata.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// return the full path with the data encoded
		return OverpassApiConstants.BASE_URL + encodedUrlData;
	}

	public static String buildCityUrl(String country) {
		StringBuilder urldata = new StringBuilder();
		String encodedUrlData = "";

		urldata.append(OverpassApiConstants.OUTPUT_FORMAT);

		// Area's Conditions
		urldata.append(OverpassApiConstants.OBJECT_TYPE_AREA);
		urldata.append("[\"name:en\"=\"" + country + "\"]");
		urldata.append(OverpassApiConstants.TAG_ADMIN_LEVEL_2);
		urldata.append(";");

		// City's conditions
		urldata.append(OverpassApiConstants.OBJECT_TYPE_NODE);
		urldata.append(OverpassApiConstants.TAG_PLACE_TOWN_OR_CITY);
		urldata.append(";");

		urldata.append(OverpassApiConstants.OUTPUT_STATEMENT);

		System.out.println(urldata.toString());
		try {
			encodedUrlData = URLEncoder.encode(urldata.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// return the full path with the data encoded
		return OverpassApiConstants.BASE_URL + encodedUrlData;

	}

	public static String buildAddressUrl(String city, String query) {
		StringBuilder urldata = new StringBuilder();
		String encodedUrlData = "";

		urldata.append(OverpassApiConstants.OUTPUT_FORMAT);

		// Area's Conditions
		urldata.append(OverpassApiConstants.OBJECT_TYPE_AREA);
		urldata.append("[name=\"" + city + "\"];");

		//Address Conditions
		urldata.append(OverpassApiConstants.OBJECT_TYPE_WAY);
		if (query != null && !query.isEmpty()) {

			urldata.append("[\"addr:street\"~\"" + query + "\",i](area);");

		} else {
			urldata.append("[\"addr:street\"~\"\",i](area);");
		}
		urldata.append(OverpassApiConstants.OUTPUT_TAG_STATEMENT);

		System.out.println(urldata.toString());
		try {
			encodedUrlData = URLEncoder.encode(urldata.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// return the full path with the data encoded
		return OverpassApiConstants.BASE_URL + encodedUrlData;
	}
}
