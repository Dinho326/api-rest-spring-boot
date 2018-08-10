package br.com.agendaAPI.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.agendaAPI.model.Contact;
import br.com.agendaAPI.repository.ContactRepository;
import br.com.agendaAPI.service.exceptions.ContactNotFound;

@Service
public class ContactService extends GroupContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public List<Contact> getList(){
		return contactRepository.findAll();
	}
	
	public Contact save(Contact contact) {
		contact.setId(null);
		return contactRepository.save(contact);
		
	}
	
	public void delete(Long id) {
		try {
			contactRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ContactNotFound(CONTATO_NOT_FOUND);
		}
	}
	
	
	public void update(Contact contact) {
		Contact c = isExistContact(contact.getId());
		c.setName(contact.getName()!=null? contact.getName(): c.getName());
		c.setNumber(contact.getName()!=null? contact.getNumber(): c.getNumber());
		c.setCreateDate(contact.getCreateDate()==null?  contact.getCreateDate(): new Date());
		contactRepository.saveAndFlush(c);
	}
	
}
