package br.com.agendaAPI.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Contact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1728997215676971289L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@JsonInclude(Include.NON_NULL)
	private String numberPhone;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date createDate;
	
	@ManyToMany(cascade = { 
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	    @JoinTable(name = "contact_group",
	        joinColumns = @JoinColumn(name = "contact_id"),
	        inverseJoinColumns = @JoinColumn(name = "group_id")
	    )
		@JsonIgnore
	    private List<Group> groups;
	
	
	@Transient
	private List<Group> allGroups;
	
	public List<String> getGroupsAll() {
		List<String> names = new ArrayList<String>();
		for(Group g: getGroups() ) {
			names.add(g.getName());
		}
		return names;
	}
	
	public Contact() {
		
	}
	
	public Contact(String nome) {
		super();
		setName(nome);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getNumber() {
		return numberPhone;
	}

	public void setNumber(String numero) {
		this.numberPhone = numero;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date dataCriacao) {
		this.createDate = dataCriacao;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> grupos) {
		this.groups = grupos;
	}
	
}
