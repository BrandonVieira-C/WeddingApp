package com.backyardweddingapp.api;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.CustomerDTO;
import com.backyardweddingapp.dto.EventDTO;
import com.backyardweddingapp.entity.Event;
import com.backyardweddingapp.exception.BackyardWeddingException;
import com.backyardweddingapp.service.CustomerService;
import com.backyardweddingapp.service.PartnerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/customer-api")
public class CustomerAPI {

  @Autowired
  CustomerService customerService;


  @PostMapping(value="/customerlogin")
  public ResponseEntity<CustomerDTO> authenticateCustomer(@RequestBody CustomerDTO customerDTO) throws BackyardWeddingException {
	  CustomerDTO dto = customerService.authenticateCustomer(customerDTO.getCustomerId(), customerDTO.getFirstName(), customerDTO.getLastName());
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }
  
  @PostMapping(value = "/addcustomer")
  public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) throws BackyardWeddingException {
    Integer newCustomerId = customerService.addCustomer(customerDTO);
    String successMsg = "New customer added with new customerId: " + newCustomerId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }
  
  @GetMapping(value = "/getAllCustomers")
  public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws BackyardWeddingException {
	  List<CustomerDTO> response = customerService.getAllCustomers();
	  return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(value = "/getcustomer")
  public ResponseEntity<CustomerDTO> getCustomerWithId(@RequestBody CustomerDTO customerDTO)
      throws BackyardWeddingException {
    CustomerDTO returned = customerService.getCustomer(customerDTO.getCustomerId());
    return new ResponseEntity<CustomerDTO>(returned, HttpStatus.OK);
  }

  @PostMapping(value = "/addevent")
  public ResponseEntity<String> addEventForCustomer(
      @RequestBody EventDTO eventDTO) throws BackyardWeddingException {
    Integer newEventId = customerService.addEventForCustomer(eventDTO);
    String successMsg = "New event added with new eventId: " + newEventId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }
  
  @PostMapping(value="/updateevent")
	public ResponseEntity<EventDTO> updateEvent (@RequestBody EventDTO eventDto) throws BackyardWeddingException {
		EventDTO dto = customerService.updateEvent(eventDto);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

  @DeleteMapping(value = "/deleteevent/{eventId}")
  public ResponseEntity<String> deleteEventForCustomer(
     @PathVariable("eventId") Integer eventId) throws BackyardWeddingException {
    String successMsg = customerService.deleteEventForCustomer(eventId);
    return new ResponseEntity<>(successMsg, HttpStatus.OK);
  }
  
  @GetMapping(value = "/getallevents/{customerId}")
  public ResponseEntity<List<EventDTO>> getAllEvents(@PathVariable("customerId") Integer customerId) throws BackyardWeddingException {
	  List<EventDTO> eventDtoList = customerService.getAllEventsForCustomer(customerId);
	  return new ResponseEntity<>(eventDtoList, HttpStatus.OK);
  }
  
  @DeleteMapping(value="/deletecustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer (@PathVariable("customerId") Integer customerId) throws BackyardWeddingException {
		String d = customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(d, HttpStatus.OK);
	}
  
  @GetMapping(value="/getallbackyards")
  public ResponseEntity<List<BackyardDTO>> getAllBackyards() throws BackyardWeddingException {
	  List<BackyardDTO> response =  customerService.getAllBackyards();
	  return new ResponseEntity<>(response,HttpStatus.OK);
  }
  
  

}
