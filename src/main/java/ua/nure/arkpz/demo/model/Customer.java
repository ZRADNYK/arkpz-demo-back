package ua.nure.arkpz.demo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "customer")
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    private Long customerId;
    private Double temperature;
    private Boolean isSicking;
    private Time entryTime;
    private Time outTime;
    
    @OneToOne(targetEntity = Building.class)
    public Building currentBuilding;
}
