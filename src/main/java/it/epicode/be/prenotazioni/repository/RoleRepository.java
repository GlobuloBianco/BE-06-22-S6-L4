package it.epicode.be.prenotazioni.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.prenotazioni.model.Role;
import it.epicode.be.prenotazioni.model.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<User> findById(int id);
}
