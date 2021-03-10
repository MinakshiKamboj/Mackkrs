package com.sukrit.mckkrs.Models;

public class OnlineWorkShop {
    String id;
    String activity_name;
    String omara_code;
    String file_name;
    String file_name_2;
    String id_invoice;
    String date_schedule;
    String time;
    String id_client_activity;
    String url;

    public OnlineWorkShop() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getOmara_code() {
        return omara_code;
    }

    public void setOmara_code(String omara_code) {
        this.omara_code = omara_code;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_name_2() {
        return file_name_2;
    }

    public void setFile_name_2(String file_name_2) {
        this.file_name_2 = file_name_2;
    }

    public String getId_invoice() {
        return id_invoice;
    }

    public void setId_invoice(String id_invoice) {
        this.id_invoice = id_invoice;
    }

    public String getDate_schedule() {
        return date_schedule;
    }

    public void setDate_schedule(String date_schedule) {
        this.date_schedule = date_schedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId_client_activity() {
        return id_client_activity;
    }

    public void setId_client_activity(String id_client_activity) {
        this.id_client_activity = id_client_activity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public OnlineWorkShop(String id, String activity_name,
                          String omara_code, String file_name, String file_name_2,
                          String id_invoice, String date_schedule, String time, String id_client_activity, String url) {
        this.id = id;
        this.activity_name = activity_name;
        this.omara_code = omara_code;
        this.file_name = file_name;
        this.file_name_2 = file_name_2;
        this.id_invoice = id_invoice;
        this.date_schedule = date_schedule;
        this.time = time;
        this.id_client_activity = id_client_activity;
        this.url = url;
    }




}
