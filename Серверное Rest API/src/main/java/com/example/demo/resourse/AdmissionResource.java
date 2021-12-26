package com.example.demo.resourse;

import com.example.demo.entity.Admission;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdmissionResource implements Serializable {
    private Integer id;
    private Integer academic_building_id;
    private Integer vehicle_id;
    private Timestamp term;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VehicleResource vehicle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AcademicBuildingResource academicBuilding;


    public AdmissionResource(){}

    public AdmissionResource(Admission admission) {
        this.id = admission.getId();
        this.academic_building_id = admission.getAcademic_building_id();
        this.vehicle_id = admission.getVehicle_id();
        this.term = admission.getTerm();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcademic_building_id() {
        return academic_building_id;
    }

    public void setAcademic_building_id(Integer academic_building_id) {
        this.academic_building_id = academic_building_id;
    }

    public Integer getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(Integer vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Timestamp getTerm() {
        return term;
    }

    public void setTerm(Timestamp term) {
        this.term = term;
    }

    public AcademicBuildingResource getAcademicBuilding() {
        return academicBuilding;
    }

    public void setAcademicBuilding(AcademicBuildingResource academicBuilding) {
        this.academicBuilding = academicBuilding;
    }

    public VehicleResource getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleResource vehicle) {
        this.vehicle = vehicle;
    }

    public Admission toEntity(){
        return new Admission(
                this.id,
                this.academic_building_id,
                this.vehicle_id,
                this.term
        );
    }
}
