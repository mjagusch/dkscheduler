package org.autumnridge.disciplekids.dksched.controller;

import java.util.List;

import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.autumnridge.disciplekids.dksched.volunteer.data.VolunteerDao;
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
public class VolunteerController {

  private VolunteerDao volunteerDao;
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/volunteers", method = RequestMethod.GET)
  public ResponseEntity<List<Volunteer>> query(){
    List<Volunteer> volunteers = volunteerDao.listVolunteers();
    return new ResponseEntity<List<Volunteer>>(volunteers, HttpStatus.OK);
  }	
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/volunteers/{volunteerId}", method = RequestMethod.GET)
  public ResponseEntity<Volunteer> getVolunteer(@PathVariable long volunteerId){
    Volunteer volunteer = null;
    if ( volunteerId > 0 ) {
    	volunteer = volunteerDao.idVolunteer(volunteerId);
    } else {
    	volunteer = new Volunteer();
    }
    return new ResponseEntity<Volunteer>(volunteer, HttpStatus.OK);
  }
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/volunteers", method = RequestMethod.PUT)
  public ResponseEntity<Volunteer> updateVolunteer(@RequestBody Volunteer volunteer){
	Volunteer current = volunteerDao.idVolunteer(volunteer.getId());
	current.merge(volunteer);
    volunteerDao.saveOrUpdateVolunteer(current);
    
    return new ResponseEntity<Volunteer>(volunteer, HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/volunteers", method = RequestMethod.POST)
  public ResponseEntity<Volunteer> saveVolunteer(@RequestBody Volunteer volunteer){
    volunteerDao.saveOrUpdateVolunteer(volunteer);
    
    return new ResponseEntity<Volunteer>(volunteer, HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
@PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/volunteers", method = RequestMethod.DELETE)
  public ResponseEntity deleteVolunteer(@RequestParam long id){
	Volunteer existing = volunteerDao.idVolunteer(id);
	if ( existing == null ) {
		return new ResponseEntity<Volunteer>(HttpStatus.NOT_FOUND);
	}
    volunteerDao.deleteVolunteer(existing);
    
    return new ResponseEntity(HttpStatus.OK);
  }	

  @Autowired
  public void setVolunteerDao(VolunteerDao volunteerDao) {
	  this.volunteerDao = volunteerDao;
  }
}
