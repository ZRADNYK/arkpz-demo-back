package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Worker;
import ua.nure.arkpz.demo.service.BuildingService;
import ua.nure.arkpz.demo.service.WorkerService;

@RestController("/workers")
public class WorkerController {
    private final WorkerService workerService;
    private final BuildingService buildingService;

    @Autowired
    public WorkerController(WorkerService workerService, BuildingService buildingService) {
        this.workerService = workerService;
        this.buildingService = buildingService;
    }

    @PostMapping("/enter")
    public ResponseEntity<Worker> newWorkerEntered(@RequestBody Worker worker) {
        Building building = buildingService.findById(worker.getCurrentBuilding().getBuildingId());
        workerService.workerEnteredBuilding(worker, building);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/left")
    public ResponseEntity<Worker> workerLeftBuilding(@RequestBody Worker worker) {
        Building building = buildingService.findById(worker.getCurrentBuilding().getBuildingId());
        workerService.workerLeftBuilding(worker, building);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
