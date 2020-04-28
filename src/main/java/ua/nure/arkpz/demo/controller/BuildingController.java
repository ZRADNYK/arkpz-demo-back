package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ua.nure.arkpz.demo.dto.BuildingDto;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.service.BuildingService;

@RestController
public class BuildingController {
    private final BuildingService buildingService;

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    // @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @PostMapping("/buildings/register")
    public ResponseEntity<Building> registerBuilding(@RequestBody BuildingDto buildingDto) {
        final Building existingBuilding = buildingService.searchExistingBuilding(buildingDto);
        if(existingBuilding != null ) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Building registeredBuilding =  buildingService.registerBuilding(buildingDto);

        return ResponseEntity.ok(registeredBuilding);
    }

   // @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @PatchMapping("/buildings/update")
    public ResponseEntity<Building> updateBuilding(@RequestBody BuildingDto buildingDto) {
        final Building existingBuilding = buildingService.searchExistingBuilding(buildingDto);
        if(existingBuilding == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Building updatedBuilding =  buildingService.updateBuilding(buildingDto, existingBuilding);

        return ResponseEntity.ok(updatedBuilding);
    }

 //   @Secured({"ROLE_ADMIN", "ROLE_SUPERADMIN"})
    @PatchMapping("/buildings/delete")
    public ResponseEntity<Building> deleteBuilding(@RequestBody BuildingDto buildingDto) {
        final Building existingBuilding = buildingService.searchExistingBuilding(buildingDto);
        if(existingBuilding == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        buildingService.deleteBuilding(existingBuilding);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/buildings/{buildingId}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long buildingId) {
        final Building existingBuilding = buildingService.findById(buildingId);
        if(existingBuilding == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else return new ResponseEntity<>(existingBuilding, HttpStatus.OK);
    }
}
