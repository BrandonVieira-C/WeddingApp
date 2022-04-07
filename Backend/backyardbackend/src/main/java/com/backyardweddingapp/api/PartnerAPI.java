package com.backyardweddingapp.api;

import java.util.List;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.PartnerDTO;
import com.backyardweddingapp.exception.BackyardWeddingException;
import com.backyardweddingapp.service.PartnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/partner-api")
public class PartnerAPI {

  @Autowired
  PartnerService partnerService;

  @GetMapping(value="/getallpartner")
  public ResponseEntity<List<PartnerDTO>> getAllPartner() throws BackyardWeddingException {
    List<PartnerDTO> partners = null;
    partners = partnerService.getAllPartner();
    return new ResponseEntity<>(partners, HttpStatus.OK);
  }

  @PostMapping(value = "/addpartner")
  public ResponseEntity<String> addCustomer(@RequestBody PartnerDTO partnerDTO) throws BackyardWeddingException {
    Integer newPartnerId = partnerService.addPartner(partnerDTO);
    String successMsg = "New partner added with new partnerId: " + newPartnerId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }

  @DeleteMapping(value="/deletepartner/{partnerId}")
  public ResponseEntity<String> deletePartner(@PathVariable(name="partnerId") Integer partnerId) throws BackyardWeddingException {
    String successMsg = partnerService.deletePartner(partnerId);
    return new ResponseEntity<>(successMsg, HttpStatus.OK);
  }

  @PostMapping(value="/login")
  public ResponseEntity<PartnerDTO> authenticatePartner(@RequestBody PartnerDTO partnerDTO) throws BackyardWeddingException {
    PartnerDTO dto = partnerService.authenticatePartner(partnerDTO.getPartnerId(), partnerDTO.getFirstName(), partnerDTO.getLastName());
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping(value="/getallbackyards/{partnerId}")
  public ResponseEntity<List<BackyardDTO>> getPartnerBackyards(@PathVariable(name="partnerId") Integer partnerId) throws BackyardWeddingException{
    List<BackyardDTO> list = partnerService.getPartnerBackyards(partnerId);
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PostMapping(value = "/addbackyard")
  public ResponseEntity<String> addBackyardForPartner(@RequestBody BackyardDTO backyardDTO) throws BackyardWeddingException {
    Integer newBackyardId = partnerService.addBackyardForPartner(backyardDTO);
    String successMsg = "New backyard added with new backyardId: " + newBackyardId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/deletebackyard/{backyardId}")
  public ResponseEntity<String> deleteBackyardForPartner(@PathVariable(name="backyardId") Integer backyardId)
      throws BackyardWeddingException {
    String successMsg = partnerService.deleteBackyard(backyardId);
    return new ResponseEntity<>(successMsg, HttpStatus.OK);
  }

}
