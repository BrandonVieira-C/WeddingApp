 package com.backyardweddingapp.api;

import com.backyardweddingapp.dto.BackyardDTO;
import com.backyardweddingapp.dto.EventDTO;
import com.backyardweddingapp.dto.PartnerDTO;
import com.backyardweddingapp.exception.BackyardWeddingException;
import com.backyardweddingapp.service.PartnerService;

import java.util.List;

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
@RequestMapping(value = "/wedding")
public class PartnerAPI {

  @Autowired
  PartnerService partnerService;

  @PostMapping(value = "/addpartner")
  public ResponseEntity<String> addPartner(@RequestBody PartnerDTO partnerDTO) throws BackyardWeddingException {
    Integer newPartnerId = partnerService.addPartner(partnerDTO);
    String successMsg = "New partner added with new partnerId: " + newPartnerId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }

  @GetMapping(value = "/getpartner")
  public ResponseEntity<PartnerDTO> getPartnerWithId(@RequestBody PartnerDTO partnerDTO)
      throws BackyardWeddingException {
    PartnerDTO returned = partnerService.getPartner(partnerDTO.getPartnerId());
    return new ResponseEntity<PartnerDTO>(returned, HttpStatus.OK);
  }

  @PostMapping(value = "/addbackyard/{partnerId}")
  public ResponseEntity<String> addBackyardForPartner(@PathVariable(name = "partnerId") Integer partnerId,
      @RequestBody BackyardDTO backyardDTO) throws BackyardWeddingException {
    Integer newBackyardId = partnerService.addBackyardForPartner(partnerId, backyardDTO);
    String successMsg = "New backyard added with new backyardId: " + newBackyardId;
    return new ResponseEntity<>(successMsg, HttpStatus.CREATED);
  }
  
  @GetMapping(value = "/getallbackyards")
  public ResponseEntity<List<BackyardDTO>> getAllBackyards() throws BackyardWeddingException {
	  List<BackyardDTO> backyardDtoList = partnerService.getAllBackyards();
	  return new ResponseEntity<>(backyardDtoList, HttpStatus.OK);
  }
  


  @DeleteMapping(value
		  = "/deletebackyard/{partnerId}")
  public ResponseEntity<String> deleteBackyardForPartner(@PathVariable(name = "partnerId") Integer partnerId,
      @RequestBody BackyardDTO backyardDTO) throws BackyardWeddingException {
    String successMsg = partnerService.deleteBackyardForPartner(partnerId, backyardDTO.getBackyardId());
    return new ResponseEntity<>(successMsg, HttpStatus.OK);
  }

}
