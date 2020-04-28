package ua.nure.arkpz.demo.dto;

import ua.nure.arkpz.demo.model.Building;

import java.sql.Time;

public class WorkerDto {
    private Double temperature;
    private Boolean isSicking;
    private Time entryTime;
    private Time outTime;
    public Building currentBuilding;
}
