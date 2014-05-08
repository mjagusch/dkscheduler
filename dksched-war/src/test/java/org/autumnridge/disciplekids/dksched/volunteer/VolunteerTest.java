package org.autumnridge.disciplekids.dksched.volunteer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class VolunteerTest {

	@Test
	public void testVolunteer() {
		Volunteer v = new Volunteer()
			.setEmail("v@somewhere.com")
			.setLastname("VOLUNTEER")
			.setFirstname("TEST")
			.setMiddlename("Q");
		
		assertNull(v.getId());
		assertEquals("v@somewhere.com", v.getEmail());
		assertEquals("VOLUNTEER", v.getLastname());
		assertEquals("TEST", v.getFirstname());
		assertEquals("Q", v.getMiddlename());
		assertTrue(v.checkVersion(0));
		assertFalse(v.checkVersion(1));
	}
}
