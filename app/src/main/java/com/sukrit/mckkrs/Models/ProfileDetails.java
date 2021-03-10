package com.sukrit.mckkrs.Models;

public class ProfileDetails {
    public ProfileDetails() {
    }

    public ProfileDetails(String message, String email, String streetNo,
                          String phone, String name, String streetName,
                          String city, String state, String zipcode,
                          String country, String address) {
        this.message = message;
        this.email = email;
        StreetNo = streetNo;
        this.phone = phone;
        this.name = name;
        StreetName = streetName;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetNo() {
        return StreetNo;
    }

    public void setStreetNo(String streetNo) {
        StreetNo = streetNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    String message;
    String email;
    String StreetNo;
    String phone;
    String name;
    String StreetName;
    String city;
    String state;
    String zipcode;
    String country;
    String address;

}
