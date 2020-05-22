package ua.nure.arkpz.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.History;

import java.sql.Date;
import java.util.ArrayList;

@Repository
public interface HistoryDao extends JpaRepository<History, Long> {
    ArrayList<History> findByBuildingAndMeasuringDate(Building building, Date measuringDate);

    ArrayList<History> findByBuildingAndMeasuringDateBetween(Building building, Date measuringDateFrom, Date measuringDateTo);

    ArrayList<History> findByBuilding(Building building);
}
