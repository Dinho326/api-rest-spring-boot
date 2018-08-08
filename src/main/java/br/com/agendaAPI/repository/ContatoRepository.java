package br.com.agendaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agendaAPI.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
