package org.autumnridge.disciplekids.dksched.controller;

import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.autumnridge.disciplekids.dksched.room.data.RoomDao;
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
public class RoomController {

  private RoomDao roomDao;
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/rooms", method = RequestMethod.GET)
  public ResponseEntity<List<Room>> query(){
    List<Room> rooms = roomDao.listRooms();
    return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
  }	
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/rooms/{roomId}", method = RequestMethod.GET)
  public ResponseEntity<Room> getRoom(@PathVariable long roomId){
    Room room = null;
    if ( roomId > 0 ) {
    	room = roomDao.idRoom(roomId);
    } else {
    	room = new Room();
    }
    return new ResponseEntity<Room>(room, HttpStatus.OK);
  }
  
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/rooms", method = RequestMethod.PUT)
  public ResponseEntity<Room> updateRoom(@RequestBody Room room){
	Room current = roomDao.idRoom(room.getId());
	current.merge(room);
    roomDao.saveOrUpdateRoom(current);
    
    return new ResponseEntity<Room>(room, HttpStatus.OK);
  }	

  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/rooms", method = RequestMethod.POST)
  public ResponseEntity<Room> saveRoom(@RequestBody Room room){
    roomDao.saveOrUpdateRoom(room);
    
    return new ResponseEntity<Room>(room, HttpStatus.OK);
  }	

  @SuppressWarnings("rawtypes")
  @PreAuthorize("hasRole('ROLE_ACTIVE')")
  @RequestMapping(value="/rooms", method = RequestMethod.DELETE)
  public ResponseEntity deleteRoom(@RequestParam long id){
	Room existing = roomDao.idRoom(id);
	if ( existing == null ) {
		return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
	}
    roomDao.deleteRoom(existing);
    
    return new ResponseEntity(HttpStatus.OK);
  }	

  @Autowired
  public void setRoomDao(RoomDao roomDao) {
	  this.roomDao = roomDao;
  }
}
