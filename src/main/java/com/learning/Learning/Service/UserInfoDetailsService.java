package com.learning.Learning.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.Learning.dto.UserInfo;
import com.learning.Learning.repository.UserInfoJpaRepository;

@Service
public class UserInfoDetailsService implements UserDetailsService{

	@Autowired
	private UserInfoJpaRepository userInfoJpaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserInfo user = userInfoJpaRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(
					"Opps! user not found with user-name: "+ username);
		}
		
		return new User(
				user.getUsername(), user.getPassword(), getAuthorities(user));
	}

	private Collection<GrantedAuthority> getAuthorities(UserInfo user){
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return authorities;
	}

}
