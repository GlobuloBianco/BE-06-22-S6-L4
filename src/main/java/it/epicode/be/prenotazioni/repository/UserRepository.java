package it.epicode.be.prenotazioni.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.epicode.be.prenotazioni.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	Page<User> findByNome(String nome, Pageable pageable);
	
	Optional<User> findByUsername(String nome);

	Optional<User> findById(int id);


}
