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
	@Column(name="fbLogin")
	private String fbLogin;
	
	@OneToMany(mappedBy="customer")
	private List<Vocabulary> vocabularies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFbLogin() {
		return fbLogin;
	}
	public void setFbLogin(String fbLogin) {
		this.fbLogin = fbLogin;
	}
	public List<Vocabulary> getVocabularies() {
		return vocabularies;
	}
	public void setVocabularies(List<Vocabulary> vocabularies) {
		this.vocabularies = vocabularies;
	}

}
