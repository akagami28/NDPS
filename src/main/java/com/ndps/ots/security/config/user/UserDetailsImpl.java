package com.ndps.ots.security.config.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = -6451639586178172462L;

	private User user;
	
	public UserDetailsImpl(User user) {
		super();
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
//		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ADMIN");
//		SimpleGrantedAuthority simpleGrantedAuthority2 = new SimpleGrantedAuthority("MANAGER");
//		SimpleGrantedAuthority simpleGrantedAuthority3 = new SimpleGrantedAuthority("DEVELOPER");
//		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
//		simpleGrantedAuthorities.add(simpleGrantedAuthority);
//		simpleGrantedAuthorities.add(simpleGrantedAuthority2);
//		simpleGrantedAuthorities.add(simpleGrantedAuthority3);
		
		return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
		
//		return Arrays.asList(simpleGrantedAuthority,simpleGrantedAuthority2,simpleGrantedAuthority3);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
