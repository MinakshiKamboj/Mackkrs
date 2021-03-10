package com.sukrit.mckkrs.Models;

public class CountryList {
    public CountryList(String id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    public CountryList() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    String id;
    String countryName;
}
