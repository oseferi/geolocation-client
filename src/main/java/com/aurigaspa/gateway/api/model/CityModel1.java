package com.aurigaspa.gateway.api.model;

public class CityModel1 {
	
	private String name;
	
	private String countryName;
	
	private String countryCode;
	
	private Double latitude;
	
	private Double longitude;

	public CityModel1(String name, String countryName, String countryCode, Double latitude, Double longitude) {
		super();
		this.name = name;
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "CityModel1 [name=" + name + ", countryName=" + countryName + ", countryCode=" + countryCode
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	

}
