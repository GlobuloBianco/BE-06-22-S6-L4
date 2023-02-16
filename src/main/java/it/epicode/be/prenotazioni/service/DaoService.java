package it.epicode.be.prenotazioni.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.epicode.be.prenotazioni.model.Role;
import it.epicode.be.prenotazioni.model.User;
import it.epicode.be.prenotazioni.repository.RoleRepository;
import it.epicode.be.prenotazioni.repository.UserRepository;

@Service
public class DaoService {
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private RoleRepository rRepo;
	
	public Optional<User> getUtenteById(int id){
		return uRepo.findById(id);
	}
	
	public User saveUtente(User obj) {
		return uRepo.save(obj);
	}
	
	public Role saveRole(Role obj) {
		return rRepo.save(obj);
	}
}
