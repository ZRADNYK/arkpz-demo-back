package ua.nure.arkpz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.BuildingDao;
import ua.nure.arkpz.demo.dao.CustomerDao;
import ua.nure.arkpz.demo.dao.RecommendationsDao;
import ua.nure.arkpz.demo.dao.WorkerDao;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Customer;
import ua.nure.arkpz.demo.model.Recommendation;
import ua.nure.arkpz.demo.model.Worker;

import java.util.ArrayList;

@Service
public class RecommendationsService {
    private final BuildingDao buildingDao;
    private final CustomerDao customerDao;
    private final WorkerDao workerDao;
    private final RecommendationsDao recommendationsDao;

    @Value("${upper-temperature-bound}")
    private static double UPPER_TEMPERATURE_BOUND;

    @Value("${workers-density-multiplier}")
    private static double WORKERS_DENSITY_MULTIPLIER;


    @Autowired
    public RecommendationsService(BuildingDao buildingDao, CustomerDao customerDao,
                                  WorkerDao workerDao, RecommendationsDao recommendationsDao) {
        this.buildingDao = buildingDao;
        this.customerDao = customerDao;
        this.workerDao = workerDao;
        this.recommendationsDao = recommendationsDao;
    }

    public ArrayList<Recommendation> prepareRecommendationsForBuilding(Building building) {
        ArrayList<Customer> sickingCustomers =
                customerDao.findByCurrentBuildingAndTemperatureIsGreaterThanEqual(building, UPPER_TEMPERATURE_BOUND);
        ArrayList<Worker> sickingWorkers =
                workerDao.findByCurrentBuildingAndTemperatureIsGreaterThanEqual(building, UPPER_TEMPERATURE_BOUND);
        ArrayList<Recommendation> recommendations = new ArrayList<>();

        recommendations.add(recommendationsDao.findByMaximalDensityIsLessThanEqualAndMinimalDensityIsGreaterThanEqual(
                calculateDensityOfSickingCustomers(building, sickingCustomers),
                calculateDensityOfSickingCustomers(building, sickingCustomers)));

        recommendations.add(recommendationsDao.findByMaximalDensityIsLessThanEqualAndMinimalDensityIsGreaterThanEqual(
                calculateDensityOfSickingWorkers(building, sickingWorkers),
                calculateDensityOfSickingWorkers(building, sickingWorkers)));

        return recommendations;
    }

    private double calculateDensityOfSickingCustomers(Building building, ArrayList<Customer> sickingCustomers) {
        return sickingCustomers.size() / building.getArea();
    }

    private double calculateDensityOfSickingWorkers(Building building, ArrayList<Worker> sickingWorkers) {
        return sickingWorkers.size() / building.getArea() * WORKERS_DENSITY_MULTIPLIER;
    }
}
