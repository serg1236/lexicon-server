package com.lexicon.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;

@Entity
public class Category {
	
	@GeneratedValue
	@Id
	@Expose(deserialize=false, serialize=false)
	private int id;	
	private String name;
	@ManyToOne
	@Expose(serialize=false)
	private Vocabulary vocabulary;
	@ManyToMany(mappedBy="categories")
	private List<Word> words;
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
	public Vocabulary getVocabulary() {
		return vocabulary;
	}
	public void setVocabulary(Vocabulary vocabulary) {
		this.vocabulary = vocabulary;
	}
	public List<Word> getWords() {
		return words;
	}
	public void setWords(List<Word> words) {
		this.words = words;
	}
	
	

}