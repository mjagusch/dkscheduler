package org.autumnridge.disciplekids.dksched.report;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReportTest {
	@Test
	public void testReport() {
		Report r = new Report();
		r.setId(88L);
		
		assertEquals(88L, (long) r.getId());
		assertTrue(r.checkVersion(0));
		assertFalse(r.checkVersion(1));
	}
}
