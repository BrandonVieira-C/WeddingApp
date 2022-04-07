package com.backyardweddingapp.repository;

import com.backyardweddingapp.entity.Customer;
import com.backyardweddingapp.exception.BackyardWeddingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CustomerRepoTest {

  @Autowired
  private CustomerRepository customerRepository;

  private Customer customer;

  @BeforeEach
  public void setUp() {
    customer = new Customer();
    customer.setCustomerId(001);
    customer.setFirstName("Claire");
    customer.setLastName("Bear");
  }

  @Test
  void saveCustomerValidTest() {
    Customer customerFromDB = customerRepository.save(customer);
    Assertions.assertEquals(001, customerFromDB.getCustomerId());
  }

  @Test
  void findCustomerByIdValidTest() throws BackyardWeddingException {
    customerRepository.save(customer);
    Customer customer = customerRepository.findById(001)
        .orElseThrow(() -> new BackyardWeddingException("Cannot find customer with that Id"));
    Assertions.assertEquals(001, customer.getCustomerId());
  }

}
