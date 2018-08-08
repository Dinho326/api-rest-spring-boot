package br.com.agendaAPI.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.agendaAPI.model.Contato;
import br.com.agendaAPI.repository.ContatoRepository;
import br.com.agendaAPI.service.ContatoService;
import br.com.agendaAPI.service.exceptions.ContatoNotFound;

@RestController
@RequestMapping("/contatos")
public class ContatosResources {

	@Autowired
	private ContatoService contatoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public  ResponseEntity<List<Contato>> getContatos(){
			
		return ResponseEntity.status(HttpStatus.OK).body(contatoService.getLista());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvarContato(@RequestBody Contato contato) {
		
		contato = contatoService.salvar(contato);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(contato.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getContato(@PathVariable("id") Long id) {
		
		Contato c = contatoService.getContatoById(id);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletarContato(@PathVariable("id") Long id) {
		
		contatoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizarContato(@RequestBody Contato contato, @PathVariable("id") Long id) {
		
		contato.setId(id);
		contatoService.atualizar(contato);
		return ResponseEntity.noContent().build();
	}
	
}
