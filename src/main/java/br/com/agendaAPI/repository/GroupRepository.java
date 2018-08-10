package br.com.agendaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.agendaAPI.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
