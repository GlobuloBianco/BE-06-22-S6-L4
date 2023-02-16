package it.epicode.be.prenotazioni.common.util;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

import it.epicode.be.prenotazioni.config.Beans;
import it.epicode.be.prenotazioni.model.Role;
import it.epicode.be.prenotazioni.model.RoleListe;
import it.epicode.be.prenotazioni.model.User;
import it.epicode.be.prenotazioni.service.DaoService;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Main implements CommandLineRunner{

	private int populateDbFlag = 0;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if(populateDbFlag == 1 ) {
			populateDb();
		}
		getRolesFromUserById(1);
	}
	
	@Autowired
	private DaoService dao;
	

	private void populateDb() {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);
			
			User u1 = (User)ctx.getBean("utente", "globulo","Mario Rossi","mariorossi@gmail.it" , "test");
			Role r1 = (Role)ctx.getBean("role", RoleListe.ROLE_ADMIN);
			Role r2 = (Role)ctx.getBean("role", RoleListe.ROLE_USER);
			
			u1.setRoles(new HashSet<>() {{
				
				add(r1);
			}});
			
			dao.saveRole(r1);
			dao.saveRole(r2);
			dao.saveUtente(u1);
			
			((AnnotationConfigApplicationContext)ctx).close();
			
		}
		
		private void getRolesFromUserById(int id) {
			Optional<User> authUserObj = dao.getUtenteById(id);
			User authUser = authUserObj.get();
			String role = "USER";
			Set<Role> roles = authUser.getRoles();
			
			for( Role r : roles ) {
				if( r.getType().toString().contains("ADMIN") ) {
					role = "ADMIN";
					break;
				}
			}
			
			System.out.println(role);
		}
}
