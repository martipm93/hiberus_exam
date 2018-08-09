package org.hiberus.exam.persistance;

import java.util.List;

import org.hiberus.exam.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}