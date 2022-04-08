package com.backyardweddingapp.service;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.CustomerDTO;
import com.backyardweddingapp.dto.EventDTO;
import com.backyardweddingapp.exception.BackyardWeddingException;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface CustomerService {

  Integer addCustomer(CustomerDTO customerDTO) throws BackyardWeddingException; //returns newly added customerId
  CustomerDTO getCustomer(Integer customerId) throws BackyardWeddingException;
  // Integer deleteCustomerWithId(Integer customerId) throws BackyardWeddingException; //returns deleted customerId
  public CustomerDTO authenticateCustomer(Integer customerId, String firstName, String lastName)
	      throws BackyardWeddingException;

  Integer addEventForCustomer(EventDTO eventDTO) throws BackyardWeddingException; //returns newly added eventId
  // CustomerDTO getEventsForCustomer(Integer customerId) throws BackyardWeddingException;
  String deleteEventForCustomer(Integer eventId) throws BackyardWeddingException; //returns success message
  // EventDTO getEvent(Integer eventId) throws BackyardWeddingException; not needed(?) since getCustomer will also give events
  
  List<CustomerDTO> getAllCustomers() throws BackyardWeddingException;
  
 List<EventDTO> getAllEventsForCustomer(Integer customerId) throws BackyardWeddingException;
 
 EventDTO updateEvent(EventDTO eventDto) throws BackyardWeddingException;
 
 String deleteCustomer(Integer customerId) throws BackyardWeddingException;
 
 List<BackyardDTO> getAllBackyards() throws BackyardWeddingException;
  
}
