package org.autumnridge.disciplekids.dksched.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Time;
import java.util.List;

import org.autumnridge.disciplekids.dksched.room.data.RoomDao;
import org.autumnridge.disciplekids.dksched.schedule.Recurrance;
import org.autumnridge.disciplekids.dksched.schedule.ScheduledDate;
import org.autumnridge.disciplekids.dksched.schedule.data.ScheduleDao;
import org.joda.time.LocalDate;
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
public class ScheduleControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ScheduleDao scheduleDao;
    
    @Autowired
    RoomDao roomDao;
    
	private ScheduleController controller = new ScheduleController();

	@Before
	public void setupTests() {
		controller.setScheduleDao(scheduleDao);		
	}
	
	@Test
	public void testListScheduledDates() {
		ResponseEntity<List<ScheduledDate>> rooms = controller.queryScheduledDates();
		assertEquals(HttpStatus.OK, rooms.getStatusCode());
		assertEquals(4, rooms.getBody().size());
	}
	
	@Test
	public void testGetScheduledDate() {
		ResponseEntity<ScheduledDate> room = controller.getScheduledDate(1L);
		ResponseEntity<ScheduledDate> room2 = controller.getScheduledDate(0L);
		ResponseEntity<ScheduledDate> room3 = controller.getScheduledDate(-1L);
		assertEquals(HttpStatus.OK, room.getStatusCode());
		assertEquals(HttpStatus.OK, room2.getStatusCode());
		assertEquals(HttpStatus.OK, room3.getStatusCode());
		assertNull(room2.getBody().getId());
		assertNull(room2.getBody().getDateScheduled());
		assertNull(room2.getBody().getTimeStart());
		assertNull(room2.getBody().getTimeEnd());
		assertNull(room3.getBody().getId());
		assertNull(room3.getBody().getDateScheduled());
		assertNull(room3.getBody().getTimeStart());
		assertNull(room3.getBody().getTimeEnd());
	}

	@Test
	public void testUpdateScheduledDate() {
		ScheduledDate r = scheduleDao.idScheduledDate(1L);
		r.setDateScheduled(LocalDate.parse("2014-01-01"))
			.setTimeStart(Time.valueOf("10:10:10"))
			.setTimeEnd(Time.valueOf("11:11:11"));
		
		ResponseEntity<ScheduledDate> room = controller.updateScheduledDate(r);
		ScheduledDate updated = scheduleDao.idScheduledDate(1L);
		
		assertEquals(HttpStatus.OK, room.getStatusCode());
		assertEquals(1L, (long) room.getBody().getId());		
		assertEquals("2014-01-01", room.getBody().getDateScheduled().toString());		
		assertEquals("10:10:10", room.getBody().getTimeStart().toString());		
		assertEquals("11:11:11", room.getBody().getTimeEnd().toString());		
		assertEquals(1L, (long) updated.getId());		
		assertEquals("2014-01-01", updated.getDateScheduled().toString());		
		assertEquals("10:10:10", updated.getTimeStart().toString());		
		assertEquals("11:11:11", updated.getTimeEnd().toString());		
	}

	@Test
	public void testSaveScheduledDate() {
		ScheduledDate r = new ScheduledDate()
			.setDateScheduled(LocalDate.parse("2013-12-31"))
			.setTimeStart(Time.valueOf("08:08:08"))
			.setTimeEnd(Time.valueOf("09:09:09"));
		
		ResponseEntity<ScheduledDate> room = controller.saveScheduledDate(r);
		ScheduledDate updated = scheduleDao.idScheduledDate(r.getId());
		
		assertEquals(5L, (long) room.getBody().getId());		
		assertEquals("2013-12-31", room.getBody().getDateScheduled().toString());		
		assertEquals("08:08:08", room.getBody().getTimeStart().toString());		
		assertEquals("09:09:09", room.getBody().getTimeEnd().toString());		
		assertEquals(5L, (long) updated.getId());		
		assertEquals("2013-12-31", updated.getDateScheduled().toString());		
		assertEquals("08:08:08", updated.getTimeStart().toString());		
		assertEquals("09:09:09", updated.getTimeEnd().toString());		
	}
	
	@Test
	public void testDeleteScheduledDate() {
		List<ScheduledDate> initial = controller.queryScheduledDates().getBody();
		
		controller.deleteScheduledDate(initial.get(0).getId());
		List<ScheduledDate> updated = controller.queryScheduledDates().getBody();
		
		assertEquals(initial.size()-1, updated.size());
		
		assertEquals(HttpStatus.NOT_FOUND, controller.deleteScheduledDate(99).getStatusCode());
	}

	@Test
	public void testListRecurrances() {
		ResponseEntity<List<Recurrance>> list = controller.queryRecurrances();
		assertEquals(HttpStatus.OK, list.getStatusCode());
		assertEquals(3, list.getBody().size());
	}
	
	@Test
	public void testGetRecurrances() {
		ResponseEntity<Recurrance> o1 = controller.getRecurrance(1L);
		ResponseEntity<Recurrance> o2 = controller.getRecurrance(0L);
		ResponseEntity<Recurrance> o3 = controller.getRecurrance(-1L);
		assertEquals(HttpStatus.OK, o1.getStatusCode());
		assertEquals(HttpStatus.OK, o2.getStatusCode());
		assertEquals(HttpStatus.OK, o3.getStatusCode());
		assertEquals(1, o1.getBody().getDayOfWeek());
		assertEquals("17:15:00", o1.getBody().getTimeStart().toString());
		assertEquals("18:45:00", o1.getBody().getTimeEnd().toString());
		assertNull(o2.getBody().getId());
		assertEquals(0, o2.getBody().getDayOfWeek());
		assertNull(o2.getBody().getTimeStart());
		assertNull(o2.getBody().getTimeEnd());
		assertNull(o3.getBody().getId());
		assertEquals(0, o3.getBody().getDayOfWeek());
		assertNull(o3.getBody().getTimeStart());
		assertNull(o3.getBody().getTimeEnd());
	}

	@Test
	public void testUpdateRecurrance() {
		Recurrance r = scheduleDao.idRecurrance(1L);
		r.setDayOfWeek(7)
			.setTimeStart(Time.valueOf("10:10:10"))
			.setTimeEnd(Time.valueOf("11:11:11"));
		
		ResponseEntity<Recurrance> original = controller.updateRecurrance(r);
		Recurrance updated = scheduleDao.idRecurrance(1L);
		
		assertEquals(HttpStatus.OK, original.getStatusCode());
		assertEquals(1L, (long) original.getBody().getId());		
		assertEquals(7, original.getBody().getDayOfWeek());		
		assertEquals("10:10:10", original.getBody().getTimeStart().toString());		
		assertEquals("11:11:11", original.getBody().getTimeEnd().toString());		
		assertEquals(1L, (long) updated.getId());		
		assertEquals(7, updated.getDayOfWeek());		
		assertEquals("10:10:10", updated.getTimeStart().toString());		
		assertEquals("11:11:11", updated.getTimeEnd().toString());		
	}

	@Test
	public void testSaveRecurrance() {
		Recurrance r = new Recurrance()
			.setDayOfWeek(5)
			.setTimeStart(Time.valueOf("05:05:05"))
			.setTimeEnd(Time.valueOf("06:06:06"));
		
		ResponseEntity<Recurrance> original = controller.saveRecurrance(r);
		Recurrance updated = scheduleDao.idRecurrance(r.getId());
		
		assertEquals(4L, (long) original.getBody().getId());		
		assertEquals(5, original.getBody().getDayOfWeek());		
		assertEquals("05:05:05", original.getBody().getTimeStart().toString());		
		assertEquals("06:06:06", original.getBody().getTimeEnd().toString());		
		assertEquals(4L, (long) updated.getId());		
		assertEquals(5, updated.getDayOfWeek());		
		assertEquals("05:05:05", updated.getTimeStart().toString());		
		assertEquals("06:06:06", updated.getTimeEnd().toString());		
	}
	
	@Test
	public void testDeleteRecurrance() {
		List<Recurrance> initial = controller.queryRecurrances().getBody();
		
		controller.deleteRecurrance(initial.get(0).getId());
		List<Recurrance> updated = controller.queryRecurrances().getBody();
		
		assertEquals(initial.size()-1, updated.size());
		
		assertEquals(HttpStatus.NOT_FOUND, controller.deleteRecurrance(99).getStatusCode());
	}
}
