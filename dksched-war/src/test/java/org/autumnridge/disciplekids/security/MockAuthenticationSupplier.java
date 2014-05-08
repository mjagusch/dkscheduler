package org.autumnridge.disciplekids.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.base.Supplier;

/**
 * This will get us one of the users from the database.  Used during testing when we need authenticationt obe
 * passed into the Daos.
 */
public class MockAuthenticationSupplier implements Supplier<UserDetails>, ApplicationContextAware {

//	private ApplicationContext context;

    public UserDetails get() {
        return new AppUser("me", "me@somewhere.com");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.context = applicationContext;
    }
}
