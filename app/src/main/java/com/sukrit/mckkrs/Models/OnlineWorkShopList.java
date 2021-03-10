package com.sukrit.mckkrs.Models;

public class OnlineWorkShopList {
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public boolean getSelected() {
        return isSelected;
    }



    public OnlineWorkShopList(String id, String date_schedule,
                              String activity_id, String activity_name,
                              String time, String capacity, String price, boolean isSelected) {
        this.id = id;
        this.date_schedule = date_schedule;
        this.activity_id = activity_id;
        this.activity_name = activity_name;
        this.time = time;
        this.capacity = capacity;
        this.price = price;
        this.isSelected = isSelected;
    }

    String id;
    String date_schedule;

    public OnlineWorkShopList() {
    }

    String activity_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate_schedule() {
        return date_schedule;
    }

    public void setDate_schedule(String date_schedule) {
        this.date_schedule = date_schedule;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String activity_name;
    String time;
    String capacity;
    String price;

}
