package ua.nure.arkpz.demo.dto;

import lombok.Getter;
import lombok.Setter;
import ua.nure.arkpz.demo.model.User;

@Getter
@Setter
public class BuildingDto {
    private String name;
    private Double area;
    private String address;
    private Short entersAmount;
    private Short workersAmount;
    private Long ownerId;
}
