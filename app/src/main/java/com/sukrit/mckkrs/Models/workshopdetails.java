package com.sukrit.mckkrs.Models;

public class workshopdetails {

    String id_schedule;
    String date_schedule;
    String city_schedule;
    String venue;
    String id_activity;
    String activity_name;
    String points;
    String omara_code;
    String price;
    String id;
    String address;

    public workshopdetails(String id_schedule, String date_schedule, String city_schedule, String venue, String id_activity,
                           String activity_name, String points, String omara_code, String price, String id, String address) {
        this.id_schedule = id_schedule;
        this.date_schedule = date_schedule;
        this.city_schedule = city_schedule;
        this.venue = venue;
        this.id_activity = id_activity;
        this.activity_name = activity_name;
        this.points = points;
        this.omara_code = omara_code;
        this.price = price;
        this.id = id;
        this.address = address;
    }

    private boolean isSelected;
    private String animal;

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }


    public workshopdetails() {

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

    public String getId_activity() {
        return id_activity;
    }

    public void setId_activity(String id_activity) {
        this.id_activity = id_activity;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getOmara_code() {
        return omara_code;
    }

    public void setOmara_code(String omara_code) {
        this.omara_code = omara_code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
