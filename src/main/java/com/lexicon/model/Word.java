package com.lexicon.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.google.gson.annotations.Expose;

@Entity
public class Word {
	
	@GeneratedValue
	@Id
	private int id;
	@Expose
	private String name;
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	@Expose
	private List<String> translations;
	@ManyToOne
	private Vocabulary vocabulary;
	@Expose
	@Column(name="fromLang")
	private String from;
	@Expose
	@Column(name="toLang")
	private String to;
	@Expose
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<String> categories;
	
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
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
