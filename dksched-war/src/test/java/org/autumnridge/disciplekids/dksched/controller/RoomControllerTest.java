package org.autumnridge.disciplekids.dksched.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.autumnridge.disciplekids.dksched.room.data.RoomDao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:app-context/test.xml",
        "classpath:app-context/embeddedDatasource.xml",
        "classpath:app-context/dataAccess.xml"
})
public class RoomControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    RoomDao roomDao;
    
	private RoomController controller = new RoomController();

	@Before
	public void setupTests() {
		controller.setRoomDao(roomDao);		
	}
	
	@Test
	public void testListRooms() {
		ResponseEntity<List<Room>> rooms = controller.query();
		assertEquals(HttpStatus.OK, rooms.getStatusCode());
		assertEquals(6, rooms.getBody().size());
	}
	
	@Test
	public void testGetRoom() {
		ResponseEntity<Room> room = controller.getRoom(1L);
		ResponseEntity<Room> room2 = controller.getRoom(0L);
		ResponseEntity<Room> room3 = controller.getRoom(-1L);
		assertEquals(HttpStatus.OK, room.getStatusCode());
		assertEquals(HttpStatus.OK, room2.getStatusCode());
		assertEquals(HttpStatus.OK, room3.getStatusCode());
		assertEquals("Giraffe", room.getBody().getName());
		assertNull(room2.getBody().getId());
		assertNull(room2.getBody().getName());
		assertNull(room2.getBody().getDescription());
		assertEquals(0, room2.getBody().getDefaultVolunteerSlots());
		assertNull(room3.getBody().getId());
		assertNull(room3.getBody().getName());
		assertNull(room3.getBody().getDescription());
		assertEquals(0, room3.getBody().getDefaultVolunteerSlots());
	}

	@Test
	public void testUpdateRoom() {
		Room r = roomDao.idRoom(1L);
		r.setName("newname").setDescription("newdescription").setDefaultVolunteerSlots(99);
		
		ResponseEntity<Room> room = controller.updateRoom(r);
		Room updated = roomDao.idRoom(1L);
		
		assertEquals(HttpStatus.OK, room.getStatusCode());
		assertEquals("newname", room.getBody().getName());		
		assertEquals("newdescription", room.getBody().getDescription());		
		assertEquals(99, room.getBody().getDefaultVolunteerSlots());		
		assertEquals("newname", updated.getName());		
		assertEquals("newdescription", updated.getDescription());		
		assertEquals(99, updated.getDefaultVolunteerSlots());		
	}

	@Test
	public void testSaveRoom() {
		Room r = new Room().setName("newname2").setDescription("newdescription2").setDefaultVolunteerSlots(999);
		
		ResponseEntity<Room> room = controller.saveRoom(r);
		Room updated = roomDao.idRoom(r.getId());
		
		assertEquals(HttpStatus.OK, room.getStatusCode());
		assertEquals("newname2", room.getBody().getName());		
		assertEquals("newdescription2", room.getBody().getDescription());		
		assertEquals(999, room.getBody().getDefaultVolunteerSlots());		
		assertEquals("newname2", updated.getName());		
		assertEquals("newdescription2", updated.getDescription());		
		assertEquals(999, updated.getDefaultVolunteerSlots());		
	}
	
	@Test
	public void testDeleteRoom() {
		List<Room> initial = controller.query().getBody();
		
		controller.deleteRoom(initial.get(0).getId());
		List<Room> updated = controller.query().getBody();
		
		assertEquals(initial.size()-1, updated.size());
		
		assertEquals(HttpStatus.NOT_FOUND, controller.deleteRoom(99).getStatusCode());
	}
}
