package com.aurigaspa.gateway.api.model;

public class RegionModel {
	
	private String geoNameId;
	
	private String name;
	
	private Double latitude;
	
	private Double longitude;
	
	private String adminCode1;
	
	private String countryCode;

	public RegionModel(String geoNameId, String name, Double latitude, Double longitude, String adminCode1, String countryCode) {
		super();
		this.geoNameId = geoNameId;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.adminCode1 = adminCode1;
		this.setCountryCode(countryCode);
	}

	public String getGeoNameId() {
		return geoNameId;
	}

	public void setGeoNameId(String geoNameId) {
		this.geoNameId = geoNameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAdminCode1() {
		return adminCode1;
	}

	public void setAdminCode1(String adminCode1) {
		this.adminCode1 = adminCode1;
	}



	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "RegionModel [geoNameId=" + geoNameId + ", name=" + name + ", latitude=" + latitude + ", longitude="
				+ longitude + ", adminCode1=" + adminCode1 + ", countryCode=" + countryCode + "]";
	}
	
	
	

}
