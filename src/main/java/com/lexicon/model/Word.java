package com.lexicon.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class Word {
	
	@GeneratedValue
	@Id
	private int id;
	@Expose
	private String name;
	@ElementCollection
	@Expose
	private List<String> translations;
	@ManyToMany
	private List<Category> categories;
	@ManyToOne
	private Vocabulary vocabulary;
	@Expose
	private String from;
	@Expose
	private String to;
	
	private String imageToken;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTranslations() {
		return translations;
	}
	public void setTranslations(List<String> translations) {
		this.translations = translations;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public Vocabulary getVocabulary() {
		return vocabulary;
	}
	public void setVocabulary(Vocabulary vocabulary) {
		this.vocabulary = vocabulary;
	}
	public String getImageToken() {
		return imageToken;
	}
	public void setImageToken(String imageToken) {
		this.imageToken = imageToken;
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
	

}
