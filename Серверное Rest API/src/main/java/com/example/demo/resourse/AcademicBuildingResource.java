package com.example.demo.resourse;

import com.example.demo.entity.AcademicBuilding;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class AcademicBuildingResource implements Serializable {
    private Integer id;
    private String name;
    private String address;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AdmissionResource[] admission;

    public AcademicBuildingResource(){}

    public AcademicBuildingResource(AcademicBuilding academicBuilding){
        this.id = academicBuilding.getId();
        this.name = academicBuilding.getName();
        this.address = academicBuilding.getAddress();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AdmissionResource[] getAdmission() {
        return admission;
    }

    public void setAdmission(AdmissionResource[] admission) {
        this.admission = admission;
    }

   /* public VehicleResource getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleResource vehicle) {
        this.vehicle = vehicle;
    }

    public WorkerResource getWorker() {
        return worker;
    }

    public void setWorker(WorkerResource worker) {
        this.worker = worker;
    }*/

    public AcademicBuilding toEntity(){
        return new AcademicBuilding(
                this.id,
                this.name,
                this.address
        );
    }
}
