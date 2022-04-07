// package com.backyardweddingapp.repository;

// import java.time.LocalDate;
// import java.util.Optional;

// import com.backyardweddingapp.entity.Backyard;
// import com.backyardweddingapp.entity.Customer;
// import com.backyardweddingapp.entity.Event;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// @DataJpaTest
// public class EventRepoTest {
  
//   @Autowired
//   private EventRepository eventRepository;

//   private Event event;

//   @BeforeEach
//   void setUp() {
//     //note: you cannot set eventId (why?) it is autoset to 1
//     event = new Event();
//     event.setAmountPaid(999);
//     event.setDateOfEvent(LocalDate.now());

//     Customer customer = new Customer();
//     customer.setCustomerEmail("nicoleMiche@gmail.com");
//     customer.setFirstName("Nicole");
//     event.setCustomer(customer);

//     Backyard backyard = new Backyard();
//     backyard.setBackyardName("popeyes");
//     event.setBackyard(backyard);
//   }

//   @Test
//   void deleteEventValidTest(){
//     eventRepository.save(event);
//     Optional<Event> eventContainer1 = eventRepository.findById(1);
//     Assertions.assertTrue(eventContainer1.isPresent());

//     eventRepository.delete(event);
//     Optional<Event> eventContainer2 = eventRepository.findById(1);
//     Assertions.assertTrue(eventContainer2.isEmpty());
//   }

//   @Test
//   void saveFindByIdValidTest() {
//     Event eventAfterSave = eventRepository.save(event);
//     Optional<Event> eventContainer = eventRepository.findById(eventAfterSave.getEventId());
//     Assertions.assertTrue(eventContainer.isPresent());
//   }

//   @Test
//   void findByIdInvalidTest() {
//     Optional<Event> eventContainer = eventRepository.findById(2);
//     Assertions.assertTrue(eventContainer.isEmpty());
//   }

// }