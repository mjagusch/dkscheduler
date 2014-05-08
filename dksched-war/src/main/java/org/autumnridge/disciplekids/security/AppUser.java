package org.autumnridge.disciplekids.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.ImmutableList;

public class AppUser implements Authentication, UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5145228276245682468L;
	private String user;
	private String email;
    private final ImmutableList<GrantedAuthority> authorities;

    public AppUser(String user, String email) {
        ImmutableList.Builder<GrantedAuthority> authBuilder = ImmutableList.builder();
       	authBuilder.add(new SimpleGrantedAuthority("ROLE_ACTIVE"));
       	authBuilder.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // TODO: fix this!
       	authorities = authBuilder.build();
    	
       	this.user = user;
       	this.email = email;
    }
    
	@Override
	public String getName() {
		return user;
	}

	@Override
	public String getPassword() {
		return email;
	}

	@Override
	public String getUsername() {
		return user;
	}

	public String getUser() {
		return user;
	}

	public boolean isAdmin() {
		return false;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public Object getCredentials() {
		return user;
	}

	@Override
	public Object getDetails() {
		return user;
	}

	@Override
	public Object getPrincipal() {
		return user;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated)
			throws IllegalArgumentException {
	}	
}
