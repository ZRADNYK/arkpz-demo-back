package ua.nure.arkpz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.chart.Chart;
import ua.nure.arkpz.demo.chart.CustomerChart;
import ua.nure.arkpz.demo.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ChartService {
    private final HistoryService historyService;
    private final CustomerService customerService;
    private final WorkerService workerService;

    @Value("${upper-temperature-bound}")
    private Double UPPER_TEMPERATURE_BOUND;

    @Autowired
    public ChartService(HistoryService historyService, CustomerService customerService, WorkerService workerService) {
        this.historyService = historyService;
        this.customerService = customerService;
        this.workerService = workerService;
    }


    public Chart createChartForAllBuildingHistories(Building building) {
        ArrayList<History> histories = historyService.findAllHistoriesForBuilding(building);
        return createChartFromHistories(histories);
    }

    public Chart createChartForBuildingByPeriod(Building building, Date from, Date to) {
        ArrayList<History> histories = historyService.findAllHistoriesForBuildingByPeriod(building, from, to);
        return createChartFromHistories(histories);
    }

    public Chart createChartForBuildingByDate(Building building, Date date) {
        ArrayList<History> histories = historyService.findAllHistoriesForBuildingByDate(building, date);
        return createChartFromHistories(histories);
    }

    private Chart createChartFromHistories(ArrayList<History> histories) {
        Chart chart = new Chart();
        for (History history : histories) {
            String label = String.valueOf(history.getMeasuringDate());
            Double y = (double) (history.getSickingCustomersInsideAmount() / history.getCustomersInsideAmount());
            HashMap<String, Double> chartPoint = new HashMap<>();
            chartPoint.put(label, y);
            chart.addPointToChart(chartPoint);
        }
        return chart;
    }

    public Chart createNewCustomerChartPeriod(Building building, Date from, Date to) {
        List<Customer> customers = customerService.findCustomerByBuildingAndPeriod(building, from, to);
        List<Customer> sickingCustomers = new ArrayList<>();
        for(Customer customer : customers) {
            if (customer.getTemperature() > UPPER_TEMPERATURE_BOUND) {
                sickingCustomers.add(customer);
            }
        }

        return null;
    }

    public CustomerChart createNewChartForAllBuilding(Building building) {
        List<Customer> customers = customerService.findCustomerByBuilding(building);
        List<Customer> sickingCustomers = new ArrayList<>();
        for(Customer customer : customers) {
            if (customer.getTemperature() > UPPER_TEMPERATURE_BOUND) {
                sickingCustomers.add(customer);
            }
        }
        return new CustomerChart(customers, sickingCustomers);
    }
}
