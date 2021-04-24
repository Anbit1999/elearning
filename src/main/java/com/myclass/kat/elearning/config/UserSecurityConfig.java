package com.myclass.kat.elearning.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.myclass.kat.elearning.filter.AuthFilter;

@Configuration
@EnableWebSecurity
@Order(value = 2)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	// Cach 2
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
	
	//@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/v2/api-docs",
		"/configuration/ui",
		"/swagger-resources/**",
		"/configuration/security",
		"/swagger-ui.html",
		"/webjars/**");
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//			.antMatcher("/api/**")
//			.authorizeRequests()
//			.antMatchers("/api/user/profile","/api/user/password")
//			.authenticated();
		
		http.csrf().disable()
		.antMatcher("/api/**")
		.authorizeRequests()
		.antMatchers("/api/auth/**")
		.permitAll()
		.antMatchers("/api/user/profile","/api/user/password")
		.hasAnyRole("STUDENT","TEACHER")
		.anyRequest()
		.authenticated();
		
		http.addFilter(new AuthFilter(authenticationManager(),userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		//.passwordEncoder(passwordEncoder());
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
