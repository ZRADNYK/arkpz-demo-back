package ua.nure.arkpz.demo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

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
    private Timestamp entryTime;
    private Timestamp outTime;
    
    @ManyToOne(targetEntity = Building.class)
    public Building currentBuilding;
}
