package com.sukrit.mckkrs.Models;

public class FtoFCpdWorkShop {
    String id_schedule;
    String date_schedule;
    String city_schedule;
    String venue;
    String address;
    String total_price;

    public FtoFCpdWorkShop(String id_schedule, String date_schedule, String city_schedule,
                           String venue, String address, String total_price) {
        this.id_schedule = id_schedule;
        this.date_schedule = date_schedule;
        this.city_schedule = city_schedule;
        this.venue = venue;
        this.address = address;
        this.total_price = total_price;
    }

    public FtoFCpdWorkShop() {

    }

    public String getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(String id_schedule) {
        this.id_schedule = id_schedule;
    }

    public String getDate_schedule() {
        return date_schedule;
    }

    public void setDate_schedule(String date_schedule) {
        this.date_schedule = date_schedule;
    }

    public String getCity_schedule() {
        return city_schedule;
    }

    public void setCity_schedule(String city_schedule) {
        this.city_schedule = city_schedule;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
