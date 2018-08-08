package br.com.agendaAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.agendaAPI.model.Contato;
import br.com.agendaAPI.repository.ContatoRepository;
import br.com.agendaAPI.service.exceptions.ContatoNotFound;

@Service
public class ContatoService {

	private final String NOT_FOUND = "Contato não encontrado";
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	public List<Contato> getLista(){
		return contatoRepository.findAll();
	}
	
	
	public Contato getContatoById(Long id){

		Contato c = contatoRepository.findById(id).orElse(null);
		if(c == null) {
			throw new ContatoNotFound(NOT_FOUND);
		}
		return c;
	}
	
	public Contato salvar(Contato contato) {
		contato.setId(null);
		return contatoRepository.save(contato);
		
	}
	
	public void deletar(Long id) {
		try {
			contatoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ContatoNotFound(NOT_FOUND);
		}
	}
	
	
	public void atualizar(Contato contato) {
		isExist(contato);
		contatoRepository.saveAndFlush(contato);
	}
	
	private void isExist(Contato contato) {
		 getContatoById(contato.getId());
	}
}
