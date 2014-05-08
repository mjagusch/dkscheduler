package org.autumnridge.disciplekids.security;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UserAuthProviderTest {

	@Test
	public void testUserAuthProvider() {
		UserAuthProvider provider = new UserAuthProvider();
		assertEquals("me", provider.retrieveUser("me", new UsernamePasswordAuthenticationToken("me", "me@somewhere.com")).getUsername());
		assertEquals("me@somewhere.com", provider.retrieveUser("me", new UsernamePasswordAuthenticationToken("me", "me@somewhere.com")).getPassword());
		assertEquals(true, provider.register("me", "m,e@somewhere.com"));
		provider.additionalAuthenticationChecks(null, null);
	}
	
	@Test(expected=BadCredentialsException.class)
	public void testBadRetrieve() {
		UserAuthProvider provider = new UserAuthProvider();
		provider.retrieveUser(null, new UsernamePasswordAuthenticationToken("me", "me@somewhere.com"));		
	}
}
