package com.backyardweddingapp.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class Event {

	@Id
  @Column(name="event_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer eventId;

  @Column(name="event_name")
	private String eventName;

  @Column(name="event_date")
	private LocalDate eventDate;

  @Column(name="backyard_id")
  private Integer backyardId;
  
  @Column(name="customer_id")
  private Integer customerId;

  // -----------------------------------------------------------------------------------------------------------

  
  public Integer getEventId() {
    return eventId;
  }

  public Integer getCustomerId() {
	return customerId;
}

public void setCustomerId(Integer customerId) {
	this.customerId = customerId;
}

public void setEventId(Integer eventId) {
    this.eventId = eventId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public LocalDate getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDate eventDate) {
    this.eventDate = eventDate;
  }

  public Integer getBackyardId() {
    return backyardId;
  }

  public void setBackyardId(Integer backyardId) {
    this.backyardId = backyardId;
  }



}

