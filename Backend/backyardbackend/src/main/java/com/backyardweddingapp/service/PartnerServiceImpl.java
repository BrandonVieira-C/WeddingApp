package com.backyardweddingapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.PartnerDTO;
import com.backyardweddingapp.entity.Backyard;
import com.backyardweddingapp.entity.Partner;
import com.backyardweddingapp.exception.BackyardWeddingException;
import com.backyardweddingapp.repository.BackyardRepository;
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
  public List<PartnerDTO> getAllPartner() throws BackyardWeddingException {
    Iterable<Partner> partners = partnerRepository.findAll();

    List<PartnerDTO> partnerDTOs = new ArrayList<>();
    partners.forEach(entity -> {
      PartnerDTO dto = new PartnerDTO();
      dto.setPartnerId(entity.getPartnerId());
      dto.setFirstName(entity.getFirstName());
      dto.setLastName(entity.getLastName());
      dto.setPartnerRating(entity.getPartnerRating());
      // not setting partner backyard here?

      partnerDTOs.add(dto);
    });
    return partnerDTOs;
  }

  @Override
  public String deletePartner(Integer partnerId) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

    partnerRepository.delete(partner);
    return "SERVICE: partner deleted successfully.";
  }

  @Override
  public PartnerDTO authenticatePartner(Integer partnerId, String firstName, String lastName)
      throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

    if (!firstName.equals(partner.getFirstName()) || !lastName.equals(partner.getLastName())) {
      throw new BackyardWeddingException("SERVICE ERROR: incorrect first or last name");
    }

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
  public List<BackyardDTO> getPartnerBackyards(Integer partnerId) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(partnerId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

    List<Backyard> backyards = partner.getBackyards();

    List<BackyardDTO> listBackyards = new ArrayList<>();
    backyards.forEach(partnerBackyard -> {
      BackyardDTO backyard = new BackyardDTO();
      backyard.setBackyardId(partnerBackyard.getBackyardId());
      backyard.setBackyardDescription(partnerBackyard.getBackyardDescription());
      backyard.setBackyardCity(partnerBackyard.getBackyardCity());
      backyard.setBackyardCost(partnerBackyard.getBackyardCost());
      backyard.setBackyardRating(partnerBackyard.getBackyardRating());

      listBackyards.add(backyard);
    });
    return listBackyards;
  }

  @Override
  public Integer addBackyardForPartner(BackyardDTO backyardDTO) throws BackyardWeddingException {
    Partner partner = partnerRepository.findById(backyardDTO.getPartnerId()).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find partner with that partnerId."));

    List<Backyard> listOfPartnerBackyards = partner.getBackyards();
    Backyard newBackyard = new Backyard();
    newBackyard.setPartnerId(backyardDTO.getPartnerId());
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
  public String deleteBackyard(Integer backyardId) throws BackyardWeddingException {
    Backyard backyard = backyardRepository.findById(backyardId).orElseThrow(
        () -> new BackyardWeddingException("SERVICE ERROR: Could not find backyard with that backyardId."));

    backyardRepository.delete(backyard);

    return "SERVICE: backyard removed successfully.";
  }

}