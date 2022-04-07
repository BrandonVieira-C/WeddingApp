package com.backyardweddingapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.EventDTO;
import com.backyardweddingapp.dto.PartnerDTO;
import com.backyardweddingapp.entity.Backyard;
import com.backyardweddingapp.entity.Event;
import com.backyardweddingapp.entity.Partner;
import com.backyardweddingapp.exception.BackyardWeddingException;
import com.backyardweddingapp.repository.BackyardRepository;
import com.backyardweddingapp.repository.EventRepository;
import com.backyardweddingapp.repository.PartnerRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PartnerServiceImpl implements PartnerService {

  @Autowired
  private PartnerRepository partnerRepository;

  @Autowired
  private BackyardRepository backyardRepository;
  
  @Autowired
  private EventRepository eventRepository;

  @Override
  public Integer addPartner(PartnerDTO partnerDTO) throws BackyardWeddingException {
    Partner partner = new Partner();
    partner.setFirstName(partnerDTO.getFirstName());
    partner.setLastName(partnerDTO.getLastName());
    // not setting partnerRating here: who actually sets the partnerRating?
    // not setting partnerBackyard here: the backyard will be added later.
    Partner saved = partnerRepository.save(partner);
    return saved.getPartnerId();
  }

  @Override
  public PartnerDTO getPartner(Integer partnerId) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

    PartnerDTO partnerDTO = new PartnerDTO();
    partnerDTO.setPartnerId(partner.getPartnerId());
    partnerDTO.setFirstName(partner.getFirstName());
    partnerDTO.setLastName(partner.getLastName());
    partnerDTO.setPartnerRating(partner.getPartnerRating());

    List<Backyard> partnerBackyards = partner.getBackyards();
    List<BackyardDTO> partnerBackyardsDTO = partnerBackyards.stream().map(entity -> {
      BackyardDTO dto = new BackyardDTO();
      dto.setBackyardId(entity.getBackyardId());
      dto.setBackyardDescription(entity.getBackyardDescription());
      dto.setBackyardRating(entity.getBackyardRating());
      dto.setBackyardCity(entity.getBackyardCity());
      dto.setBackyardCost(entity.getBackyardCost());
      // not setting partnerId here because it seems redundant.
      return dto;
    }).collect(Collectors.toList());
    partnerDTO.setPartnerBackyards(partnerBackyardsDTO);
    return partnerDTO;
  }

  @Override
  public Integer addBackyardForPartner(Integer partnerId, BackyardDTO backyardDTO) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

    List<Backyard> listOfPartnerBackyards = partner.getBackyards();
    Backyard newBackyard = new Backyard();
    newBackyard.setPartnerId(partnerId);
    newBackyard.setBackyardDescription(backyardDTO.getBackyardDescription());
    newBackyard.setBackyardRating(backyardDTO.getBackyardRating()); // not sure who actually sets the backyardRating
    newBackyard.setBackyardCity(backyardDTO.getBackyardCity());
    newBackyard.setBackyardCost(backyardDTO.getBackyardCost());

    listOfPartnerBackyards.add(newBackyard);
    partner.setBackyards(listOfPartnerBackyards);

    Partner partnerAfterSave = partnerRepository.save(partner);
    List<Backyard> partnerBackyardEntityAfterAddition = partnerAfterSave.getBackyards();
    Backyard newBackyardWithId = partnerBackyardEntityAfterAddition.get(partnerBackyardEntityAfterAddition.size() - 1);
    return newBackyardWithId.getBackyardId();
  }

  @Override
  public String deleteBackyardForPartner(Integer partnerId, Integer backyardId) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

    // nulls partnerId in backyard table
    List<Backyard> listOfPartnerBackyards = partner.getBackyards();
    listOfPartnerBackyards.removeIf(backyard -> backyard.getBackyardId().equals(backyardId));
    partner.setBackyards(listOfPartnerBackyards);
    partnerRepository.save(partner);

    Backyard backyard = backyardRepository.findById(backyardId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find backyard with that backyardId."));
    backyardRepository.delete(backyard);

    return "SERVICE: backyard removed successfully.";
  }
  
  @Override
  public List<BackyardDTO> getAllBackyards() throws BackyardWeddingException {
	  List<Backyard> backyardEntityList = (List<Backyard>) backyardRepository.findAll();
	  if (backyardEntityList.isEmpty()) {
		  throw new BackyardWeddingException("Backyard not found");
	  }
	  List<BackyardDTO> backyardDtoList = new LinkedList<BackyardDTO>();
	  for (Backyard b : backyardEntityList) {
		  BackyardDTO backyardDto = new BackyardDTO();
		  backyardDto.setBackyardId(b.getBackyardId());
		  backyardDto.setBackyardCity(b.getBackyardCity());
		  backyardDto.setBackyardDescription(b.getBackyardDescription());
		  backyardDto.setBackyardRating(b.getBackyardRating());
		  backyardDto.setBackyardCost(b.getBackyardCost());
		  backyardDtoList.add(backyardDto);	  
		  
	  }
	  
	  return backyardDtoList;
  }
  

}
