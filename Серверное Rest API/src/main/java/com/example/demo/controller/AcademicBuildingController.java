package com.example.demo.controller;

import com.example.demo.entity.AcademicBuilding;
import com.example.demo.repository.AcademicBuildingRepository;
import com.example.demo.repository.AdmissionRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.resourse.AcademicBuildingResource;
import com.example.demo.resourse.AdmissionResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "academicBuilding")
public class AcademicBuildingController {
    private final AdmissionRepository admissionRepository;
    private final AcademicBuildingRepository academicBuildingRepository;
    private final VehicleRepository vehicleRepository;
    private final WorkerRepository workerRepository;

    public AcademicBuildingController(AdmissionRepository admissionRepository, AcademicBuildingRepository academicBuildingRepository, VehicleRepository vehicleRepository, WorkerRepository workerRepository) {
        this.admissionRepository = admissionRepository;
        this.academicBuildingRepository = academicBuildingRepository;
        this.vehicleRepository = vehicleRepository;
        this.workerRepository = workerRepository;
    }

    @GetMapping(value = "")
    AcademicBuildingResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(academicBuildingRepository.select())
                .map(entity -> {
                    AcademicBuildingResource resource = new AcademicBuildingResource(entity);
                    if (expand != null)
                        resource.setAdmission(
                                Arrays.stream(admissionRepository.selectByAcademicBuildingIdQuery(entity.getId()))
                                        .map(e -> new AdmissionResource(e))
                                        .toArray(AdmissionResource[]::new)
                        );
                    return resource;
                })
                .toArray(AcademicBuildingResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    AcademicBuildingResource get(@PathVariable Integer id,
                       @RequestParam(required = false) Object expand) {
        AcademicBuilding entity = academicBuildingRepository.select(id);
        if (entity == null) return null;
        AcademicBuildingResource resource = new AcademicBuildingResource(entity);
        if (expand != null)
            resource.setAdmission(
                    Arrays.stream(admissionRepository.selectByAcademicBuildingIdQuery(entity.getId()))
                            .map(e -> new AdmissionResource(e))
                            .toArray(AdmissionResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    AcademicBuildingResource post(@RequestBody AcademicBuildingResource resource) {
        AcademicBuilding entity = academicBuildingRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new AcademicBuildingResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    AcademicBuildingResource put(@PathVariable Integer id,
                       @RequestBody AcademicBuildingResource resource) {
        AcademicBuilding entity = academicBuildingRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new AcademicBuildingResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    AcademicBuildingResource delete(@PathVariable Integer id) {
        AcademicBuilding entity = academicBuildingRepository.delete(id);
        if (entity == null) return null;
        return new AcademicBuildingResource(entity);
    }
}