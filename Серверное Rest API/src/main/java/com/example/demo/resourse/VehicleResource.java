package com.example.demo.resourse;

import com.example.demo.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class VehicleResource implements Serializable {
    private Integer id;
    private String brand_of_transport;
    private String model;
    private String state_number;
    private String color;
    private int worker_id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private WorkerResource worker;

    public VehicleResource(){}

    public VehicleResource(Vehicle vehicle){
        this.id = vehicle.getId();
        this.brand_of_transport = vehicle.getBrand_of_transport();
        this.model = vehicle.getModel();
        this.state_number = vehicle.getState_number();
        this.color = vehicle.getColor();
        this.worker_id = vehicle.getWorker_id();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public WorkerResource getWorker() {
        return worker;
    }

    public void setWorker(WorkerResource worker) {
        this.worker = worker;
    }

    public Vehicle toEntity(){
        return new Vehicle(
                this.id,
                this.brand_of_transport,
                this.model,
                this.state_number,
                this.color,
                this.worker_id
        );
    }
}
