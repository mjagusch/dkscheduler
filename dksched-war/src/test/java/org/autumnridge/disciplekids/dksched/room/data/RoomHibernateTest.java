package org.autumnridge.disciplekids.dksched.room.data;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledRoom;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.autumnridge.disciplekids.dksched.schedule.data.ScheduleDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:app-context/test.xml",
        "classpath:app-context/embeddedDatasource.xml",
        "classpath:app-context/dataAccess.xml"
})
public class RoomHibernateTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    RoomDao roomDao;
    
    @Autowired ScheduleDao scheduleDao;
    
	@Test
	public void testRoomSave() {
		Room r = new Room();
		r.setName("NAME");
		r.setDescription("DESC");
		r.setDefaultVolunteerSlots(99);

		roomDao.saveOrUpdateRoom(r);
		
		Room loaded = roomDao.idRoom(r.getId());
		
		assertEquals("NAME", loaded.getName());
		assertEquals("DESC", loaded.getDescription());
		assertEquals(99, loaded.getDefaultVolunteerSlots());
	}
	
	@Test
	public void testBadId() {
		assertNull(roomDao.idRoom(null));
		assertNull(roomDao.idRoom(999L));
	}
	
	@Test
	public void testListRooms() {
		assertEquals(6, roomDao.listRooms().size());
	}
	
	@Test
	public void testDeleteRoom() {
		List<Room> initial = roomDao.listRooms();
		int expectedSize = initial.size() - 1;
		
		List<ScheduledRoom> scheduledRooms = scheduleDao.listScheduledRooms(null,  initial.get(0));
		for ( ScheduledRoom sr : scheduledRooms ) {
			List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(sr, null);
			for ( VolunteerInstance v : volunteers ) {
				scheduleDao.deleteVolunteerInstance(v);
			}
			scheduleDao.deleteScheduledRoom(sr);
		}
		
		roomDao.deleteRoom(initial.get(0));
		List<Room> updated = roomDao.listRooms();
		assertEquals(expectedSize, updated.size());
	}	
}
