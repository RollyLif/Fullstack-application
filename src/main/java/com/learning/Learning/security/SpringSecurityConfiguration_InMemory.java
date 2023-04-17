package com.learning.Learning.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration_InMemory{

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.roles("ADMIN","USER")
				.build();
		
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth ->{
					auth.requestMatchers(HttpMethod.GET, "/api/user/").hasRole("USER");
					auth.requestMatchers(HttpMethod.POST, "/api/user/").hasRole("USER");
					auth.requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("USER");
					auth.requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN");
				})
				.httpBasic(Customizer.withDefaults())
				.build();
	}
}
