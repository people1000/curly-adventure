package com.example.demo.controller;

import com.example.demo.entity.Worker;
import com.example.demo.repository.AcademicBuildingRepository;
import com.example.demo.repository.AdmissionRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.resourse.VehicleResource;
import com.example.demo.resourse.WorkerResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/worker")
public class WorkerController {
   /* private final AdmissionRepository admissionRepository;
    private final AcademicBuildingRepository academicBuildingRepository;*/
    private final VehicleRepository vehicleRepository;
    private final WorkerRepository workerRepository;

    public WorkerController(/*AdmissionRepository admissionRepository, AcademicBuildingRepository academicBuildingRepository,*/ VehicleRepository vehicleRepository, WorkerRepository workerRepository) {
        /*this.admissionRepository = admissionRepository;
        this.academicBuildingRepository = academicBuildingRepository;*/
        this.vehicleRepository = vehicleRepository;
        this.workerRepository = workerRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    WorkerResource[] getAll(@RequestParam(required = false) Object expand) {
        return Arrays.stream(workerRepository.select())
                .map(entity -> {
                    WorkerResource resource = new WorkerResource(entity);
                    if (expand != null)
                        resource.setVehicle(
                                Arrays.stream(vehicleRepository.selectByWorkerIdQuery(entity.getId()))
                                        .map(e -> new VehicleResource(e))
                                        .toArray(VehicleResource[]::new)
                        );
                    return resource;
                })
                .toArray(WorkerResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    WorkerResource get(@PathVariable Integer id,
                          @RequestParam(required = false) Object expand) {
        Worker entity = workerRepository.select(id);
        if (entity == null) return null;
        WorkerResource resource = new WorkerResource(entity);
        if (expand != null)
            resource.setVehicle(
                    Arrays.stream(vehicleRepository.selectByWorkerIdQuery(entity.getId()))
                            .map(e -> new VehicleResource(e))
                            .toArray(VehicleResource[]::new)
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    WorkerResource post(@RequestBody WorkerResource resource) {
        Worker entity = workerRepository.insert(resource.toEntity());
        if (entity == null) return null;
        return new WorkerResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    WorkerResource put(@PathVariable Integer id,
                          @RequestBody WorkerResource resource) {
        Worker entity = workerRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        return new WorkerResource(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    WorkerResource delete(@PathVariable Integer id) {
        Worker entity = workerRepository.delete(id);
        if (entity == null) return null;
        return new WorkerResource(entity);
    }
}
