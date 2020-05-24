package ua.nure.arkpz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.CustomerDao;
import ua.nure.arkpz.demo.dao.WorkerDao;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Worker;
import ua.nure.arkpz.demo.validator.OvalValidatorImpl;

import java.sql.Time;
import java.util.ArrayList;

@Service
public class WorkerService {
    private final WorkerDao workerDao;
    private final OvalValidatorImpl validator;
    private final HistoryService historyService;

    @Value("${upper-temperature-bound}")
    private double UPPER_TEMPERATURE_BOUND;

    @Autowired
    public WorkerService(WorkerDao workerDao, OvalValidatorImpl validator, HistoryService historyService) {
        this.workerDao = workerDao;
        this.validator = validator;
        this.historyService = historyService;
    }

    public Worker workerEnteredBuilding(Worker newWorker, Building building) {
        Worker worker = Worker.builder()
                .workerId(null)
                .entryTime(newWorker.getEntryTime())
                .outTime(null)
                .temperature(newWorker.getTemperature())
                .isSicking(findIsWorkerSicking(newWorker.getTemperature()))
                .currentBuilding(building)
                .build();
        validator.validate(workerDao);
        workerDao.save(worker);
        historyService.addNewHistory(building, building.getCustomers().size(),
                building.getWorkers().size());

        return worker;
    }

    private Boolean findIsWorkerSicking(Double temperature) {
        return temperature >= UPPER_TEMPERATURE_BOUND;
    }

    public Worker workerLeftBuilding(Worker worker, Building building) {
        ArrayList<Worker> potentialWorkers = workerDao.findByCurrentBuildingAndTemperatureBetween(
                worker.getCurrentBuilding(), worker.getTemperature() - 0.2,
                worker.getTemperature() + 3.0);
        Worker workerToLeave = findClosestTemperature(worker.getTemperature(), potentialWorkers);

        workerToLeave.setCurrentBuilding(null);
        workerToLeave.setOutTime(new Time(System.currentTimeMillis()));
        workerDao.save(workerToLeave);

        historyService.addNewHistory(building, building.getCustomers().size(),
                building.getWorkers().size());

        return workerToLeave;
    }

    private Worker findClosestTemperature(Double temperature, ArrayList<Worker> potentialWorkers) {
        double minimalDeviance = Double.MAX_VALUE;
        Worker foundWorker = null;
        for (Worker worker : potentialWorkers) {
            if (worker.getTemperature() - temperature < minimalDeviance) {
                minimalDeviance = worker.getTemperature() - temperature;
                foundWorker = worker;
            }
        }
        return foundWorker;
    }
}
