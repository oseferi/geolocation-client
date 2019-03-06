package com.aurigaspa.gateway.api.client;

import java.util.List;

import com.aurigaspa.gateway.api.model.AddressModel;
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CountryModel1;

/**
 * @author oseferi
 *
 */
public interface MapClient {

	/**
	 * @return
	 */
	public List<CountryModel1> getCountries(String country);

	/**
	 * @return
	 */
	public List<CityModel> getCities(String country, String city);

	/**
	 * @return
	 */
	public List<AddressModel> getAddresses(String city, String address);

}
