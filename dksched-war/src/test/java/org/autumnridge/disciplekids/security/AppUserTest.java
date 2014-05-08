package org.autumnridge.disciplekids.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppUserTest {

	@Test
	public void testUser() {
		AppUser user = new AppUser("me", "me@somewhere.com");
		assertEquals("me", user.getName());
		assertEquals("me@somewhere.com", user.getPassword());
		assertEquals("me", user.getUsername());
		assertEquals("me", user.getUser());
		assertEquals(true, user.isAuthenticated());
		user.setAuthenticated(false);
		assertEquals(true, user.isAuthenticated());
		assertEquals(false, user.isAdmin());
		assertEquals(true, user.isEnabled());
		assertEquals(true, user.isCredentialsNonExpired());
		assertEquals(true, user.isAccountNonExpired());
		assertEquals(true, user.isAccountNonLocked());
		assertEquals("me", user.getDetails());
		assertEquals("me", user.getPrincipal());
		assertEquals("me", user.getCredentials());
		assertEquals(2, user.getAuthorities().size());
	}
}
