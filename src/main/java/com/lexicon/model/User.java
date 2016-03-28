package com.lexicon.model;

import java.util.List;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="customer")
public class User {
	
	@GeneratedValue
	@Id
	private int id;
	@Expose
	private String login;
	
	@OneToMany(mappedBy="user")
	private List<Vocabulary> vocabularies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<Vocabulary> getVocabularies() {
		return vocabularies;
	}
	public void setVocabularies(List<Vocabulary> vocabularies) {
		this.vocabularies = vocabularies;
	}

}
