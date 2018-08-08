package br.com.agendaAPI.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.agendaAPI.model.Contato;
import br.com.agendaAPI.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatosResources {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Contato> getContatos(){
			
		return contatoRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public void salvarContato(@RequestBody Contato contato) {
		
		contatoRepository.save(contato);
	}
}
