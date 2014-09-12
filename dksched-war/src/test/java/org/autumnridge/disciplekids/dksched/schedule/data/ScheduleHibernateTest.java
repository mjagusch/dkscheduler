package org.autumnridge.disciplekids.dksched.schedule.data;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Time;
import java.util.List;

import org.autumnridge.disciplekids.dksched.room.data.RoomDao;
import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledRoom;
import org.autumnridge.disciplekids.dksched.schedule.VolunteerInstance;
import org.autumnridge.disciplekids.dksched.volunteer.data.VolunteerDao;
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
		List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(null); 
		assertEquals(12, rooms.size());
	}	

	@Test
	public void testListScheduledRoomsByDate() {
		ScheduledDate scheduledDate = scheduleDao.idScheduledDate(1L);
		List<ScheduledRoom> rooms = scheduleDao.listScheduledRooms(scheduledDate); 
		assertEquals(3, rooms.size());
	}	

	@Test
	public void testListVolunteerInstances() {
		assertEquals(4, scheduleDao.listVolunteerInstances(null).size());
	}	

	@Test
	public void testListVolunteerInstancesByRoom() {
		ScheduledRoom room = scheduleDao.idScheduledRoom(1L);
		assertEquals(1, scheduleDao.listVolunteerInstances(room).size());
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
	
	@Test
	public void testDeleteScheduledRoom() {
		List<ScheduledRoom> initial = scheduleDao.listScheduledRooms(null);
		int expectedSize = initial.size() - 1;
		
		scheduleDao.deleteScheduledRoom(initial.get(0));
		List<ScheduledRoom> updated = scheduleDao.listScheduledRooms(null);
		assertEquals(expectedSize, updated.size());
	}
	
	@Test
	public void testDeleteVolunteerInstance() {
		List<VolunteerInstance> initial = scheduleDao.listVolunteerInstances(null);
		int expectedSize = initial.size() - 1;
		
		scheduleDao.deleteVolunteerInstance(initial.get(0));
		List<VolunteerInstance> updated = scheduleDao.listVolunteerInstances(null);
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
}
