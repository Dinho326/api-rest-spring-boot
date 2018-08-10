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

import br.com.agendaAPI.model.Contact;
import br.com.agendaAPI.repository.ContactRepository;
import br.com.agendaAPI.service.ContactService;
import br.com.agendaAPI.service.exceptions.ContactNotFound;

@RestController
@RequestMapping("/contatos")
public class ContactsResources {

	@Autowired
	private ContactService contatoService;
	
	@RequestMapping(method=RequestMethod.GET)
	public  ResponseEntity<List<Contact>> getContacts(){
			
		return ResponseEntity.status(HttpStatus.OK).body(contatoService.getList());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvarContact(@RequestBody Contact contact) {
		
		contact = contatoService.save(contact);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(contact.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getContact(@PathVariable("id") Long id) {
		
		Contact c = contatoService.getContactById(id);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
		
		contatoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateContact(@RequestBody Contact contato, @PathVariable("id") Long id) {
		
		contato.setId(id);
		contatoService.update(contato);
		return ResponseEntity.noContent().build();
	}
	
}
