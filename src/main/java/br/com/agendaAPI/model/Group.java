package br.com.agendaAPI.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="Groups")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5821473285489741313L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NaturalId
	private String name;
	
	private Date createDate;

	@JsonInclude(Include.NON_EMPTY)
	@ManyToMany(mappedBy = "groups")
    private List<Contact> contacts;
 
	
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date dataCriacao) {
		this.createDate = dataCriacao;
	}

	public List<Contact> getContatcs() {
		return contacts;
	}

	public void setContacts(List<Contact> contatos) {
		this.contacts = contatos;
	}
	
}
