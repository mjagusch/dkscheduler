package org.autumnridge.disciplekids.dksched.schedule.data;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Time;
import java.util.List;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.autumnridge.disciplekids.dksched.room.data.RoomDao;
import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledRoom;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.autumnridge.disciplekids.dksched.volunteer.data.VolunteerDao;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath:app-context/test.xml",
        "classpath:app-context/embeddedDatasource.xml",
        "classpath:app-context/dataAccess.xml"
})
public class ScheduleHibernateTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ScheduleDao scheduleDao;
    
    @Autowired
    RoomDao roomDao;
    
    @Autowired
    VolunteerDao volunteerDao;
    
	@Test
	public void testScheduledDateSave() {
		ScheduledDate di = new ScheduledDate();
		di.setDateScheduled(LocalDate.parse("2014-01-01"));
		di.setTimeStart(Time.valueOf("10:10:10"));
		di.setTimeEnd(Time.valueOf("11:11:11"));

		scheduleDao.saveOrUpdateScheduledDate(di);
		
		ScheduledDate loaded = scheduleDao.idScheduledDate(di.getId());
		
		assertEquals(di.getId().longValue(), loaded.getId().longValue());
		assertEquals("2014-01-01", loaded.getDateScheduled().toString());
		assertEquals("10:10:10", loaded.getTimeStart().toString());
		assertEquals("11:11:11", loaded.getTimeEnd().toString());
	}
	
	@Test
	public void testVolunteerInstanceSave() {
		VolunteerInstance vi = new VolunteerInstance();
		vi.setScheduledRoom(scheduleDao.idScheduledRoom(1L));
		vi.setVolunteer(null);

		scheduleDao.saveOrUpdateVolunteerInstance(vi);
		
		VolunteerInstance loaded = scheduleDao.idVolunteerInstance(vi.getId());
		
		assertEquals(vi.getId().longValue(), loaded.getId().longValue());
//TODO		assertEquals("Giraffe", loaded.getScheduledRoom().getRoom().getName());
		assertNull(loaded.getVolunteer());
		
		vi.setVolunteer(volunteerDao.idVolunteer(1L));
		scheduleDao.saveOrUpdateVolunteerInstance(vi);
		
		loaded = scheduleDao.idVolunteerInstance(vi.getId());
		assertEquals("mj@gmail.com", loaded.getVolunteer().getEmail());
	}
	
	@Test
	public void testBadId() {
		assertNull(scheduleDao.idScheduledRoom(null));
		assertNull(scheduleDao.idScheduledRoom(999L));
		assertNull(scheduleDao.idVolunteerInstance(null));
		assertNull(scheduleDao.idVolunteerInstance(999L));
		assertNull(scheduleDao.idRecurrance(null));
		assertNull(scheduleDao.idRecurrance(999L));
	}

	@Test
	public void testListScheduledRooms() {
		List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(null, null); 
		assertEquals(12, rooms.size());
	}	

	@Test
	public void testListScheduledRoomsByRoom() {
		List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(null, roomDao.listRooms().get(0)); 
		assertEquals(4, rooms.size());
	}	

	@Test
	public void testListScheduledRoomsByDate() {
		ScheduledDate scheduledDate = scheduleDao.idScheduledDate(1L);
		List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(scheduledDate, null); 
		assertEquals(3, rooms.size());
	}	

	@Test
	public void testListScheduledRoomsByDateAndRoom() {
		ScheduledDate scheduledDate = scheduleDao.idScheduledDate(1L);
		List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(scheduledDate, roomDao.listRooms().get(0)); 
		assertEquals(1, rooms.size());
	}	

	@Test
	public void testListVolunteerInstances() {
		assertEquals(24, scheduleDao.listVolunteerInstances(null, null).size());
	}	

	@Test
	public void testListVolunteerInstancesByRoom() {
		ScheduledRoom room = scheduleDao.idScheduledRoom(1L);
		assertEquals(2, scheduleDao.listVolunteerInstances(room, null).size());
	}
	
	@Test
	public void testListVolunteerInstancesByVolunteer() {
		Volunteer v = volunteerDao.idVolunteer(1L);
		assertEquals(2, scheduleDao.listVolunteerInstances(null, v).size());
	}
	
	@Test
	public void testListVolunteerInstancesByRoomAndVolunteer() {
		ScheduledRoom room = scheduleDao.idScheduledRoom(1L);
		Volunteer v = volunteerDao.idVolunteer(1L);
		assertEquals(1, scheduleDao.listVolunteerInstances(room, v).size());
	}
	
	@Test
	public void testRecurranceSave() {
		Recurrance rr = new Recurrance();
		rr.setDayOfWeek(5);
		rr.setTimeStart(Time.valueOf("10:10:10"));
		rr.setTimeEnd(Time.valueOf("11:11:11"));

		scheduleDao.saveOrUpdateRecurrance(rr);
		
		Recurrance loaded = scheduleDao.idRecurrance(rr.getId());
		
		assertEquals(rr.getId().longValue(), loaded.getId().longValue());
		assertEquals(5, loaded.getDayOfWeek());
		assertEquals("10:10:10", loaded.getTimeStart().toString());
		assertEquals("11:11:11", loaded.getTimeEnd().toString());
	}	

	@Test
	public void testListRecurrances() {
		assertEquals(3, scheduleDao.listRecurrances().size());
	}
	
	@Test(expected = ConstraintViolationException.class) 
	public void testDeleteScheduledDateWithRooms() {
		List<ScheduledDate> initial = scheduleDao.listScheduledDates();
		int expectedSize = initial.size() - 1;
		
		scheduleDao.deleteScheduledDate(initial.get(0));
		List<ScheduledDate> updated = scheduleDao.listScheduledDates();
		assertEquals(expectedSize, updated.size());
	}
	
	@Test 
	public void testDeleteScheduledDateWithoutRooms() {
		
		List<ScheduledDate> initial = scheduleDao.listScheduledDates();
		int expectedSize = initial.size() - 1;
		
		List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(initial.get(0), null);
		for ( ScheduledRoom r : rooms ) {
			List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(r, null);
			for ( VolunteerInstance v : volunteers ) {
				scheduleDao.deleteVolunteerInstance(v);
			}
			scheduleDao.deleteScheduledRoom(r);
		}
		scheduleDao.deleteScheduledDate(initial.get(0));
		List<ScheduledDate> updated = scheduleDao.listScheduledDates();
		assertEquals(expectedSize, updated.size());	
	}
	
	@Test(expected = ConstraintViolationException.class) 
	public void testDeleteScheduledRoomWithVolunteers() {
		List<ScheduledRoom> initial = scheduleDao.listScheduledRooms(null, null);
		int expectedSize = initial.size() - 1;
		
		scheduleDao.deleteScheduledRoom(initial.get(0));
		List<ScheduledRoom> updated = scheduleDao.listScheduledRooms(null, null);
		assertEquals(expectedSize, updated.size());
	}
	
	@Test
	public void testDeleteScheduledRoomWithoutVolunteers() {
		
		List<ScheduledRoom> initial = scheduleDao.listScheduledRooms(null, null);
		int expectedSize = initial.size() - 1;
		
		List<VolunteerInstance> volunteers = scheduleDao.listVolunteerInstances(initial.get(0), null);
		for ( VolunteerInstance v : volunteers ) {
			scheduleDao.deleteVolunteerInstance(v);
		}
		scheduleDao.deleteScheduledRoom(initial.get(0));
		List<ScheduledRoom> updated = scheduleDao.listScheduledRooms(null, null);
		assertEquals(expectedSize, updated.size());
	}
	
	@Test
	public void testDeleteVolunteerInstance() {
		List<VolunteerInstance> initial = scheduleDao.listVolunteerInstances(null, null);
		int expectedSize = initial.size() - 1;
		
		scheduleDao.deleteVolunteerInstance(initial.get(0));
		List<VolunteerInstance> updated = scheduleDao.listVolunteerInstances(null, null);
		assertEquals(expectedSize, updated.size());
	}	

	@Test
	public void testDeleteRecurrance() {
		List<Recurrance> initial = scheduleDao.listRecurrances();
		int expectedSize = initial.size() - 1;
		
		scheduleDao.deleteRecurrance(initial.get(0));
		List<Recurrance> updated = scheduleDao.listRecurrances();
		assertEquals(expectedSize, updated.size());
	}
	
	@Test
	public void testloadScheduledDate() {
		ScheduledDate d1 = scheduleDao.loadScheduledDate(LocalDate.parse("2014-04-19"), Time.valueOf("17:15:00"), Time.valueOf("18:45:00"));
		assertNotNull(d1);
		assertEquals("2014-04-19", d1.getDateScheduled().toString());
		assertEquals("17:15:00", d1.getTimeStart().toString());
		assertEquals("18:45:00", d1.getTimeEnd().toString());
		assertEquals(1, d1.getId().intValue());
		ScheduledDate d2 = scheduleDao.loadScheduledDate(LocalDate.parse("2014-04-19"), Time.valueOf("17:15:00"), Time.valueOf("18:46:00"));
		assertNull(d2);
		ScheduledDate d3 = scheduleDao.loadScheduledDate(LocalDate.parse("2014-04-19"), Time.valueOf("17:16:00"), Time.valueOf("18:45:00"));
		assertNull(d3);
		ScheduledDate d4 = scheduleDao.loadScheduledDate(LocalDate.parse("2014-05-19"), Time.valueOf("17:15:00"), Time.valueOf("18:45:00"));
		assertNull(d4);
	}

	@Test
	public void testloadScheduledRoom() {
		ScheduledDate d1 = scheduleDao.idScheduledDate(1L);
		Room r1 = roomDao.idRoom(1L);
		ScheduledRoom sr1 = scheduleDao.loadScheduledRoom(d1, r1);
		assertNotNull(sr1);
		assertEquals("2014-04-19", sr1.getScheduledDate().getDateScheduled().toString());
		assertEquals("17:15:00", sr1.getScheduledDate().getTimeStart().toString());
		assertEquals("18:45:00", sr1.getScheduledDate().getTimeEnd().toString());
		assertEquals("Giraffe", sr1.getRoom().getName());

		ScheduledDate d2 = new ScheduledDate().setDateScheduled(new LocalDate()).setTimeStart(Time.valueOf("11:00:00")).setTimeEnd(Time.valueOf("12:00:00"));
		scheduleDao.saveOrUpdateScheduledDate(d2);
		Room r2 = new Room().setName("newroom").setDefaultVolunteerSlots(99);
		roomDao.saveOrUpdateRoom(r2);
		
		ScheduledRoom sr2 = scheduleDao.loadScheduledRoom(d1,  r2);
		assertNull(sr2);
		
		ScheduledRoom sr3 = scheduleDao.loadScheduledRoom(d2,  r1);
		assertNull(sr3);
	}
}
