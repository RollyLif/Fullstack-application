package com.learning.Learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SpringSecurityConfiguration_InMemory extends WebSecurityConfiguration {

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN", "USER");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/api/user/").hasRole("USER")
				.requestMatchers(HttpMethod.POST, "/api/user/").hasRole("USER")
				.requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("USER")
				.requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN").and().csrf().disable();
	}
}
