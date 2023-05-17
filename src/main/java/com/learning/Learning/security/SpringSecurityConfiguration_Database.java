package com.learning.Learning.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.learning.Learning.Service.UserInfoDetailsService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration_Database {
	
	private UserInfoDetailsService userInfoDetailsService;
	
	@Autowired
	public SpringSecurityConfiguration_Database(UserInfoDetailsService userInfoDetailsService) {
		this.userInfoDetailsService =userInfoDetailsService;
	}
	
	
	EmbeddedDatabase dataSource() {
		return  new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setName("dashboard")
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	
	public UserDetailsService userDetailsService() {
		return new UserInfoDetailsService();
	}
	
	
	
	JdbcUserDetailsManager users(DataSource datasource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
		return jdbcUserDetailsManager;
	}
	
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console"))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/h2-console/**").permitAll()
						.anyRequest().authenticated()
				)
				.headers(headers -> headers.frameOptions().sameOrigin())
				.formLogin()
				.and()
				.build();
	}
	
	
	PasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
}
