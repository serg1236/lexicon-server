package com.lexicon.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value="/save", method={RequestMethod.POST, RequestMethod.GET})
	public String save(@RequestParam("word") String json, @RequestParam("login") String login) {
		Word word = gson.fromJson(json, Word.class);
		Word foundWord = wordRepository.findByNameIgnoreCaseAndFromAndToAndVocabularyCustomerFbLogin(word.getName(),word.getFrom(), word.getTo(), login);
		Vocabulary vocabulary = 
				vocabularyRepository.findByFromAndToAndCustomerFbLogin(word.getFrom(), word.getTo(), login);
		if(foundWord != null) {
			System.out.println("word exists already. Deleting...");
			wordRepository.delete(foundWord);
		}
		if(vocabulary == null) {
			vocabulary = new Vocabulary();
			vocabulary.setFrom(word.getFrom());
			vocabulary.setTo(word.getTo());
			User customer = userRepository.findByFbLogin(login);
			if(customer == null) {
				System.out.println("Cannot find user!");
			}
			vocabulary.setCustomer(customer);
			vocabulary = vocabularyRepository.save(vocabulary);
		}
		word.setVocabulary(vocabulary);
		wordRepository.save(word);
		return "OK";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("word") String json, @RequestParam("login") String login) {
		Word word = gson.fromJson(json, Word.class);
		Word foundWord = wordRepository.findByNameIgnoreCaseAndFromAndToAndVocabularyCustomerFbLogin(word.getName(),word.getFrom(), word.getTo(), login);
		if(foundWord != null) {
			wordRepository.delete(foundWord);
		}
		return "OK";
	}
	
	@RequestMapping(value="/filter",  produces="text/plain;charset=UTF-8")
	public String filter(@RequestParam String from, @RequestParam String to, @RequestParam String category, @RequestParam String login) {
		Vocabulary vocabulary = vocabularyRepository.findByFromAndToAndCustomerFbLogin(from, to, login);
		if(vocabulary != null && vocabulary.getWords() != null) {
			VocabularyController.extractCategories(vocabulary);
			if(! "all".equals(category.toLowerCase())) {
				List<Word> words = vocabulary.getWords();
				Iterator<Word> iter = words.iterator();
				while(iter.hasNext()) {
					Word word = iter.next();
					if(word.getCategories() == null || !word.getCategories().contains(category)) {
						iter.remove();
					}
				}
			}
		}
		return gson.toJson(vocabulary);
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
