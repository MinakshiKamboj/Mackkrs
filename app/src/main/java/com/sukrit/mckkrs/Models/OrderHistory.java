package com.sukrit.mckkrs.Models;

public class OrderHistory {
    String id_invoice;
    String marn;
    String order_date;
    String payment_date;
    String price;
    String activity_name;

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public OrderHistory(String id_invoice, String marn, String order_date, String payment_date, String price, String activity_name) {
        this.id_invoice = id_invoice;
        this.marn = marn;
        this.order_date = order_date;
        this.payment_date = payment_date;
        this.price = price;
        this.activity_name = activity_name;
    }

    public OrderHistory() {
    }

    public String getId_invoice() {
        return id_invoice;
    }

    public void setId_invoice(String id_invoice) {
        this.id_invoice = id_invoice;
    }

    public String getMarn() {
        return marn;
    }

    public void setMarn(String marn) {
        this.marn = marn;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
