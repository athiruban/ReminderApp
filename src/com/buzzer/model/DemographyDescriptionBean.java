package com.buzzer.model;

public class DemographyDescriptionBean implements DemographyDescription {
	private String countryName;
	private String stateName;
	private String cityName;
	private String zipcode;

	DemographyDescriptionBean(String country, String state, String city,
			String zipcode) {
		this.countryName = country;
		this.stateName = state;
		this.cityName = city;
		this.zipcode = zipcode;
	}

	@Override
	public String country() {
		return this.countryName;
	}

	@Override
	public String state() {
		return this.stateName;
	}

	@Override
	public String city() {
		return this.cityName;
	}

	@Override
	public String zipcode() {
		return this.zipcode;
	}
}