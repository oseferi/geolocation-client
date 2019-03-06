package com.aurigaspa.gateway.api.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressNominatimModel {

	@JsonProperty(value = "type")
	private String type;
	
	@JsonProperty(value = "osm_id")
	private String id;
	
	@JsonProperty(value = "lat")
	private Double lat;
	
	@JsonProperty(value = "lon")
	private Double lon;
	
	@JsonProperty(value = "display_name")
	private String name;
	
	@JsonProperty(value = "address")
	private Address addressDetails;

	public AddressNominatimModel() {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(Address addressDetails) {
		this.addressDetails = addressDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressDetails == null) ? 0 : addressDetails.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lon == null) ? 0 : lon.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressNominatimModel other = (AddressNominatimModel) obj;
		if (addressDetails == null) {
			if (other.addressDetails != null)
				return false;
		} else if (!addressDetails.equals(other.addressDetails))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lon == null) {
			if (other.lon != null)
				return false;
		} else if (!lon.equals(other.lon))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressNominatimModel [type=");
		builder.append(type);
		builder.append(", id=");
		builder.append(id);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lon=");
		builder.append(lon);
		builder.append(", name=");
		builder.append(name);
		builder.append(", addressDetails=");
		builder.append(addressDetails);
		builder.append("]");
		return builder.toString();
	}
	
	
}