package com.example.demo.entity;

public class Vehicle extends BaseEntity{
    private String brand_of_transport;
    private String model;
    private String state_number;
    private String color;
    private int worker_id;

    public Vehicle(Integer id, String brand_of_transport, String model, String state_number, String color, int worker_id) {
        super(id);
        this.brand_of_transport = brand_of_transport;
        this.model = model;
        this.state_number = state_number;
        this.color = color;
        this.worker_id = worker_id;
    }

    public String getBrand_of_transport() {
        return brand_of_transport;
    }

    public void setBrand_of_transport(String brand_of_transport) {
        this.brand_of_transport = brand_of_transport;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getState_number() {
        return state_number;
    }

    public void setState_number(String state_number) {
        this.state_number = state_number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }
}
