package ua.nure.arkpz.demo.chart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nure.arkpz.demo.model.Customer;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerChart {
    private List<Customer> customers;
    private List<Customer> sickingCustomers;
}
