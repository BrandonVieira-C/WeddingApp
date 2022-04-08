package com.backyardweddingapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.CustomerDTO;
import com.backyardweddingapp.dto.EventDTO;
import com.backyardweddingapp.entity.Backyard;
import com.backyardweddingapp.entity.Customer;
import com.backyardweddingapp.entity.Event;
import com.backyardweddingapp.exception.BackyardWeddingException;
import com.backyardweddingapp.repository.BackyardRepository;
import com.backyardweddingapp.repository.CustomerRepository;
import com.backyardweddingapp.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private EventRepository eventRepository;
  
  @Autowired
  private BackyardRepository backyardRepository;

  @Override
  public Integer addCustomer(CustomerDTO customerDTO) throws BackyardWeddingException {
    Customer customer = new Customer();
    customer.setFirstName(customerDTO.getFirstName());
    customer.setLastName(customerDTO.getLastName());
    // not setting customer event here...
    Customer saved = customerRepository.save(customer);
    return saved.getCustomerId();
  }
  
  @Override
  public List<CustomerDTO> getAllCustomers() throws BackyardWeddingException {
	  List<Customer> customerEntityList = (List<Customer>) customerRepository.findAll();
	  if (customerEntityList.isEmpty()) {
		  throw new BackyardWeddingException("No customers were found.");
	  }
	  List<CustomerDTO> customerDtoList = new LinkedList<CustomerDTO>();
	  for (Customer c : customerEntityList) {
		  CustomerDTO customerDto = new CustomerDTO();
		  customerDto.setCustomerId(c.getCustomerId());
		  customerDto.setFirstName(c.getFirstName());
		  customerDto.setLastName(c.getLastName());
		  customerDtoList.add(customerDto);
	  }
	  return customerDtoList;
  }
  
  @Override
  public CustomerDTO authenticateCustomer(Integer customerId, String firstName, String lastName)
      throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find customer with that customerId."));

    if (!firstName.equals(customer.getFirstName()) || !lastName.equals(customer.getLastName())) {
      throw new BackyardWeddingException("SERVICE ERROR: incorrect first or last name");
    }

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setCustomerId(customer.getCustomerId());
    customerDTO.setFirstName(customer.getFirstName());
    customerDTO.setLastName(customer.getLastName());

    return customerDTO;
  }

  @Override
  public CustomerDTO getCustomer(Integer customerId) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(customerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find customer with that customerId."));

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setCustomerId(customer.getCustomerId());
    customerDTO.setFirstName(customer.getFirstName());
    customerDTO.setLastName(customer.getLastName());

    List<Event> customerEvents = customer.getEvents();
    // copy list entity to list dto
    List<EventDTO> customerEventsDTO = customerEvents.stream().map(entity -> {
      EventDTO dto = new EventDTO();
      dto.setEventId(entity.getEventId());
      dto.setEventName(entity.getEventName());
      dto.setEventDate(entity.getEventDate());
      dto.setBackyardId(entity.getBackyardId());
      return dto;
    }).collect(Collectors.toList());

    customerDTO.setCustomerEvents(customerEventsDTO);

    return customerDTO;
  }

  @Override
  public Integer addEventForCustomer(EventDTO eventDTO) throws BackyardWeddingException {
    Customer customer = customerRepository.findById(eventDTO.getCustomerId()).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find customer with that customerId."));

    List<Event> listOfCustomerEvents = customer.getEvents();
    Event newEvent = new Event();
    newEvent.setEventName(eventDTO.getEventName()); 
    newEvent.setEventDate(eventDTO.getEventDate());

    newEvent.setBackyardId(eventDTO.getBackyardId());

    listOfCustomerEvents.add(newEvent);
    customer.setEvents(listOfCustomerEvents);

    Customer customerAfterSave = customerRepository.save(customer);
    List<Event> customerEventEntityAfterAddition = customerAfterSave.getEvents();
    Event newEventWithId = customerEventEntityAfterAddition.get(customerEventEntityAfterAddition.size() - 1); //gets the last event

    return newEventWithId.getEventId();
  }

  @Override
  public String deleteEventForCustomer(Integer eventId) throws BackyardWeddingException {
//    Customer customer = customerRepository.findById(eventId).orElseThrow(
//        () -> new BackyardWeddingException("SERVICE ERROR: Could not find customer with that customerId."));

    // nulls customerId in event table
    // List<Event> listOfCustomerEvents = customer.getEvents();
    // listOfCustomerEvents.removeIf(event -> event.getEventId().equals(eventId));
    // customer.setEvents(listOfCustomerEvents);
    // customerRepository.save(customer);

    Event eventToRemove = eventRepository.findById(eventId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find event with that eventId"));
    eventRepository.delete(eventToRemove);

    return "SERVICE: event removed successfully.";
  }
  
  @Override
  public EventDTO updateEvent(EventDTO eventDto) throws BackyardWeddingException {
		Event event = eventRepository.findById(eventDto.getEventId())
				.orElseThrow(() -> new BackyardWeddingException("Event not found."));

		event.setBackyardId(eventDto.getBackyardId());
		event.setCustomerId(eventDto.getCustomerId());
		event.setEventDate(eventDto.getEventDate());
		event.setEventId(eventDto.getEventId());
		event.setEventName(eventDto.getEventName());
		
		eventRepository.save(event);
		return eventDto;

	}

@Override
  public List<EventDTO> getAllEventsForCustomer(Integer customerId) throws BackyardWeddingException {
	  List<Event> eventEntityList = (List<Event>) eventRepository.findAll();
	  if (eventEntityList.isEmpty()) {
		  throw new BackyardWeddingException("Event not found");
	  }
	  List<EventDTO> eventDtoList = new LinkedList<EventDTO>();
	  for (Event b : eventEntityList) {
		  if (b.getCustomerId() == customerId) {
			  EventDTO eventDto = new EventDTO();
			  eventDto.setBackyardId(b.getBackyardId());
			  eventDto.setEventDate(b.getEventDate());
			  eventDto.setEventId(b.getEventId());
			  eventDto.setEventName(b.getEventName());
			  eventDto.setCustomerId(b.getCustomerId());
			  eventDtoList.add(eventDto);	
		  }
		  
	  }
	  
	  return eventDtoList;
  }

public String deleteCustomer(Integer customerId) throws BackyardWeddingException {
	Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new BackyardWeddingException("Could not find customer with that ID"));

	customerRepository.delete(customer);	
	return "Account deleted.";	

}

public List<BackyardDTO> getAllBackyards() throws BackyardWeddingException {
	List<Backyard> backyardEntityList = (List<Backyard>) backyardRepository.findAll();
	if (backyardEntityList.isEmpty()) {
		throw new BackyardWeddingException("No backyards found.");
	}
	
	List<BackyardDTO> dtoList = new LinkedList<BackyardDTO>();
	backyardEntityList.forEach(entity -> {
		BackyardDTO dto = new BackyardDTO();
		dto.setBackyardCity(entity.getBackyardCity());
		dto.setBackyardCost(entity.getBackyardCost());
		dto.setBackyardDescription(entity.getBackyardDescription());
		dto.setBackyardId(entity.getBackyardId());
		dto.setBackyardRating(entity.getBackyardRating());
		dto.setPartnerId(entity.getPartnerId());
		dtoList.add(dto);
	});
	return dtoList;
}
}
