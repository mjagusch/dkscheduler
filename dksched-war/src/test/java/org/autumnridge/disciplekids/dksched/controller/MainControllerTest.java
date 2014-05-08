package org.autumnridge.disciplekids.dksched.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MainControllerTest {

	@Test
	public void testMain() {
		assertEquals("index", new MainController().getMainPage());
	}
}
