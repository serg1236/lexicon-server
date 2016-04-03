package com.lexicon.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexicon.model.User;
import com.lexicon.model.Vocabulary;
import com.lexicon.model.Word;
import com.lexicon.repository.UserRepository;
import com.lexicon.repository.VocabularyRepository;

@RestController
@RequestMapping("/vocabulary")
public class VocabularyController {
	
	@Autowired
	private VocabularyRepository vocabularyRepository;
	@Autowired
	private UserRepository userRepository;
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	@RequestMapping(value="getAll", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getAll(@RequestParam String login) {
		User user = userRepository.findByFbLogin(login);
		List<Vocabulary> vocabularies = null;
		if(user != null) {
			try {
				vocabularies = vocabularyRepository.findByCustomer(user);
				Iterator<Vocabulary> iter = vocabularies.iterator();
				while(iter.hasNext()) {
					Vocabulary vocabulary = iter.next();
					if(vocabulary.getWords() == null || vocabulary.getWords().isEmpty()) {
						iter.remove();
					} else {
						extractCategories(vocabulary);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
		} else {
			user = new User();
			user.setFbLogin(login);
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
	
	private void extractCategories(Vocabulary vocabulary) {
		Set<String> categories = new HashSet<String>();
		if(vocabulary.getWords() != null) {
			for(Word word: vocabulary.getWords()) {
				if(word.getCategories() != null){
					for(String category: word.getCategories()) {
						categories.add(category);
					}
				}
			}
		}
		vocabulary.setCategories(categories);
	}
	
}
