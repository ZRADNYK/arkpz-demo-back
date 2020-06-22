package ua.nure.arkpz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.BuildingDao;
import ua.nure.arkpz.demo.dao.CustomerDao;
import ua.nure.arkpz.demo.dto.CustomerDto;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Customer;
import ua.nure.arkpz.demo.validator.OvalValidatorImpl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerDao customerDao;
    private final BuildingDao buildingDao;
    private final OvalValidatorImpl validator;
    private final HistoryService historyService;

    @Autowired
    public CustomerService(CustomerDao customerDao, BuildingDao buildingDao, OvalValidatorImpl validator, HistoryService historyService) {
        this.customerDao = customerDao;
        this.buildingDao = buildingDao;
        this.validator = validator;
        this.historyService = historyService;
    }

    public Customer customerEnteredBuilding(Building building, CustomerDto newCustomer) {
        Customer customer = Customer.builder()
                .customerId(null)
                .entryTime(new Timestamp(System.currentTimeMillis()))
                .outTime(null)
                .temperature(newCustomer.getTemperature())
                .isSicking(findIsCustomerSicking(newCustomer.getTemperature()))
                .currentBuilding(building)
                .build();
        validator.validate(customer);

        building.addCustomer(customer);

        return customer;
    }

    public Customer customerLeftBuilding(Building building, CustomerDto customer) {
        ArrayList<Customer> potentialCustomers = customerDao.findByCurrentBuildingAndTemperatureBetween(
                building , customer.getTemperature() - 0.2,
                customer.getTemperature() + 3.0);

        Customer customerToLeave = findClosestTemperature(customer.getTemperature(), potentialCustomers);
        customerToLeave.setOutTime(new Timestamp(System.currentTimeMillis()));

        return customerToLeave;
    }

    private Boolean findIsCustomerSicking(Double temperature) {
        return temperature > 38.0;
    }

    private Customer findClosestTemperature(Double temperature, ArrayList<Customer> potentialCustomers) {
        double minimalDeviance = Double.MAX_VALUE;
        Customer foundCustomer = null;
        for (Customer customer : potentialCustomers) {
            if(customer.getTemperature() - temperature < minimalDeviance) {
                minimalDeviance = customer.getTemperature() - temperature;
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }

    public List<Customer> findCustomerByBuildingAndPeriod(Building building, Date from, Date to) {
        Timestamp ts=new Timestamp(from.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return customerDao.findByCurrentBuildingAndEntryTimeBetweenOrderByEntryTime(building, new Timestamp(from.getTime()),
                new Timestamp(to.getTime()));
    }

    public List<Customer> findCustomerByBuilding(Building building) {
        return customerDao.findByCurrentBuildingOrderByEntryTime(building);
    }
}
