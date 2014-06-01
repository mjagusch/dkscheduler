package org.autumnridge.disciplekids.dksched.schedule;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.autumnridge.disciplekids.dksched.room.Room;
import org.autumnridge.disciplekids.dksched.volunteer.Volunteer;
import org.junit.Test;

public class VolunteerInstanceTest {

	@Test
	public void testVolunteerInstance() {
		VolunteerInstance vi = new VolunteerInstance()
		.setVolunteer(new Volunteer().setLastname("VOLUNTEER"))
		.setScheduledRoom(new ScheduledRoom().setRoom(new Room().setName("ROOM")));
	
	assertNull(vi.getId());
	assertEquals("ROOM", vi.getScheduledRoom().getRoom().getName());
	assertEquals("VOLUNTEER", vi.getVolunteer().getLastname());
	assertTrue(vi.checkVersion(0));
	assertFalse(vi.checkVersion(1));		
		
	}
}
