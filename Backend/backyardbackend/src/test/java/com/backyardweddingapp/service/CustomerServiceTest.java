package com.backyardweddingapp.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backyardweddingapp.dto.CustomerDTO;
import com.backyardweddingapp.entity.Customer;
import com.backyardweddingapp.entity.Event;
import com.backyardweddingapp.exception.BackyardWeddingException;
import com.backyardweddingapp.repository.CustomerRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

public class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerService customerService = new CustomerServiceImpl();


  @Test
  public void addCustomerValidTest() throws BackyardWeddingException {
    Customer customer = new Customer();
    customer.setCustomerId(002);
    customer.setFirstName("Deborah");
    customer.setLastName("Robertson");
    // whenever save method is called, customer is returned.
    Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

    CustomerDTO customerDTO = new CustomerDTO();
    Assertions.assertEquals(002, customerService.addCustomer(customerDTO));
  }

  @Test
  public void getCustomerValidTest() throws BackyardWeddingException{
    Customer customer = new Customer();
    customer.setCustomerId(001);
    customer.setFirstName("Claire");
    customer.setLastName("Bear");
    List<Event> listOfEvents = new ArrayList<>();
    customer.setEvents(listOfEvents);

    Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customer));

    CustomerDTO customerDTO = customerService.getCustomer(001);

    Assertions.assertEquals("Claire", customerDTO.getFirstName());
  }


  // @Test
  // void addCustomerValidTest() throws BackyardWeddingException {

  //   Customer customer2 = new Customer();
  //   customer2.setCustomerId(002);
  //   customer2.setFirstName("Deborah");
  //   customer2.setLastName("Huang");

  //   Customer customer3 = new Customer();
  //   Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer3);

  //   CustomerDTO newCustomer = new CustomerDTO();
  //   newCustomer.setCustomerId(customer2.getCustomerId());
  //   newCustomer.setFirstName(customer2.getFirstName());
  //   newCustomer.setLastName(customer2.getLastName());

  //   Integer newCustomerId = customerService.addCustomer(newCustomer);
  //   Assertions.assertEquals(002, newCustomerId);
    
  // }


  // @Test
  // void getCustomerWithEventsValidTest() throws BackyardWeddingException {
  //   Customer customer = new Customer();
  //   customer.setCustomerId(001);
  //   customer.setFirstName("Claire");
  //   customer.setLastName("Bear");
  //   List<Event> events = new ArrayList<>();
  //   customer.setEvents(events);
	// 	Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customer));

  //   CustomerDTO returned = customerService.getCustomer(001);
  //   Assertions.assertEquals(001, returned.getCustomerId());
  // }



  
}
