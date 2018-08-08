package br.com.agendaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.agendaAPI.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	
}
