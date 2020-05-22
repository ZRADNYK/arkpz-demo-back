package ua.nure.arkpz.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.Chart;
import ua.nure.arkpz.demo.model.History;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ChartService {
    private final HistoryService historyService;

    @Autowired
    public ChartService(HistoryService historyService) {
        this.historyService = historyService;
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
}
