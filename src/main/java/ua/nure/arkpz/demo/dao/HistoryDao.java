package ua.nure.arkpz.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.History;

import java.sql.Date;
import java.util.Set;

@Repository
public interface HistoryDao extends JpaRepository<History, Long> {
    Set<History> findByBuildingAndMeasuringDate(Building building, Date measuringDate);
    Set<History> findByBuildingAndMeasuringDateBetween(Building building, Date measuringDateFrom, Date measuringDateTo);

    Set<History> findByMeasuringDate(Date date);
}
