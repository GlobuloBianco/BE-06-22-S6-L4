package it.epicode.be.prenotazioni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import it.epicode.be.prenotazioni.model.Role;
import it.epicode.be.prenotazioni.model.RoleListe;
import it.epicode.be.prenotazioni.model.User;

@Configuration
public class Beans {
	
	@Bean
	@Scope("prototype")
	public User utente(String username, String nome, String email, String password) {
		return User.builder()
				.username(username)
				.nome(nome)
				.email(email)
				.password(password)
				.build();
	}
	
	@Bean
	@Scope("prototype")
	public Role role(RoleListe rt) {
		return Role.builder()
				.type(rt)
				.build();
	}

}