package ua.nure.arkpz.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Worker;

import java.util.ArrayList;

@Repository
public interface WorkerDao extends JpaRepository<Worker, Long> {
    ArrayList<Worker> findByCurrentBuildingAndTemperatureBetween(Building building,
                                                                 double temperatureLowerBound, double temperatureUpperBound);

    ArrayList<Worker> findByCurrentBuildingAndTemperatureIsGreaterThanEqual(Building building, double temperatureBound);

}
