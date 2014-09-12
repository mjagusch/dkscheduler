package org.autumnridge.disciplekids.dksched.schedule;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import java.sql.Time;

import org.junit.Test;

public class RecurranceTest {

	@Test
	public void testRecurrance() {
		Recurrance rr = new Recurrance()
			.setDayOfWeek(5)
			.setTimeStart(Time.valueOf("10:10:10"))
			.setTimeEnd(Time.valueOf("11:11:11"));
		
		assertNull(rr.getId());
		assertEquals(5, rr.getDayOfWeek());
		assertEquals("10:10:10", rr.getTimeStart().toString());
		assertEquals("11:11:11", rr.getTimeEnd().toString());
		assertTrue(rr.checkVersion(0));
		assertFalse(rr.checkVersion(1));		
	}

	@Test
	public void testMerge() {
		
		Recurrance initial = new Recurrance()
		.setDayOfWeek(1)
		.setTimeStart(Time.valueOf("10:10:10"))
		.setTimeEnd(Time.valueOf("11:11:11"));
	
		Recurrance toMerge = new Recurrance()
		.setDayOfWeek(7)
		.setTimeStart(Time.valueOf("08:08:08"))
		.setTimeEnd(Time.valueOf("09:09:09"));
	
		initial.merge(toMerge);
		
		assertEquals(7, initial.getDayOfWeek());
		assertEquals("08:08:08", initial.getTimeStart().toString());
		assertEquals("09:09:09", initial.getTimeEnd().toString());
		assertTrue(initial.checkVersion(0));
		assertFalse(initial.checkVersion(1));
		
		initial.merge(new Recurrance());
		assertEquals(0, initial.getDayOfWeek());
		assertNull(initial.getTimeStart());
		assertNull(initial.getTimeEnd());
		assertTrue(initial.checkVersion(0));
		assertFalse(initial.checkVersion(1));
	}
}
