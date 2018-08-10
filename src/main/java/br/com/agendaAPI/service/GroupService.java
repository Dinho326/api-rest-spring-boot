package br.com.agendaAPI.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.agendaAPI.model.Group;
import br.com.agendaAPI.repository.GroupRepository;
import br.com.agendaAPI.service.exceptions.GroupNotFound;

@Service
public class GroupService extends GroupContactService{

	
	@Autowired
	private GroupRepository groupRepository;
	
	
	public List<Group> getList(){
		return groupRepository.findAll();
	}
	
	
	public Group save(Group group) {
		group.setId(null);
		return groupRepository.save(group);
		
	}
	
	public void delete(Long id) {
		try {
			groupRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new GroupNotFound(GRUPO_NOT_FOUND);
		}
	}
	
	
	public void update(Group group) {
		Group g = isExistGroup(group.getId());
		g.setName(group.getName()!=null? group.getName(): g.getName());
		g.setCreateDate(group.getCreateDate()==null?  group.getCreateDate(): new Date());
		groupRepository.saveAndFlush(g);
	}
	
}
