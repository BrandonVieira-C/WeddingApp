package com.backyardweddingapp.service;

import java.util.List;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.EventDTO;
import com.backyardweddingapp.dto.PartnerDTO;
import com.backyardweddingapp.exception.BackyardWeddingException;

public interface PartnerService {
  Integer addPartner(PartnerDTO partnerDTO) throws BackyardWeddingException; //returns newly added partnerId
  PartnerDTO getPartner(Integer partnerId) throws BackyardWeddingException;

  Integer addBackyardForPartner(Integer partnerId, BackyardDTO backyardDTO) throws BackyardWeddingException; //returns newly added backyardId.
  String deleteBackyardForPartner(Integer partnerId, Integer backyardId) throws BackyardWeddingException; //returns success message
  List<BackyardDTO> getAllBackyards() throws BackyardWeddingException;

}
