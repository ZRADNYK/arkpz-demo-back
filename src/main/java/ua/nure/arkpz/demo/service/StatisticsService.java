package ua.nure.arkpz.demo.service;

import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.HistoryDao;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.History;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class StatisticsService {
    private final HistoryDao historyDao;

    public StatisticsService(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    public ArrayList<History> getStatisticsByPeriod(Building building, Date from, Date to) {
        return historyDao.findByBuildingAndMeasuringDateBetween(building, from, to);
    }

    public ArrayList<History> getStatisticsByDate(Building building, Date date) {
        return historyDao.findByBuildingAndMeasuringDate(building, date);
    }
}
