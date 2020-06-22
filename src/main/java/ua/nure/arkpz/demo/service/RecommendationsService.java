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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RecommendationsService {
    private final BuildingDao buildingDao;
    private final CustomerDao customerDao;
    private final WorkerDao workerDao;
    private final RecommendationsDao recommendationsDao;

    @Value("${upper-temperature-bound}")
    private double UPPER_TEMPERATURE_BOUND;

    @Value("${workers-density-multiplier}")
    private double WORKERS_DENSITY_MULTIPLIER;


    @Autowired
    public RecommendationsService(BuildingDao buildingDao, CustomerDao customerDao,
                                  WorkerDao workerDao, RecommendationsDao recommendationsDao) {
        this.buildingDao = buildingDao;
        this.customerDao = customerDao;
        this.workerDao = workerDao;
        this.recommendationsDao = recommendationsDao;
    }

    public Recommendation prepareRecommendationsForBuilding(Building building) {
        int daysBefore = 7;
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);
        Date from = subtractDays(now, daysBefore);

        ArrayList<Customer> sickingCustomers =
                customerDao.findByCurrentBuildingAndTemperatureIsGreaterThanEqualAndEntryTimeBetweenOrderByEntryTime(
                        building, UPPER_TEMPERATURE_BOUND, new Timestamp(from.getTime()), new Timestamp(now.getTime()));
        ArrayList<Worker> sickingWorkers =
                workerDao.findByCurrentBuildingAndTemperatureIsGreaterThanEqual(building, UPPER_TEMPERATURE_BOUND);

        double density = calculateDensityOfSickingCustomers(building, sickingCustomers);
        List<Recommendation> recommendations =  recommendationsDao.findByMinimalDensityGreaterThanEqual(density);;
        return recommendations.get(0);

    }

    private double calculateDensityOfSickingCustomers(Building building, ArrayList<Customer> sickingCustomers) {
        return sickingCustomers.size() / building.getArea();
    }

    private double calculateDensityOfSickingWorkers(Building building, ArrayList<Worker> sickingWorkers) {
        return sickingWorkers.size() / building.getArea() * WORKERS_DENSITY_MULTIPLIER;
    }

    private Date subtractDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -days);
        return new Date(c.getTimeInMillis());
    }

}
