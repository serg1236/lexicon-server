package com.lexicon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lexicon.model.Vocabulary;

public interface VocabularyRepository extends CrudRepository<Vocabulary, Integer>{
	List<Vocabulary> findByUserLogin(String login);
	Vocabulary findByFromAndToAndUserLogin(String from, String to, String userLogin);
}