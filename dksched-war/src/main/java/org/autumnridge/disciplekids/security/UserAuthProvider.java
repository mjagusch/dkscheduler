package org.autumnridge.disciplekids.security;

import org.autumnridge.disciplekids.util.ArcLogger;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;


/**
 * THis is used to authenticate NGS user against the database if passwords are set or the configured LDAP setup.
 */
public class UserAuthProvider extends AbstractUserDetailsAuthenticationProvider {
	
	public UserAuthProvider() {
		super();
	}

	/**
	 * does nothing...
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authToken) throws AuthenticationException {
	}

	/**
	 * This will try to authorize against ldap and if that fails it will attempt authentication against the database
	 */
	@Override
	protected UserDetails retrieveUser(String user, UsernamePasswordAuthenticationToken authToken) throws AuthenticationException {
		ArcLogger.DEFAULT.debug("Registering user");
		
		final String email = Strings.emptyToNull((String)authToken.getCredentials());
		
		if(!register(user, email)) {
        	throw new BadCredentialsException("Could not register user.");
        }
		
		AppUser volunteer = new AppUser(user, email);

		return volunteer;
	}
	
    @VisibleForTesting boolean register(String user, String email){
		ArcLogger.DEFAULT.debug("Registered: " + user + " (" + email + ")");
				
		if (user == null) {
			return false;
		}
		
		// TODO: record access in the database
		
    	return true;
    }    
}
