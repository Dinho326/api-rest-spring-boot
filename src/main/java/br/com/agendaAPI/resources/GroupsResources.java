package br.com.agendaAPI.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.agendaAPI.model.Contact;
import br.com.agendaAPI.model.Group;
import br.com.agendaAPI.service.GroupService;

@RestController
@RequestMapping("/grupos")
public class GroupsResources {
	
	@Autowired
	private GroupService grupoService;

	@RequestMapping(method=RequestMethod.GET)
	public  ResponseEntity<List<Group>> getGroups(){
			
		return ResponseEntity.status(HttpStatus.OK).body(grupoService.getList());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveGroup(@RequestBody Group group) {
		
		group = grupoService.save(group);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(group.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> getGroup(@PathVariable("id") Long id) {
		
		Group g = grupoService.getGrupoById(id);
		return ResponseEntity.status(HttpStatus.OK).body(g);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteGroup(@PathVariable("id") Long id) {
		
		grupoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateGroup(@RequestBody Group group, @PathVariable("id") Long id) {
		
		group.setId(id);
		grupoService.update(group);
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value="/{idGroup}/{idContact}", method = RequestMethod.POST)
	public ResponseEntity<Void> saveContactInGroup(@PathVariable("idGroup") Long idGroup, @PathVariable("idContact") Long idContact) {
		
		Contact contact = grupoService.addGrouptInContact(idGroup, idContact);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
