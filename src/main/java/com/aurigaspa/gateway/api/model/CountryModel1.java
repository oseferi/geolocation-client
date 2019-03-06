package com.aurigaspa.gateway.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryModel1 {

	@JsonProperty(value = "geonameId")
	private String geonameId;

	@JsonProperty(value = "countryCode")
	private String countryCode;

	@JsonProperty(value = "countryName")
	private String countryName;

	public String getGeonameId() {
		return geonameId;
	}

	public void setGeonameId(String geonameId) {
		this.geonameId = geonameId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "CountryModel [geonameId=" + geonameId + ", countryCode=" + countryCode + ", countryName=" + countryName
				+ "]";
	}
	
	

}
