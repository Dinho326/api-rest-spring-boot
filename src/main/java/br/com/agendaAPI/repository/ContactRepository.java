package br.com.agendaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.agendaAPI.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	
}
