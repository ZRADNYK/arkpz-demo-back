package ua.nure.arkpz.demo.service;

import jdk.javadoc.internal.doclets.formats.html.markup.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.BuildingDao;
import ua.nure.arkpz.demo.dao.CustomerDao;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Customer;
import ua.nure.arkpz.demo.validator.OvalValidatorImpl;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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

    public Customer customerEnteredBuilding(Building building, Customer newCustomer) {
        Customer customer = Customer.builder()
                .customerId(null)
                .entryTime(newCustomer.getEntryTime())
                .outTime(null)
                .temperature(newCustomer.getTemperature())
                .isSicking(findIsCustomerSicking(newCustomer.getTemperature()))
                .currentBuilding(building)
                .build();
        validator.validate(customer);
        customerDao.save(customer);

        building.addCustomer(customer);
        buildingDao.save(building);

        historyService.addNewHistory(building, building.getCustomers().size(),
                building.getWorkers().size());

        return customer;
    }

    public Customer customerLeftBuilding(Building building, Customer customer) {
        ArrayList<Customer> potentialCustomers = customerDao.findByCurrentBuildingAndTemperatureBetween(
                building , customer.getTemperature() - 0.2,
                customer.getTemperature() + 3.0);

        Customer customerToLeave = findClosestTemperature(customer.getTemperature(), potentialCustomers);
        customerToLeave.setOutTime(new Timestamp(System.currentTimeMillis()));

        building.deleteCustomer(customer);
        buildingDao.save(building);

        customerDao.delete(customerToLeave);

        building.deleteCustomer(customer);
        buildingDao.save(building);

        historyService.addNewHistory(building, building.getCustomers().size(),
                building.getWorkers().size());

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
        Time timeFrom = Time.valueOf(String.valueOf(from));
        Time timeTo = Time.valueOf(String.valueOf(to));
        return customerDao.findByCurrentBuildingAndEntryTimeBetween(building, timeFrom, timeTo);
    }

    public List<Customer> findCustomerByBuilding(Building building) {
        return customerDao.findByCurrentBuildingOrderByEntryTime(building);
    }
}
