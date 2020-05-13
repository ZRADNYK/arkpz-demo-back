package ua.nure.arkpz.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Customer;
import ua.nure.arkpz.demo.service.BuildingService;
import ua.nure.arkpz.demo.service.CustomerService;

@RestController
public class CustomerController {
    private final CustomerService customerService;
    private final BuildingService buildingService;

    @Autowired
    public CustomerController(CustomerService customerService, BuildingService buildingService) {
        this.customerService = customerService;
        this.buildingService = buildingService;
    }

    @PostMapping("/customers/enter")
    public ResponseEntity<Customer> newCustomerEntered(@RequestBody Customer customer) {
        Building building = buildingService.findById(customer.getCurrentBuilding().getBuildingId());
        customerService.customerEnteredBuilding(building, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/customers/left")
    public ResponseEntity<Customer> customerLeftBuilding(@RequestBody Customer customer) {
        Building building = buildingService.findById(customer.getCurrentBuilding().getBuildingId());
        customerService.customerLeftBuilding(building, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
