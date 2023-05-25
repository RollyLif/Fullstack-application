package com.learning.Learning.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfiguration_InMemory{

	//@Bean
	protected InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user = User.withUsername("user")
				.password("password")
				.roles("USER")
				.build();
		
		UserDetails admin = User.withUsername("admin")
				.password("password")
				.roles("ADMIN","USER")
				.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	protected SecurityFilterChain configure1(HttpSecurity http) throws Exception {
		return http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests((auth)-> {
				auth.requestMatchers(HttpMethod.GET, "/api/user/").hasRole("user");
				auth.requestMatchers(HttpMethod.POST, "/api/user/").hasRole("user");
				auth.requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("user");
				auth.requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("admin");
		})
				.httpBasic(Customizer.withDefaults())
				.build();
				
	}
}
