// package com.backyardweddingapp.repository;

// import java.time.LocalDate;
// import java.util.Optional;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.backyardweddingapp.entity.Partner;
// import com.backyardweddingapp.repository.PartnerRepository;

// @SpringBootTest
// public class PartnerRepoTest {
// 	Partner partner = new Partner();
// 	Partner partnerFromDB = new Partner();
	
// 	@Autowired
// 	PartnerRepository partnerRepo;
	
// 	@BeforeEach
// 	public void testPartner() {
// 		partner.setCity("Toronto");
// 		partner.setDob(LocalDate.of(1994, 1, 13));
// 		partner.setEmail("raptors@yahoo.ca");
// 		partner.setFirstName("Johnny");
// 		partner.setLastName("Nullo");
// 		partnerFromDB = partnerRepo.save(partner);
// 	}
	
// 	@Test
// 	public void savePartnerTest() {
// 		Assertions.assertEquals("raptors@yahoo.ca", partnerFromDB.getEmail());	
		
// 	}
	
// 	@Test
// 	public void findByIdTest() {
// 		Optional<Partner> optionalPartner = partnerRepo.findById(partner.getPartnerId());
// 		Assertions.assertTrue(optionalPartner.isPresent());
// 	}
	
// 	@Test
// 	public void deletePartnerTest() {
// 		partnerRepo.delete(partnerFromDB);
// 	    Optional<Partner> optionalPartnerFromDB = partnerRepo.findById(partnerFromDB.getPartnerId());
// 	    Assertions.assertTrue(optionalPartnerFromDB.isEmpty());
		
// 	}

// }