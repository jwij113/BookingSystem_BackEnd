package com.dominicon.booking.test;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

  private final CustomerRepository repository;

  CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/employees")
  List<Customer> all() {
    Customer c = new Customer("Joko", "test", "Wijaya");
    repository.save(c);
    return repository.findByFirstName("Joko");
  }

  @PostMapping("/employees")
  Customer newEmployee(@RequestBody Customer newEmployee) {
    return repository.save(newEmployee);
  }
  
  @GetMapping("/employees/{id}")
  Optional<Customer> one(@PathVariable Long id) {
    return repository.findById(id);
  }


}