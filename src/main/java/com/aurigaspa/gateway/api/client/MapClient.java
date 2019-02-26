package com.aurigaspa.gateway.api.client;

import java.util.List;

import com.aurigaspa.gateway.api.model.AddressModel;
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CountryModel;

/**
 * @author oseferi
 *
 */
public interface MapClient {

	/**
	 * @return
	 */
	public List<CountryModel> getCountries(String query);

	/**
	 * @return
	 */
	public List<CityModel> getCities(String country);

	/**
	 * @return
	 */
	public List<AddressModel> getAddresses(String city, String query);
}
