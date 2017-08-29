package com.rudra.aks.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/*
 * Adding configuration for security
 * however it has be registered with container to enable security 
 * @see SecurityInit.java
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	@Qualifier("userDetailsServiceImpl")
	UserDetailsService userDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	/*@Autowired
	PersistentTokenRepository	tokenRepository;
	
	@Bean
	public DaoAuthenticationProvider	daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}*/
	
	/*@Override
 	public void configure(WebSecurity web) throws Exception {
 		web.ignoring()
 		// Spring Security should completely ignore URLs starting with /public/
 				.antMatchers("/public/**");
 	}*/


	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.authorizeRequests()
 			.and().addFilterBefore(new CustomLogoutFilter(), BasicAuthenticationFilter.class)
 			.authorizeRequests()
 			.mvcMatchers("/", "/public/**").permitAll()
 			.antMatchers("/user/register/**", "/user/create/**").permitAll()
 			.antMatchers("/user/login*", "/user/listAll/**").access("hasRole('USER') or hasRole('ADMIN')")
 			.antMatchers("/user/**").hasRole("ADMIN")//.anyRequest()//.fullyAuthenticated()
 			.and().exceptionHandling().accessDeniedPage("/user/accessDenied")
 			.and()
 			.formLogin()//.loginPage("/user/login") // enable custome form based log in
 			//.loginProcessingUrl("/user/login")
 			.permitAll()
 			.and()
 			.logout()//.logoutUrl("/logout")
 			.deleteCookies("remember-me", "JSESSIONID")
 			.logoutSuccessUrl("/")
 			.permitAll()
 			.and()
 			.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(12000)
 			//.rememberMe().tokenRepository(tokenRepository).tokenValiditySeconds(12000)
 			.and().sessionManagement().maximumSessions(1);
 	}

	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(userDetailsService);
 		//auth.authenticationProvider(daoAuthenticationProvider());
 		// enable in memory based authentication with a user named "user" and "admin"
 		/*auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
 				.and().withUser("admin").password("admin").roles("USER", "ADMIN");*/
 	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		//db.setCreateTableOnStartup(true);
		db.setDataSource(dataSource);
		return db;
	}
}
