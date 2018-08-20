package br.com.agendaAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agendaAPI.model.Contact;
import br.com.agendaAPI.model.Group;
import br.com.agendaAPI.repository.ContactRepository;
import br.com.agendaAPI.repository.GroupRepository;
import br.com.agendaAPI.service.exceptions.ContactNotFound;
import br.com.agendaAPI.service.exceptions.GroupNotFound;

@Service
public class GroupContactService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	public final String CONTATO_NOT_FOUND = "Contato não encontrado";
	public final String GRUPO_NOT_FOUND = "Grupo não encontrado";
	
	/**
	 * 
	 * @param id 
	 * @return
	 */
	public Contact getContactById(Long id){

		Contact c = contactRepository.findById(id).orElse(null);
		if(c == null) {
			throw new ContactNotFound(CONTATO_NOT_FOUND);
		}
		if(!c.getGroups().isEmpty()) {
			
			System.out.println(c.getGroups().get(0).getName());
		}
		return c;
	}
	
	/**
	 * 
	 * @param contato
	 * @return
	 */
	public Contact isExistContact(Long idContato) {
		 return getContactById(idContato);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Group getGrupoById(Long id){

		Group c = groupRepository.findById(id).orElse(null);
		if(c == null) {
			throw new GroupNotFound(GRUPO_NOT_FOUND);
		}
		return c;
	}
	
	/**
	 * 
	 * @param Group
	 * @return
	 */
	public Group isExistGroup(Long idGrupo) {
		 return getGrupoById(idGrupo);
	}
	
	public Group addContactInGroup(Long idGroup, Long idContact) {
		
		Contact c = isExistContact(idContact);
		Group   g = isExistGroup(idGroup);
		List<Contact> list = new ArrayList<>();
		list.add(c);
		g.setContacts(list);
		return groupRepository.saveAndFlush(g);
	}
	
	
public Contact addGrouptInContact(Long idGroup, Long idContact) {
		
		Contact c = isExistContact(idContact);
		Group   g = isExistGroup(idGroup);
		List<Group> list = new ArrayList<>();
		list.add(g);
		c.setGroups(list);
		return contactRepository.saveAndFlush(c);
	}
}
