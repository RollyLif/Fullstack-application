package com.learning.Learning.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SpringSecurityConfiguration_InMemory{

	@Bean
	protected InMemoryUserDetailsManager userDetailsManager() {
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
	protected SecurityFilterChain configure1(HttpSecurity http) throws Exception {
		return http.httpBasic()
				.realmName("user registration system")
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/login/login.html", "/template/home.html","/","/h2-console/")
				.permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and()
				.build();
	}
}
