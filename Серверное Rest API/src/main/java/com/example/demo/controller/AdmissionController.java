package com.example.demo.controller;

import com.example.demo.entity.Admission;
import com.example.demo.entity.Vehicle;
import com.example.demo.repository.AcademicBuildingRepository;
import com.example.demo.repository.AdmissionRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.resourse.AcademicBuildingResource;
import com.example.demo.resourse.AdmissionResource;
import com.example.demo.resourse.VehicleResource;
import com.example.demo.resourse.WorkerResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/admission")
public class AdmissionController {
    private final AdmissionRepository admissionRepository;
    private final AcademicBuildingRepository academicBuildingRepository;
    private final VehicleRepository vehicleRepository;
    private final WorkerRepository workerRepository;

    public AdmissionController(AdmissionRepository admissionRepository, AcademicBuildingRepository academicBuildingRepository, VehicleRepository vehicleRepository, WorkerRepository workerRepository) {
        this.admissionRepository = admissionRepository;
        this.academicBuildingRepository = academicBuildingRepository;
        this.vehicleRepository = vehicleRepository;
        this.workerRepository = workerRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    AdmissionResource[] getAll(@RequestParam(required = false) Integer academic_building_id,
                               @RequestParam(required = false) Integer vehicle_id ,
                               @RequestParam(required = false) Object expand) {

            Admission[]entities;
            if (!(academic_building_id == null) && (vehicle_id == null)){
                entities = admissionRepository.selectByAcademicBuildingIdQuery(academic_building_id);
            }
            else if ((academic_building_id == null) && !(vehicle_id == null)){
                entities = admissionRepository.selectByVehicleIdQuery(vehicle_id);
            }
            else {
                entities = admissionRepository.select();
            }

        return Arrays.stream(entities)
                .map(entity -> {
                    AdmissionResource resource = new AdmissionResource(entity);
                    if (expand != null) {
                        resource.setVehicle(new VehicleResource(
                                vehicleRepository.select(entity.getVehicle_id())));
                        resource.setAcademicBuilding(new AcademicBuildingResource(
                                academicBuildingRepository.select(entity.getAcademic_building_id())));
                    }
                    return resource;
                })
                .toArray(AdmissionResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AdmissionResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        Admission entity = admissionRepository.select(id);
        if (entity == null) return null;
        AdmissionResource resource = new AdmissionResource(entity);
        /*if (expand != null)
            resource.setWorker(new WorkerResource(
                    workerRepository.select(entity.getWorker_id()))
            );*/
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    AdmissionResource post(@RequestBody AdmissionResource resource) {
        Admission entity = admissionRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new AdmissionResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AdmissionResource put(@PathVariable Integer id,
                        @RequestBody AdmissionResource resource) {
        Admission entity = admissionRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new AdmissionResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    AdmissionResource delete(@PathVariable Integer id) {
        Admission entity = admissionRepository.delete(id);
        if (entity == null) return null;
        AdmissionResource resource = new AdmissionResource(entity);
        return resource;
    }
}
