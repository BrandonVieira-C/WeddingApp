// package com.backyardweddingapp.service;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;

// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.jupiter.MockitoExtension;

// import com.backyardweddingapp.dto.PartnerDTO;
// import com.backyardweddingapp.entity.Backyard;
// import com.backyardweddingapp.entity.Partner;
// import com.backyardweddingapp.exception.BackyardWeddingException;
// import com.backyardweddingapp.repository.BackyardRepository;
// import com.backyardweddingapp.repository.PartnerRepository;

// @ExtendWith(MockitoExtension.class)
// public class PartnerServiceTest {
	
// 	private Partner partner = new Partner();
	
// 	@Mock
// 	PartnerRepository partnerRepo;
	
// 	@Mock 
// 	BackyardRepository backyardRepo;
	
// 	@InjectMocks
// 	BackyardWeddingServiceImpl serviceImpl = new BackyardWeddingServiceImpl();
	
// 	@BeforeEach
// 	public void createPartner() {

// 		partner.setCity("Toronto");
// 		partner.setDob(LocalDate.of(1980, 07, 10));
// 		String email = "dundas_gamil.com";
// 		partner.setEmail(email);
// 		partner.setFirstName("Tony");
// 		partner.setLastName("Ezekeil");
// 		partner.setPartnerId(2);
// 	}
	
// 	@Test
// 	public void addPartnerTest() throws BackyardWeddingException {
// 		PartnerDTO dto = new PartnerDTO();
// 		dto.setCity(partner.getCity());
// 		dto.setDob(partner.getDob());
// 		dto.setEmail(partner.getEmail());
// 		dto.setFirstName(partner.getFirstName());
// 		dto.setLastName(partner.getLastName());
// 		dto.setPartnerId(partner.getPartnerId());
// 		Mockito.when(partnerRepo.save(partner)).thenReturn(partner);
// 		Assertions.assertEquals(2, serviceImpl.addPartner(dto));
// 	}
	
// 	@Test
// 	public void getPartnerTest() throws BackyardWeddingException {
// 		Mockito.when(partnerRepo.findById(partner.getPartnerId())).thenReturn(Optional.of(partner));
// 		Assertions.assertEquals(2, serviceImpl.getPartner(partner.getPartnerId()).getPartnerId());
		
// 	}
	
// 	@Test
// 	public void updatePartnerTest() throws BackyardWeddingException {
// 		PartnerDTO dto = new PartnerDTO();
// 		dto.setCity(partner.getCity());
// 		dto.setDob(partner.getDob());
// 		dto.setEmail("email_changed@wow.com");
// 		dto.setFirstName(partner.getFirstName());
// 		dto.setLastName(partner.getLastName());
// 		dto.setPartnerId(partner.getPartnerId());
// 		Mockito.when(partnerRepo.findById(partner.getPartnerId())).thenReturn(Optional.of(partner));
// 		Assertions.assertEquals("email_changed@wow.com", serviceImpl.updatePartner(dto).getEmail()); 		
		
// 	}
	
// 	@Test
// 	public void deletePartnerTest() throws BackyardWeddingException {
// 		Mockito.when(partnerRepo.findById(partner.getPartnerId())).thenReturn(Optional.of(partner));
// 		Backyard backyard = new Backyard();
// 		backyard.setBackyardId(1);
// 		backyard.setBackyardImage("2");
// 		backyard.setBackyardName("Oasis");
// 		backyard.setCity("Whitby");
// 		backyard.setDescription("Lit");
// 		backyard.setSquareFootage(124);
// 		// backyard.setPartner(partner);
// 		List<Backyard> list = new ArrayList<Backyard>();
// 		list.add(backyard);
// 		Mockito.when(backyardRepo.findByPartner(partner.getPartnerId())).thenReturn(Optional.of(list));
// 		Assertions.assertEquals("Partner account deleted.", serviceImpl.deletePartner(partner.getPartnerId()));
		
// 	}
