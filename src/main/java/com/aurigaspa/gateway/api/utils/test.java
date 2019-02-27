package com.aurigaspa.gateway.api.utils;

import java.util.List;

import com.aurigaspa.gateway.api.client.impl.MapClientImpl;
import com.aurigaspa.gateway.api.model.AddressModel;
import com.aurigaspa.gateway.api.model.CityModel;
import com.aurigaspa.gateway.api.model.CountryModel;

public class test {

	public static void main(String[] args) {
		MapClientImpl mp = new MapClientImpl();
//		List<CountryModel> countries = mp.getCountries(null);
//		System.out.println("Countries : " +countries.toString());
//		
//		List<CityModel> cities = mp.getCities("Albania", null);
//		System.out.println("Cities : " + cities.toString());
//		
		List<AddressModel> addresses = mp.getAddresses("Dehli", null);
		System.out.println("Addresses : " + addresses.toString());
	}

}
