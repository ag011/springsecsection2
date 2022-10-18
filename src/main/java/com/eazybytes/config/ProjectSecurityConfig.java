package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig {

	/**
	 * /myAccount - Secured /myBalance - Secured /myLoans - Secured /myCards -
	 * Secured /notices - Not Secured /contact - Not Secured
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/myAccount").authenticated()
				.antMatchers("/myBalance").authenticated()
				.antMatchers("/myLoans").authenticated()
				.antMatchers("/myCards").authenticated().antMatchers("/notices").permitAll()
				.antMatchers("/contact").permitAll().and().formLogin().and().httpBasic();
		return http.build();
	}
	//@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
		//Aprroach 1

/*
      UserDetails admin = User.withDefaultPasswordEncoder()
			  .username("admin").
			  password("12345")
			  .authorities("admin")
			  .build();
      UserDetails user = User.withDefaultPasswordEncoder()
			  .username("user")
			  .password("12345")
			  .authorities("read")
			  .build();
      return new InMemoryUserDetailsManager(admin,user);
*/

		// Approach 2

//		UserDetails admin = User.withUsername("admin").password("12345").authorities("admin").build();
//		UserDetails user = User.withUsername("user").password("12345").authorities("read").build();
//		return new InMemoryUserDetailsManager(admin, user);


//	}


	/*@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}*/
	// This is important when you are going with approach 2
	@Bean
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}


