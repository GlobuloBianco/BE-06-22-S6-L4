package it.epicode.be.prenotazioni.config;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.epicode.be.prenotazioni.model.Role;
import it.epicode.be.prenotazioni.model.User;
import it.epicode.be.prenotazioni.service.DaoService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DaoService dao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		
			.authorizeRequests()
			.antMatchers("/", "/about")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
				.successForwardUrl("/login_success")
			.and()
			.logout()
			.and()
			.csrf()
				.disable();

	}
	
	//QUESTO METODO GESTISCE TRAMITE L'ID DELL UTENTE LA POSSIBILITA DI FARE IL LOGIN
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		Optional<User> authUserObj = dao.getUtenteById(1);
		User authUser = authUserObj.get();
		String role = "USER";
		Set<Role> roles = authUser.getRoles();
		
		for( Role r : roles ) {
			if( r.getType().toString().contains("ADMIN") ) {
				role = "ADMIN";
				break;
			}
		}
		
		auth.inMemoryAuthentication()
			.withUser( authUser.getUsername() ).password( passwordEncoder().encode( authUser.getPassword() ) )
			.roles(role);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}