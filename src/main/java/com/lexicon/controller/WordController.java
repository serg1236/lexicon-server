package com.lexicon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexicon.model.Vocabulary;
import com.lexicon.model.Word;
import com.lexicon.repository.UserRepository;
import com.lexicon.repository.VocabularyRepository;
import com.lexicon.repository.WordRepository;
import com.lexicon.yandex.Translator;

@RestController
@RequestMapping("/word")
public class WordController {
	
	private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	@Autowired
	private VocabularyRepository vocabularyRepository;
	@Autowired
	private WordRepository wordRepository;
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/translate", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String translate(@RequestParam String word, @RequestParam String from, @RequestParam String to) {
		String wordJson;
		try {
			wordJson = gson.toJson(Translator.getWord(from, to, word));
		} catch (Exception e) {
			wordJson = gson.toJson(new Word());
			e.printStackTrace();
		}
		return wordJson;
	}
	
	@RequestMapping(value="/save")
	public String save(@RequestParam("word") String json, @RequestParam String login) {
		Word word = gson.fromJson(json, Word.class);
		Vocabulary vocabulary = 
				vocabularyRepository.findByFromAndToAndUserLogin(word.getFrom(), word.getTo(), login);
		if(vocabulary == null) {
			
			vocabulary = new Vocabulary();
			vocabulary.setFrom(word.getFrom());
			vocabulary.setTo(word.getTo());
			vocabulary.setUser(userRepository.findByLogin(login));
			vocabulary = vocabularyRepository.save(vocabulary);
		}
		word.setVocabulary(vocabulary);
		wordRepository.save(word);
		return "OK";
	}

	public void setVocabularyRepository(VocabularyRepository vocabularyRepository) {
		this.vocabularyRepository = vocabularyRepository;
	}

	public void setWordRepository(WordRepository wordRepository) {
		this.wordRepository = wordRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	
	
}
