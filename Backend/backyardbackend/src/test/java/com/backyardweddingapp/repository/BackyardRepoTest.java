// package com.backyardweddingapp.repository;

// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import com.backyardweddingapp.entity.Backyard;
// import com.backyardweddingapp.entity.Partner;
// import com.backyardweddingapp.exception.BackyardWeddingException;
// import com.backyardweddingapp.repository.BackyardRepository;
// @DataJpaTest
// public class BackyardRepoTest {
	
// 	private Backyard yard1 = new Backyard();
// 	private Backyard yardfromDB = new Backyard();
// 	Partner partner = new Partner();
	
// 	@Autowired 
// 	BackyardRepository backyardRepo;
	
// 	@BeforeEach
// 	public void createBackyard() {
// 		yard1.setBackyardId(1);
// 		yard1.setBackyardImage("n/a");
// 		yard1.setBackyardName("Garden");
// 		yard1.setCity("Toronto");
// 		yard1.setDescription("Very green");
// 		yard1.setSquareFootage(1500);
		
// 		partner.setCity("Toronto");
// 		partner.setDob(LocalDate.of(1994, 1, 13));
// 		partner.setEmail("raptors@yahoo.ca");
// 		partner.setFirstName("Johnny");
// 		partner.setLastName("Nullo");
// 		partner.setPartnerId(3);
		
// 		// yard1.setPartner(partner);
		
// 		yardfromDB = backyardRepo.save(yard1);
		
// 	}
	
	
// 	@Test
// 	public void saveBackyardTest() throws BackyardWeddingException {
// 		Assertions.assertEquals("Garden", yardfromDB.getBackyardName());
		
// 	}
	
// 	@Test
// 	public void findByIdTest() {
// 		Optional<Backyard> optionalYard = backyardRepo.findById(yardfromDB.getBackyardId());
// 		Assertions.assertTrue(optionalYard.isPresent());
// 	}
	
// 	@Test
// 	public void findByPartnerTest() {
// 		Optional<List<Backyard>> listFromDB = backyardRepo.findByPartner(partner.getPartnerId());
// 		boolean actual = (listFromDB != null);
// 		boolean expected = true;
// 		Assertions.assertEquals(actual, expected);

// 	}
	
// 	@Test
// 	public void deleteBackyardTest() throws BackyardWeddingException {
// 		backyardRepo.delete(yardfromDB);
// 	    Optional<Backyard> backyard = backyardRepo.findById(yardfromDB.getBackyardId());
// 	    Assertions.assertTrue(backyard.isEmpty());
		
// 	}

// }
