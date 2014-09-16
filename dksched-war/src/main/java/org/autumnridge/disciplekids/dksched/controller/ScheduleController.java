package org.autumnridge.disciplekids.dksched.controller;

import java.sql.Time;
import java.util.Comparator;
import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.autumnridge.disciplekids.dksched.room.data.RoomDao;
import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledRoom;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.autumnridge.disciplekids.dksched.schedule.data.ScheduleDao;
import org.joda.time.LocalDate;
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

import edu.emory.mathcs.backport.java.util.Collections;

@Controller
public class ScheduleController {

  private ScheduleDao scheduleDao;
  private RoomDao roomDao;
  
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
	  
	ScheduledDate newScheduledDate = scheduleDate(scheduledDate.getDateScheduled(), scheduledDate.getTimeStart(), scheduledDate.getTimeEnd(), true);
    
    return new ResponseEntity<ScheduledDate>(newScheduledDate, HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-dates", method = RequestMethod.DELETE)
  public ResponseEntity deleteScheduledDate(@RequestParam long id){
	ScheduledDate existing = scheduleDao.idScheduledDate(id);
	if ( existing == null ) {
		return new ResponseEntity<ScheduledDate>(HttpStatus.NOT_FOUND);
	}
    
    List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(existing, null);
    for ( ScheduledRoom room : rooms ) {
    	List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(room, null);
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
    List<ScheduledRoom> scheduledRooms = scheduleDao.listScheduledRooms(scheduledDate, null);
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
    
	scheduleVolunteers(current);
        
    return new ResponseEntity<ScheduledRoom>(room, HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-rooms", method = RequestMethod.POST)
  public ResponseEntity<ScheduledRoom> saveScheduledRoom(@RequestBody ScheduledRoom room){
	ScheduledRoom newScheduledRoom = scheduleRoom(room.getScheduledDate(), room.getRoom(), true);
    
    return new ResponseEntity<ScheduledRoom>(newScheduledRoom, HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/scheduled-rooms", method = RequestMethod.DELETE)
  public ResponseEntity deleteScheduledRoom(@RequestParam long id){
	ScheduledRoom existing = scheduleDao.idScheduledRoom(id);
	if ( existing == null ) {
		return new ResponseEntity<ScheduledDate>(HttpStatus.NOT_FOUND);
	}
	    
   	List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(existing, null);
    	for ( VolunteerInstance volunteer: volunteers ) {
    		scheduleDao.deleteVolunteerInstance(volunteer);
    	}
   	scheduleDao.deleteScheduledRoom(existing);    	
	    
   	return new ResponseEntity(HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/schedule-recurring", method = RequestMethod.POST)
  public ResponseEntity scheduleRecurring(@RequestParam String toDate){
    System.out.println("schedule-recurring: " + toDate);
	LocalDate now = new LocalDate();
    System.out.println("now: " + now);
	final int dayOfWeek = now.getDayOfWeek();
    System.out.println("dow: " + dayOfWeek);
	
	Comparator<Recurrance> byDOW = new Comparator<Recurrance>() {
		public int compare ( Recurrance left, Recurrance right ) {
			Integer leftRelDOW = (left.getDayOfWeek() - dayOfWeek + (left.getDayOfWeek() - dayOfWeek < 0 ? 7 : 0));
			Integer rightRelDOW = (right.getDayOfWeek() - dayOfWeek + (right.getDayOfWeek() - dayOfWeek < 0 ? 7 : 0));
			return leftRelDOW.compareTo(rightRelDOW);
		}
	};
	
	List<Recurrance> recurrances = scheduleDao.listRecurrances();
	System.out.println("recurrances: " + recurrances.size());
	Collections.sort(recurrances, byDOW); 
	System.out.println("recurrances: " + recurrances.size());
		
	System.out.println("while before");
	LocalDate processing = now;
	while ( processing.isBefore(LocalDate.parse(toDate)) ) {
		System.out.println("    Processing week: " + processing);
		for ( Recurrance r : recurrances ) {
			System.out.println("        Processing recurrance: " + r.getDayOfWeek() + "::" + r.getTimeStart());
			// Get list of schedules for this date and time
			// Create any necessary schedules

			Integer relDOW = (r.getDayOfWeek() - dayOfWeek + (r.getDayOfWeek() - dayOfWeek < 0 ? 7 : 0));
			System.out.println("        Processing recurrance for relative DOW: " + relDOW);
			LocalDate processingDayOfWeek = processing.plusDays(relDOW);
			System.out.println("        Processing recurrance for date: " + processingDayOfWeek);

			scheduleDate(processingDayOfWeek, r.getTimeStart(), r.getTimeEnd(), true);
		}
		
		// Move one week at a time
		processing = processing.plusDays(7);
		System.out.println("    Moving to the next week: " + processing);
	}
	
	System.out.println("return");
    return new ResponseEntity(HttpStatus.OK);
  }	
  
  private ScheduledDate scheduleDate(LocalDate date, Time start, Time end, boolean scheduleRooms) {
	ScheduledDate scheduledDate = scheduleDao.loadScheduledDate(date, start, end);
	if ( scheduledDate == null ) {
		scheduledDate = new ScheduledDate()
			.setDateScheduled(date)
			.setTimeStart(start)
			.setTimeEnd(end);
		scheduleDao.saveOrUpdateScheduledDate(scheduledDate);
		System.out.println("        Created scheduled date: " + scheduledDate.getDateScheduled() + "::" + scheduledDate.getTimeStart());

		if ( scheduleRooms ) {
			List<Room> rooms = roomDao.listRooms();
			for ( Room room : rooms ) {			
				scheduleRoom(scheduledDate, room, scheduleRooms);
			}			
		}
	}
	
	return scheduledDate;
  }

  private ScheduledRoom scheduleRoom(ScheduledDate scheduledDate, Room room, boolean scheduleVolunteers) {
	ScheduledRoom scheduledRoom = scheduleDao.loadScheduledRoom(scheduledDate, room);
	if ( scheduledRoom == null ) {
		scheduledRoom = new ScheduledRoom()
			.setScheduledDate(scheduledDate)
			.setRoom(room)
			.setVolunteerSlots(room.getDefaultVolunteerSlots());
		scheduleDao.saveOrUpdateScheduledRoom(scheduledRoom);
		System.out.println("            Created scheduled room: " + room.getName());

		if ( scheduleVolunteers ) {
			scheduleVolunteers(scheduledRoom);
		}
	}
	
	return scheduledRoom;
  }
  
  private void scheduleVolunteers(ScheduledRoom room) {
	  
	List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(room, null);
	
	// Create any missing volunteer slots
	for ( int i = volunteers.size(); i < room.getVolunteerSlots(); i++ ) {
		VolunteerInstance volunteerInstance = new VolunteerInstance()
			.setScheduledRoom(room);
		scheduleDao.saveOrUpdateVolunteerInstance(volunteerInstance);
		System.out.println("                Created volunteer instance..."+(i+1));
	}
	
	while ( volunteers.size() > room.getVolunteerSlots() ) {
		VolunteerInstance removed = volunteers.remove(volunteers.size()-1);
		scheduleDao.deleteVolunteerInstance(removed);
		System.out.println("                removed volunteer instance..."+(volunteers.size()));		
	}
	
	
  }
  
  @Autowired
  public void setScheduleDao(ScheduleDao scheduleDao) {
	  this.scheduleDao = scheduleDao;
  }

  @Autowired
  public void setRoomDao(RoomDao roomDao) {
	  this.roomDao = roomDao;
  }
}
