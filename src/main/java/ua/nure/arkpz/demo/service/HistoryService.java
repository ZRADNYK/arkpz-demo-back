package ua.nure.arkpz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.HistoryDao;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Customer;
import ua.nure.arkpz.demo.model.History;
import ua.nure.arkpz.demo.model.Worker;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

@Service
public class HistoryService {
    @Autowired
    private HistoryDao historyDao;

    @Value("${upper-temperature-bound}")
    private static double UPPER_TEMPERATURE_BOUND;

    public void addNewHistory(Building building, int customersInside, int workersInside) {
        History history = History.builder()
                .historyId(null)
                .building(building)
                .customersInsideAmount(customersInside)
                .workersInsideAmount(workersInside)
                .sickingCustomersInsideAmount(getSickingCustomers(building))
                .sickingWorkersInsideAmount(getSickingWorkers(building))
                .measuringDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()))
                .build();
        historyDao.save(history);
    }

    private int getSickingCustomers(Building building) {
        int sicking = 0;
        for (Customer customer : building.getCustomers()) {
            if (customer.getTemperature() >= UPPER_TEMPERATURE_BOUND) {
                sicking++;
            }
        }
        return sicking;
    }

    private int getSickingWorkers(Building building) {
        int sicking = 0;
        for (Worker worker : building.getWorkers()) {
            if (worker.getTemperature() >= UPPER_TEMPERATURE_BOUND) {
                sicking++;
            }
        }
        return sicking;
    }

    public ArrayList<History> findAllHistoriesForBuilding(Building building) {
        return historyDao.findByBuilding(building);
    }

    public ArrayList<History> findAllHistoriesForBuildingByDate(Building building, Date date) {
        return historyDao.findByBuildingAndMeasuringDate(building, date);
    }

    public ArrayList<History> findAllHistoriesForBuildingByPeriod(Building building, Date from, Date to) {
        return historyDao.findByBuildingAndMeasuringDateBetween(building, from, to);
    }
}
