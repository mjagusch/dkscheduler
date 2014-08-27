package org.autumnridge.disciplekids.dksched.schedule;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.Test;

public class ScheduledDateTest {

	@Test
	public void testScheduledDate() {
		ScheduledDate di = new ScheduledDate()
			.setDateScheduled(Date.valueOf("2014-01-01"))
			.setTimeStart(Time.valueOf("10:10:10"))
			.setTimeEnd(Time.valueOf("11:11:11"));
		
		assertNull(di.getId());
		assertEquals(Date.valueOf("2014-01-01"), di.getDateScheduled());
		assertEquals("10:10:10", di.getTimeStart().toString());
		assertEquals("11:11:11", di.getTimeEnd().toString());
		assertTrue(di.checkVersion(0));
		assertFalse(di.checkVersion(1));		
	}
}
