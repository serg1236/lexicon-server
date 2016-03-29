package com.lexicon.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lexicon.model.User;
import com.lexicon.model.Vocabulary;

public interface VocabularyRepository extends CrudRepository<Vocabulary, Integer>{
	//List<Vocabulary> findAllCustom(@Param("fbLogin")String fbLogin);
	List<Vocabulary> findByCustomer(User customer);
	Vocabulary findByFromAndToAndCustomerFbLogin(String from, String to, String FbLogin);
}