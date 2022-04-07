// package com.backyardweddingapp.service;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.Optional;

// import com.backyardweddingapp.dto.CustomerDTO;
// import com.backyardweddingapp.entity.Customer;
// import com.backyardweddingapp.exception.BackyardWeddingException;
// import com.backyardweddingapp.repository.CustomerRepository;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// public class BackyardServiceTest {

//   private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

//   @Mock
//   private CustomerRepository customerRepository;

//   @InjectMocks
//   private BackyardWeddingService customerService = new BackyardWeddingServiceImpl();

//   @Test
//   void addCustomerValidTest() throws BackyardWeddingException {

//     CustomerDTO customerDTO = new CustomerDTO();
//     customerDTO.setCustomerEmail("annTseng@gmail.com");
//     customerDTO.setFirstName("Ann");
//     customerDTO.setLastName("Tseng");
//     customerDTO.setCity("Taiwan");
//     String date = "16/09/1999";
//     LocalDate dob = LocalDate.parse(date, formatter);
//     customerDTO.setDob(dob);

//     Assertions.assertEquals("annTseng@gmail.com", customerService.addCustomer(customerDTO));
//   }

//   @Test
//   void getCustomerValidTest() throws BackyardWeddingException {

//     Customer customer = new Customer();
//     customer.setCustomerEmail("kenWoo@gmail.com");
//     customer.setLastName("Woo");
//     Optional<Customer> customerContainer = Optional.of(customer);
//     Mockito.when(customerRepository.findById("kenWoo@gmail.com")).thenReturn(customerContainer);

//     CustomerDTO customerDTO = customerService.getCustomer(customer.getCustomerEmail());
//     Assertions.assertEquals("Woo", customerDTO.getLastName());
//   }

//   @Test
//   void updateCustomerValidTest() throws BackyardWeddingException {

//     Customer customer = new Customer();
//     customer.setCustomerEmail("yumeFukao@gmail.com");
//     customer.setFirstName("Yume");
//     customer.setLastName("Fukao");
//     Optional<Customer> customerContainer = Optional.of(customer);
//     Mockito.when(customerRepository.findById("yumeFukao@gmail.com")).thenReturn(customerContainer);

//     CustomerDTO customerDTO = new CustomerDTO();
//     customerDTO.setCustomerEmail("yumeFukao@gmail.com");
//     customerDTO.setFirstName("Yumemi");

//     // this seems kinda stupid because we don't actually know if data is actually updated since return type: void
//     Assertions.assertDoesNotThrow(() -> customerService.updateCustomer(customerDTO));
//   }


//   @Test
//   void deleteCustomerValidTest() throws BackyardWeddingException {

//     Customer customer = new Customer();
//     customer.setCustomerEmail("noahCuckriet@gmail.com");
//     Optional<Customer> customerContainer = Optional.of(customer);
//     Mockito.when(customerRepository.findById("noahCuckriet@gmail.com")).thenReturn(customerContainer);

//     CustomerDTO customerDTO = customerService.getCustomer("noahCuckriet@gmail.com");
//     Assertions.assertTrue(customerDTO.getCustomerEmail() != null);

//     // this seems kinda stupid because we don't actually know if data is actually updated since return type: void
//     Assertions.assertDoesNotThrow(() -> customerService.deleteCustomer("noahCuckriet@gmail.com"));
//   }


// }