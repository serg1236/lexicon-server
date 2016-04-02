package com.lexicon.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

@Entity
@NamedQuery(name="Vocabulary.findAllCustom", query="select v from Vocabulary v join v.customer c where c in(select u from User u where u.fbLogin=:fbLogin)")
public class Vocabulary {
	
	@GeneratedValue
	@Id
	private int id;	
	@Expose
	@Column(name="fromLang")
	private String from;
	@Expose
	@Column(name="toLang")
	private String to;
	@ManyToOne
	private User customer;
	@OneToMany(mappedBy="vocabulary", fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@Expose
	private List<Word> words;
	@Transient	
	@Expose
	private Set<String> categories;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public List<Word> getWords() {
		return words;
	}
	public void setWords(List<Word> words) {
		this.words = words;
	}
	public Set<String> getCategories() {
		return categories;
	}
	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}
	

}
