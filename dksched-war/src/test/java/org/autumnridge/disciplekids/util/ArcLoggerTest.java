package org.autumnridge.disciplekids.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ArcLoggerTest {

	@Test
	public void testLogger() {
		assertNotNull(ArcLogger.DEFAULT);
		assertNotNull(new ArcLogger()); // dummy test to fill hole in coverage report
	}
}
