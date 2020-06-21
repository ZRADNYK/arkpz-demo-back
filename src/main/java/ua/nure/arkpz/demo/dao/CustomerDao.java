package ua.nure.arkpz.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Customer;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    ArrayList<Customer> findByCurrentBuildingAndTemperatureBetween(Building building,
                                                                   double temperatureLowerBound, double temperatureUpperBound);

    ArrayList<Customer> findByCurrentBuildingAndTemperatureIsGreaterThanEqual(Building building, double temperatureBound);

    List<Customer> findByCurrentBuildingAndEntryTimeBetween(Building currentBuilding, Time entryTime, Time entryTime2);

    List<Customer> findByCurrentBuildingOrderByEntryTime(Building building);
}
