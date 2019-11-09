package com.web.event.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="Event")
public class EventBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;
	private String  eventName;
	private String  eventStartTime;
	private String  eventEndTime;
	private String  location;
	private Integer maxPeople;
	private Integer price;
	private String  aboutEvent;
	private Blob    eventPic;	
	private String  fileName;
	private Integer exist;
	@Transient
	private MultipartFile eventImage;
	
	public MultipartFile getEventImage() {
		return eventImage;
	}

	public void setEventImage(MultipartFile eventImage) {
		this.eventImage = eventImage;
	}

	public EventBean(Integer eventId,String eventName,String eventStartTime,
			String eventEndTime,String location,Integer maxPeople,Integer price,
			String aboutEvent,Blob eventPic,String fileName) {
		this.eventId=eventId;
		this.eventName=eventName;
		this.eventStartTime=eventStartTime;
		this.eventEndTime=eventEndTime;
		this.location=location;
		this.maxPeople=maxPeople;
		this.price=price;
		this.aboutEvent=aboutEvent;
		this.eventPic=eventPic;
		this.fileName=fileName;	
	}
	
	public EventBean() {}
	
	public Integer getEventId() {
		return eventId;
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
	public String getEventStartTime() {
		return eventStartTime;
	}
	public void setEventStartTime(String eventStartTime) {
		this.eventStartTime = eventStartTime;
	}
	public String getEventEndTime() {
		return eventEndTime;
	}
	public void setEventEndTime(String eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getAboutEvent() {
		return aboutEvent;
	}
	public void setAboutEvent(String aboutEvent) {
		this.aboutEvent = aboutEvent;
	}
	public Blob getEventPic() {
		return eventPic;
	}
	public void setEventPic(Blob eventPic) {
		this.eventPic = eventPic;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getExist() {
		return exist;
	}
	public void setExist(Integer exist) {
		this.exist = exist;
	}
}
