package com.sukrit.mckkrs.Models;

public class BookPrivateStudy {

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

    public BookPrivateStudy() {
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

    public String getActivity_status() {
        return activity_status;
    }

    public void setActivity_status(String activity_status) {
        this.activity_status = activity_status;
    }

    public String getOmara_code() {
        return omara_code;
    }

    public void setOmara_code(String omara_code) {
        this.omara_code = omara_code;
    }

    public String getId_activity_type() {
        return id_activity_type;
    }

    public void setId_activity_type(String id_activity_type) {
        this.id_activity_type = id_activity_type;
    }

    public String getActivity_mandatory() {
        return activity_mandatory;
    }

    public void setActivity_mandatory(String activity_mandatory) {
        this.activity_mandatory = activity_mandatory;
    }

    public String getDisplay_status() {
        return display_status;
    }

    public void setDisplay_status(String display_status) {
        this.display_status = display_status;
    }

    public String getGlobalDuration() {
        return globalDuration;
    }

    public void setGlobalDuration(String globalDuration) {
        this.globalDuration = globalDuration;
    }

    public String getGlobalPoint() {
        return globalPoint;
    }

    public void setGlobalPoint(String globalPoint) {
        this.globalPoint = globalPoint;
    }

    public String getGlobalPrice() {
        return globalPrice;
    }

    public void setGlobalPrice(String globalPrice) {
        this.globalPrice = globalPrice;
    }

    public BookPrivateStudy(String id_activity, String activity_name, String activity_status,
                            String omara_code, String id_activity_type, String activity_mandatory,
                            String display_status, String globalDuration, String globalPoint, String globalPrice) {
        this.id_activity = id_activity;
        this.activity_name = activity_name;
        this.activity_status = activity_status;
        this.omara_code = omara_code;
        this.id_activity_type = id_activity_type;
        this.activity_mandatory = activity_mandatory;
        this.display_status = display_status;
        this.globalDuration = globalDuration;
        this.globalPoint = globalPoint;
        this.globalPrice = globalPrice;
    }

    String id_activity;
    String activity_name;
    String activity_status;
    String omara_code;
    String id_activity_type;
    String activity_mandatory;
    String display_status;
    String globalDuration;
    String globalPoint;
    String globalPrice;

}
