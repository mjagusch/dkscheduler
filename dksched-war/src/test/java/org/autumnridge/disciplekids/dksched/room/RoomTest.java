package org.autumnridge.disciplekids.dksched.room;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;

public class RoomTest {

	@Test
	public void testRoom() {
		Room r = new Room()
			.setName("NAME")
			.setDescription("DESC")
			.setDefaultVolunteerSlots(99);
		r.setId(88L);
		
		assertEquals(88L, (long) r.getId());
		assertEquals("NAME", r.getName());
		assertEquals("DESC", r.getDescription());
		assertEquals(99, r.getDefaultVolunteerSlots());
		assertTrue(r.checkVersion(0));
		assertFalse(r.checkVersion(1));
	}
	
	@Test
	public void testMerge() {
		
		Room initial = new Room()
		.setName("NAME")
		.setDescription("DESC")
		.setDefaultVolunteerSlots(99);
		initial.setId(88L);
	
		Room toMerge = new Room()
		.setName("newNAME")
		.setDescription("newDESC")
		.setDefaultVolunteerSlots(999);
		toMerge.setId(888L);
	
		initial.merge(toMerge);
		
		assertEquals(88L, (long) initial.getId());
		assertEquals("newNAME", initial.getName());
		assertEquals("newDESC", initial.getDescription());
		assertEquals(999, initial.getDefaultVolunteerSlots());
		assertTrue(initial.checkVersion(0));
		assertFalse(initial.checkVersion(1));
		
		initial.merge(new Room());
		assertEquals(88L, (long) initial.getId());
		assertEquals("newNAME", initial.getName());
		assertEquals("newDESC", initial.getDescription());
		assertEquals(999, initial.getDefaultVolunteerSlots());
		assertTrue(initial.checkVersion(0));
		assertFalse(initial.checkVersion(1));
	}
}
