package ua.nure.arkpz.demo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "worker")
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workerId;
    private Double temperature;
    private Boolean isSicking;
    private Time entryTime;
    private Time outTime;
    
    @OneToOne(targetEntity = Building.class)
    public Building currentBuilding;
}
