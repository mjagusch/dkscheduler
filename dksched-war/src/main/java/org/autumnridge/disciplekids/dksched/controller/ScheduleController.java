package org.autumnridge.disciplekids.dksched.controller;

import java.util.List;

import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledRoom;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.autumnridge.disciplekids.dksched.schedule.data.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {

  private ScheduleDao scheduleDao;
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/recurrances", method = RequestMethod.GET)
  public ResponseEntity<List<Recurrance>> queryRecurrances(){
    List<Recurrance> recurrances = scheduleDao.listRecurrances();
    return new ResponseEntity<List<Recurrance>>(recurrances, HttpStatus.OK);
  }	
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/recurrances/{recurranceId}", method = RequestMethod.GET)
  public ResponseEntity<Recurrance> getRecurrance(@PathVariable long recurranceId){
    Recurrance recurrance = null;
    if ( recurranceId > 0 ) {
    	recurrance = scheduleDao.idRecurrance(recurranceId);
    } else {
    	recurrance = new Recurrance();
    }
    return new ResponseEntity<Recurrance>(recurrance, HttpStatus.OK);
  }
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/recurrances", method = RequestMethod.PUT)
  public ResponseEntity<Recurrance> updateRecurrance(@RequestBody Recurrance recurrance){
	Recurrance current = scheduleDao.idRecurrance(recurrance.getId());
	current.merge(recurrance);
    scheduleDao.saveOrUpdateRecurrance(current);
    
    return new ResponseEntity<Recurrance>(recurrance, HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/recurrances", method = RequestMethod.POST)
  public ResponseEntity<Recurrance> saveRecurrance(@RequestBody Recurrance recurrance){
    scheduleDao.saveOrUpdateRecurrance(recurrance);
    
    return new ResponseEntity<Recurrance>(recurrance, HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/recurrances", method = RequestMethod.DELETE)
  public ResponseEntity deleteRecurrance(@RequestParam long id){
	Recurrance existing = scheduleDao.idRecurrance(id);
	if ( existing == null ) {
		return new ResponseEntity<Recurrance>(HttpStatus.NOT_FOUND);
	}
    scheduleDao.deleteRecurrance(existing);
    
    return new ResponseEntity(HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-dates", method = RequestMethod.GET)
  public ResponseEntity<List<ScheduledDate>> queryScheduledDates(){
	List<ScheduledDate> scheduledDates = scheduleDao.listScheduledDates();
    return new ResponseEntity<List<ScheduledDate>>(scheduledDates, HttpStatus.OK);
  }	
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-dates/{scheduledDateId}", method = RequestMethod.GET)
  public ResponseEntity<ScheduledDate> getScheduledDate(@PathVariable long scheduledDateId){
    ScheduledDate scheduledDate = null;
    if ( scheduledDateId > 0 ) {
    	scheduledDate = scheduleDao.idScheduledDate(scheduledDateId);
    } else {
    	scheduledDate = new ScheduledDate();
    }
    return new ResponseEntity<ScheduledDate>(scheduledDate, HttpStatus.OK);
  }
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-dates", method = RequestMethod.PUT)
  public ResponseEntity<ScheduledDate> updateScheduledDate(@RequestBody ScheduledDate scheduledDate){
	ScheduledDate current = scheduleDao.idScheduledDate(scheduledDate.getId());
	current.merge(scheduledDate);
    scheduleDao.saveOrUpdateScheduledDate(current);
    
    return new ResponseEntity<ScheduledDate>(scheduledDate, HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-dates", method = RequestMethod.POST)
  public ResponseEntity<ScheduledDate> saveScheduledDate(@RequestBody ScheduledDate scheduledDate){
    scheduleDao.saveOrUpdateScheduledDate(scheduledDate);
    
    return new ResponseEntity<ScheduledDate>(scheduledDate, HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-dates", method = RequestMethod.DELETE)
  public ResponseEntity deleteScheduledDate(@RequestParam long id){
	ScheduledDate existing = scheduleDao.idScheduledDate(id);
	if ( existing == null ) {
		return new ResponseEntity<ScheduledDate>(HttpStatus.NOT_FOUND);
	}
    
    List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(existing);
    for ( ScheduledRoom room : rooms ) {
    	List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(room);
    	for ( VolunteerInstance volunteer: volunteers ) {
    		scheduleDao.deleteVolunteerInstance(volunteer);
    	}
    	scheduleDao.deleteScheduledRoom(room);    	
    }
    
    scheduleDao.deleteScheduledDate(existing);
    
    return new ResponseEntity(HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-rooms", method = RequestMethod.GET)
  public ResponseEntity<List<ScheduledRoom>> queryScheduledRooms(@RequestParam Long scheduledDate_id){
  	ScheduledDate scheduledDate = scheduleDao.idScheduledDate(scheduledDate_id);
    List<ScheduledRoom> scheduledRooms = scheduleDao.listScheduledRooms(scheduledDate);
    return new ResponseEntity<List<ScheduledRoom>>(scheduledRooms, HttpStatus.OK);
  }	
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-rooms/{scheduledRoomId}", method = RequestMethod.GET)
  public ResponseEntity<ScheduledRoom> getScheduledRooms(@PathVariable long scheduledRoomId){
    ScheduledRoom scheduledRoom = null;
    if ( scheduledRoomId > 0 ) {
    	scheduledRoom = scheduleDao.idScheduledRoom(scheduledRoomId);
    } else {
    	scheduledRoom = new ScheduledRoom();
    }
    return new ResponseEntity<ScheduledRoom>(scheduledRoom, HttpStatus.OK);
  }
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-rooms", method = RequestMethod.PUT)
  public ResponseEntity<ScheduledRoom> updateScheduledRoom(@RequestBody ScheduledRoom room){
	ScheduledRoom current = scheduleDao.idScheduledRoom(room.getId());
	current.merge(room);
    scheduleDao.saveOrUpdateScheduledRoom(current);
    
    return new ResponseEntity<ScheduledRoom>(room, HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-rooms", method = RequestMethod.POST)
  public ResponseEntity<ScheduledRoom> saveScheduledRoom(@RequestBody ScheduledRoom room){
    scheduleDao.saveOrUpdateScheduledRoom(room);
    
    return new ResponseEntity<ScheduledRoom>(room, HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-rooms", method = RequestMethod.DELETE)
  public ResponseEntity deleteScheduledRoom(@RequestParam long id){
	ScheduledRoom existing = scheduleDao.idScheduledRoom(id);
	if ( existing == null ) {
		return new ResponseEntity<ScheduledDate>(HttpStatus.NOT_FOUND);
	}
	    
   	List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(existing);
    	for ( VolunteerInstance volunteer: volunteers ) {
    		scheduleDao.deleteVolunteerInstance(volunteer);
    	}
   	scheduleDao.deleteScheduledRoom(existing);    	
	    
   	return new ResponseEntity(HttpStatus.OK);
  }	

  @Autowired
  public void setScheduleDao(ScheduleDao scheduleDao) {
	  this.scheduleDao = scheduleDao;
  }
}
