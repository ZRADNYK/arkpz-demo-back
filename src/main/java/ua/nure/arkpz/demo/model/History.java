package ua.nure.arkpz.demo.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private Long historyId;

    private Date measuringDate;
    private int customersInsideAmount;
    private int sickingCustomersInsideAmount;
    private int workersInsideAmount;
    private int sickingWorkersInsideAmount;

    @ManyToOne(targetEntity = Building.class)
    @JoinColumn(name = "building_id")
    private Building building;
}
