package com.lexicon.repository;

import org.springframework.data.repository.CrudRepository;

import com.lexicon.model.Word;

public interface WordRepository extends CrudRepository<Word, Integer>{
	Word findByNameIgnoreCaseAndFromAndToAndVocabularyCustomerFbLogin(String name, String from, String to, String login);
}
