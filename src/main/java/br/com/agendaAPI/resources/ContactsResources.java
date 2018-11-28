package br.com.agendaAPI.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.agendaAPI.model.Contact;
import br.com.agendaAPI.service.ContactService;

@RestController
@RequestMapping("/contatos")
public class ContactsResources {

	@Autowired
	private ContactService contatoService;
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.GET, produces = {
				MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE
	})
	public  ResponseEntity<List<Contact>> getContacts(){
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(contatoService.getList());
	}
	
	@CrossOrigin
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvarContact(@Valid @RequestBody Contact contact) {
		
		contact = contatoService.save(contact);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(contact.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getContact(@PathVariable("id") Long id) {
		
		CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
		Contact c = contatoService.getContactById(id);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(c);
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteContact(@PathVariable("id") Long id) {
		
		contatoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateContact(@Valid @RequestBody Contact contato, @PathVariable("id") Long id) {
		
		contato.setId(id);
		contatoService.update(contato);
		return ResponseEntity.noContent().build();
	}
	
}
