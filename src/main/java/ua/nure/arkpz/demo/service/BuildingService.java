package ua.nure.arkpz.demo.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.arkpz.demo.dao.BuildingDao;
import ua.nure.arkpz.demo.dao.UserDao;
import ua.nure.arkpz.demo.dto.BuildingDto;
import ua.nure.arkpz.demo.exception.EntitySearchFailedException;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.User;
import ua.nure.arkpz.demo.validator.OvalValidatorImpl;

import java.util.Optional;
import java.util.Set;

@Service
public class BuildingService {
    private final BuildingDao buildingDao;
    private final UserDao userDao;
    private final OvalValidatorImpl validator;
    private static final int MAX_CUSTOMERS_ON_BUILDING_SQUARE = 5;

    @Autowired
    public BuildingService(BuildingDao buildingDao, UserDao userDao, OvalValidatorImpl validator) {
        this.buildingDao = buildingDao;
        this.userDao = userDao;
        this.validator = validator;
    }

    public Building searchExistingBuilding(BuildingDto building) {
        return buildingDao.findByAddress(building.getAddress());
    }

    public Building registerBuilding(BuildingDto buildingDto) {
        User owner = userDao.findById(buildingDto.getOwnerId()).get();
        Building building = Building.builder()
                .buildingId(null)
                .name(buildingDto.getName())
                .address(buildingDto.getAddress())
                .area(buildingDto.getArea())
                .customers(null)
                .entersAmount(buildingDto.getEntersAmount())
                .workersAmount(buildingDto.getWorkersAmount())
                .maxCustomersAllowed(calculateMaxCustomersNumber(buildingDto.getWorkersAmount(), buildingDto.getArea()))
                .owner(owner)
                .build();
        validator.validate(building);
        buildingDao.save(building);
        return building;
    }

    private Short calculateMaxCustomersNumber(Short workersAmount, Double area) {
        return (short) (area / MAX_CUSTOMERS_ON_BUILDING_SQUARE - workersAmount);
    }

    public Building updateBuilding(BuildingDto buildingDto, Building building) {
        // TODO check if it really works
        BeanUtils.copyProperties(buildingDto, building);
        buildingDao.save(building);
        return building;
    }

    public void deleteBuilding(Building building) {
        buildingDao.delete(building);
    }

    public Building findByName(String buildingName) {
        Building building = buildingDao.findByName(buildingName);
        if(building == null) {
            throw new EntitySearchFailedException();
        }
        return building;
    }

    public Building findById(Long buildingId) {
        return buildingDao.findById(buildingId).orElseThrow(EntitySearchFailedException::new);
    }

    public Set<Building> findByOwner(User user) {
        return buildingDao.findByOwner(user);
    }

    /*public Set<Building> findAllBuildingsByUser(User user) {
        return byu
    }*/
}
