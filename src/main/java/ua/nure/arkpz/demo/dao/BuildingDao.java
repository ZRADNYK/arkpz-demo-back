package ua.nure.arkpz.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.arkpz.demo.model.Building;
import ua.nure.arkpz.demo.model.User;

@Repository
public interface BuildingDao extends JpaRepository<Building, Long> {
    Building findByNameAndAddress(String name, String address);
    Building findByAddress(String address);
    Building findByOwner(User user);
    Building findByName(String buildingName);
}
