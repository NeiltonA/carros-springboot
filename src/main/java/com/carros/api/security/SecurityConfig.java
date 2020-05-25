package com.carros.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)//habilita a seguração por metodo
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("userDetailsService") //quando for fazer essaejeção de dependencia usar esse objeto de indentificador"UserDetailsService" 
	private UserDetailsService userDetailsService;
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		//.formLogin().and()
		.httpBasic()
		.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
		
		auth.userDetailsService(userDetailsService).passwordEncoder(encode);
		
		// autenticação em memoria
		/*auth
	 	.inMemoryAuthentication().passwordEncoder(encode)
	 	.withUser("user").password(encode.encode("user")).roles("USER")
	 	.and()
	 	.withUser("admin").password(encode.encode("admin")).roles("USER", "ADMIN");*/
	 }
	
}
