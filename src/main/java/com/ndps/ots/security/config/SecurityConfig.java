package com.ndps.ots.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ndps.ots.exception.AuthorizationException;
import com.ndps.ots.security.config.jwt.JwtAuthFilter;
import com.ndps.ots.security.config.user.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
    @Bean
    public UserDetailsService getUserDetailsService()
    {
        return new UserDetailsServiceImpl();
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
    	return new BCryptPasswordEncoder();
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
//		daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		
		return daoAuthenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
	{
		try {
			return configuration.getAuthenticationManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Exception: "+e);
			throw new AuthorizationException("Authentication Failed!!!");
		}
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
	{
		// password -> test123 
		try {
			httpSecurity
				.csrf(csrf-> csrf.disable())
				.authorizeHttpRequests(configurer ->
						configurer
							.antMatchers(HttpMethod.POST, "/api/employee/").hasRole("ADMIN")
							.antMatchers(HttpMethod.GET,"/api/employee/").hasAnyRole("ADMIN","MANAGER","DEVELOPER")
							.antMatchers(HttpMethod.GET, "/api/employee/**").hasAnyRole("ADMIN","MANAGER","DEVELOPER")
							.antMatchers(HttpMethod.GET, "/api/employee/department/").hasRole("ADMIN")
							.antMatchers(HttpMethod.PUT, "/api/employee/").hasAnyRole("ADMIN","MANAGER")
							.antMatchers(HttpMethod.DELETE, "/api/employee/**").hasRole("ADMIN")
							.antMatchers(HttpMethod.DELETE, "/api/employee/").hasRole("ADMIN")
							
							.antMatchers(HttpMethod.POST, "/api/department/").hasRole("ADMIN")
							.antMatchers(HttpMethod.GET,"/api/department/").hasAnyRole("ADMIN","MANAGER","DEVELOPER")
							.antMatchers(HttpMethod.GET, "/api/department/**").hasAnyRole("ADMIN","MANAGER","DEVELOPER")
							.antMatchers(HttpMethod.PUT, "/api/department/").hasAnyRole("ADMIN","MANAGER")
							.antMatchers(HttpMethod.DELETE, "/api/department/**").hasRole("ADMIN")
							.antMatchers(HttpMethod.DELETE, "/api/department/").hasRole("ADMIN")
							
							.antMatchers(HttpMethod.GET, "/api/security/user/").hasAnyRole("ADMIN","MANAGER")
							.antMatchers(HttpMethod.PUT, "/api/security/user/").hasRole("ADMIN")
							.antMatchers(HttpMethod.PUT, "/api/security/user/").hasRole("ADMIN")
							.antMatchers(HttpMethod.DELETE, "/api/security/user/**").hasRole("ADMIN")
							.antMatchers(HttpMethod.DELETE, "/api/security/user/").hasRole("ADMIN")
						)
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.httpBasic(Customizer.withDefaults());
			
			return httpSecurity.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Exception: "+e);
			throw new AuthorizationException("Authorization Failed!!!");
 		}	
	}
}
