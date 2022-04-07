package com.backyardweddingapp.service;

import java.util.List;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.PartnerDTO;
import com.backyardweddingapp.exception.BackyardWeddingException;

public interface PartnerService {
  Integer addPartner(PartnerDTO partnerDTO) throws BackyardWeddingException; //returns newly added partnerId
  String deletePartner(Integer partnerId) throws BackyardWeddingException;
  List<PartnerDTO> getAllPartner() throws BackyardWeddingException;
  PartnerDTO authenticatePartner(Integer partnerId, String firstName, String lastName) throws BackyardWeddingException;

  List<BackyardDTO> getPartnerBackyards(Integer partnerId) throws BackyardWeddingException;

  Integer addBackyardForPartner(BackyardDTO backyardDTO) throws BackyardWeddingException; //returns newly added backyardId.
  String deleteBackyard(Integer backyardId) throws BackyardWeddingException; //returns success message
}
