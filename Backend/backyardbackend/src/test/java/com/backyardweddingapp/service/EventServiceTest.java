// package com.backyardweddingapp.service;

// import java.time.LocalDate;
// import java.util.Optional;

// import com.backyardweddingapp.dto.EventDTO;
// import com.backyardweddingapp.entity.Backyard;
// import com.backyardweddingapp.entity.Customer;
// import com.backyardweddingapp.entity.Event;
// import com.backyardweddingapp.exception.BackyardWeddingException;
// import com.backyardweddingapp.repository.BackyardRepository;
// import com.backyardweddingapp.repository.CustomerRepository;
// import com.backyardweddingapp.repository.EventRepository;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// public class EventServiceTest {
  
//   @Mock
//   private EventRepository eventRepository;

//   @Mock
//   private BackyardRepository backyardRepository;

//   @Mock
//   private CustomerRepository customerRepository;

//   @InjectMocks
//   private BackyardWeddingService eventService = new BackyardWeddingServiceImpl();

//   @Test
//   void addEventValidTest() throws BackyardWeddingException {

//     Backyard backyard = new Backyard();
//     backyard.setBackyardId(13);
//     backyard.setBackyardName("atlantis");
//     Optional<Backyard> backyardContainer = Optional.of(backyard);
//     Mockito.when(backyardRepository.findById(13)).thenReturn(backyardContainer);

//     Customer customer = new Customer();
//     customer.setCustomerEmail("meepmops@gmail.com");
//     customer.setFirstName("meep");
//     customer.setLastName("mops");
//     Optional<Customer> customerContainer = Optional.of(customer);
//     Mockito.when(customerRepository.findById("meepmops@gmail.com")).thenReturn(customerContainer);

//     Event event = new Event();
//     event.setEventId(42);
//     event.setAmountPaid(2442);
//     event.setDateOfEvent(LocalDate.now());

//     EventDTO eventDTO = new EventDTO();
//     eventDTO.setEventId(42);
//     eventDTO.setAmountPaid(event.getAmountPaid());
//     eventDTO.setDateOfEvent(event.getDateOfEvent());
//     eventDTO.setCustomer(customer);
//     eventDTO.setBackyard(backyard);

//     // EventDTO dto = eventService.addEvent("meepmops@gmail.com", 13, eventDTO);
//     // Assertions.assertEquals(2442, dto.getAmountPaid()); //some reason i cant use eventID. must be the GenerationType.IDENTITY
//   }

//   @Test
//   void getEventValidTest() throws BackyardWeddingException {
//     Event event = new Event();
//     event.setEventId(14);
//     event.setAmountPaid(2424);
//     Optional<Event> eventContainer = Optional.of(event);
//     Mockito.when(eventRepository.findById(14)).thenReturn(eventContainer);

//     EventDTO dto = eventService.getEvent(14);
//     Assertions.assertEquals(2424, dto.getAmountPaid());
//   }

//   @Test
//   void updateEventValidTest() throws BackyardWeddingException{
//     Event event = new Event();
//     event.setEventId(27);
//     event.setAmountPaid(72);
//     Optional<Event> eventContainer = Optional.of(event);
//     Mockito.when(eventRepository.findById(27)).thenReturn(eventContainer);
    
//     EventDTO eventDTO = new EventDTO();
//     eventDTO.setEventId(event.getEventId());
//     eventDTO.setAmountPaid(event.getAmountPaid());

//     // EventDTO dto = eventService.updateEvent(eventDTO);
//     // Assertions.assertEquals(72, dto.getAmountPaid());
//   }

//   @Test
//   void deleteEventValidTest() throws BackyardWeddingException {
//     Event event = new Event();
//     event.setEventId(55);
//     event.setAmountPaid(99);
//     Optional<Event> eventContainer = Optional.of(event);
//     Mockito.when(eventRepository.findById(55)).thenReturn(eventContainer);

//     // String successMessage = eventService.deleteEvent(55);
//     // Assertions.assertEquals("Event has been deleted.", successMessage);
//   }

// }