package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.repository.AcademicBuildingRepository;
import com.example.demo.repository.AdmissionRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.WorkerRepository;
import com.example.demo.resourse.VehicleResource;
import com.example.demo.resourse.WorkerResource;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {
    private final VehicleRepository vehicleRepository;
    private final WorkerRepository workerRepository;

    public VehicleController(VehicleRepository vehicleRepository, WorkerRepository workerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.workerRepository = workerRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    VehicleResource[] getAll(@RequestParam(required = false) Integer workedId,
                             @RequestParam(required = false) Object expand) {
        Vehicle[] entities = workedId == null ?
                vehicleRepository.select() :
                vehicleRepository.selectByWorkerIdQuery(workedId);
        //Vehicle[] entities = vehicleRepository.select();
        /*if (workedId == null){
            entities = vehicleRepository.select();
        }
        else{
            entities = vehicleRepository.selectByWorkerIdQuery(workedId);
        }*/
        return Arrays.stream(entities)
                .map(entity -> {
                    VehicleResource resource = new VehicleResource(entity);
                    if (expand != null)
                        resource.setWorker(new WorkerResource(
                                workerRepository.select(entity.getWorker_id()))
                        );
                    return resource;
                })
                .toArray(VehicleResource[]::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    VehicleResource get(@PathVariable Integer id,
                        @RequestParam(required = false) Object expand) {
        Vehicle entity = vehicleRepository.select(id);
        if (entity == null) return null;
        VehicleResource resource = new VehicleResource(entity);
        if (expand != null)
            resource.setWorker(new WorkerResource(
                    workerRepository.select(entity.getWorker_id()))
            );
        return resource;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    VehicleResource post(@RequestBody VehicleResource resource) {
        Vehicle entity = vehicleRepository.insert(resource.toEntity());
        if (entity == null) return null;
        resource = new VehicleResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    VehicleResource put(@PathVariable Integer id,
                        @RequestBody VehicleResource resource) {
        Vehicle entity = vehicleRepository.update(id, resource.toEntity());
        if (entity == null) return null;
        resource = new VehicleResource(entity);
        return resource;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    VehicleResource delete(@PathVariable Integer id) {
        Vehicle entity = vehicleRepository.delete(id);
        if (entity == null) return null;
        VehicleResource resource = new VehicleResource(entity);
        return resource;
    }
}
