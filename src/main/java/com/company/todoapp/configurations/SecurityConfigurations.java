package com.company.todoapp.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfigurations  extends WebSecurityConfigurerAdapter {

	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
	                .authorizeRequests().antMatchers("/console/**").permitAll().and()
	                .authorizeRequests().antMatchers("/swagger-ui.html").permitAll();

	        httpSecurity.csrf().disable();
	        httpSecurity.headers().frameOptions().disable();


	    }

	
}
