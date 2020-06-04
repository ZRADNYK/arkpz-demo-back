package ua.nure.arkpz.demo.model;

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
    private Long buildingId;
    private String name;
    private Double area;
    private String address;
    private Short entersAmount;
    private Short workersAmount;
    private Short maxCustomersAllowed;
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User user;
    @OneToMany(targetEntity = Customer.class)
    private Set<Customer> customers;
    @OneToMany(targetEntity = Worker.class)
    private Set<Worker> workers;
    @OneToMany(targetEntity = History.class)
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
