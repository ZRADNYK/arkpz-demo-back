package ua.nure.arkpz.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "building_id")
    private Long buildingId;
    @Column(name = "name")
    private String name;
    @Column(name = "area")
    private Double area;
    @Column(name = "address")
    private String address;
    @Column(name = "enters_amount")
    private Short entersAmount;
    @Column(name = "workers_amount")
    private Short workersAmount;
    @Column(name = "max_customers_allowed")
    private Short maxCustomersAllowed;

    @ManyToOne
    private User user;

    @OneToMany(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Set<Customer> customers;
    @JsonIgnore
    @OneToMany(targetEntity = Worker.class)
    @JoinColumn(name = "worker_id")
    private Set<Worker> workers;
    @JsonIgnore
    @OneToMany(targetEntity = History.class)
    @JoinColumn(name = "history_id")
    private Set<History> histories;

    public Set<Customer> addCustomer(Customer customer) {
        Set<Customer> customers = this.getCustomers();
        customers.add(customer);
        return customers;
    }

    public Set<Customer> deleteCustomer(Customer customer) {
        Set<Customer> customers = this.getCustomers();
        customers.remove(customer);
        return customers;
    }

    public Set<Worker> addWorker(Worker worker) {
        Set<Worker> workers = this.getWorkers();
        workers.add(worker);
        return workers;
    }

    public Set<Worker> deleteWorker(Worker worker) {
        Set<Worker> workers = this.getWorkers();
        workers.remove(worker);
        return workers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return buildingId.equals(building.buildingId) &&
                user.equals(building.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingId, user);
    }
}
