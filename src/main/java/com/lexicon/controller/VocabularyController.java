package com.lexicon.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexicon.model.User;
import com.lexicon.model.Vocabulary;
import com.lexicon.repository.UserRepository;
import com.lexicon.repository.VocabularyRepository;

@RestController
@RequestMapping("/vocabylary")
public class VocabularyController {
	
	@Autowired
	private VocabularyRepository vocabularyRepository;
	@Autowired
	private UserRepository userRepository;
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	@RequestMapping(value="getAll", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getAll(@RequestParam String login) {
		User user = userRepository.findByLogin(login);
		List<Vocabulary> vocabularies = null;
		if(user != null) {
			vocabularies = vocabularyRepository.findByUserLogin(login);
		} else {
			user = new User();
			user.setLogin(login);
			userRepository.save(user);
			vocabularies = new ArrayList<Vocabulary>();
		}
		return gson.toJson(vocabularies);
		
	}

	public void setVocabularyRepository(VocabularyRepository vocabularyRepository) {
		this.vocabularyRepository = vocabularyRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	
	
}
