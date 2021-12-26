package com.example.demo.entity;

import java.sql.Timestamp;

public class Admission extends BaseEntity{
    private Integer academic_building_id;
    private Integer vehicle_id;
    private Timestamp term;

    public Admission(Integer id, Integer academic_building_id, Integer vehicle_id, Timestamp term) {
        super(id);
        this.academic_building_id = academic_building_id;
        this.vehicle_id = vehicle_id;
        this.term = term;
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
}
