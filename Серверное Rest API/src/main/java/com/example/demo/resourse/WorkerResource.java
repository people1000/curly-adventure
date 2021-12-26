package com.example.demo.resourse;

import com.example.demo.entity.Worker;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class WorkerResource implements Serializable {
    private Integer id;
    private String name;
    private String post;
    private String phone_number;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VehicleResource[] vehicle;

    public WorkerResource(){}

    public WorkerResource(Worker worker){
        this.id = worker.getId();
        this.name = worker.getName();
        this.post = worker.getPost();
        this.phone_number = worker.getPhone_number();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public VehicleResource[] getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleResource[] vehicle) {
        this.vehicle = vehicle;
    }

    public Worker toEntity(){
        return new Worker(
                this.id,
                this.name,
                this.post,
                this.phone_number);
    }
}
