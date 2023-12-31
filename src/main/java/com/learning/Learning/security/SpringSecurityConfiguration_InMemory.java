package com.learning.Learning.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfiguration_InMemory{

	@Bean
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
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http
			.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))			
			.authorizeHttpRequests()
			.requestMatchers("/login/login.html","/template/home.html","/").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.realmName("User Registration System")
			.and()
			.build();
	}
}
