package org.qa82.analyzer.core.services;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.WordnetStemmer;

/**
 * This service loads the WordNet-3.0 dictionary from the Princeton University  
 * 
 * @author Pascal Burkhardt
 * @since  18.04.2015
 */
public class WordNetService {

	private IDictionary dictionary;
	private WordnetStemmer stemmer;
	
	private static WordNetService wordNetService = new WordNetService();
	private Logger logger = Logger.getLogger(WordNetService.class.getName());
	
	private WordNetService() { 
		init();
	}
	
	private void init() {
		ClassLoader classLoader = getClass().getClassLoader();
		File folder = new File(classLoader.getResource("wordnet/dict").getFile());
		dictionary = new Dictionary(folder);
		stemmer = new WordnetStemmer(dictionary);
	}
	
	public static WordNetService getInstance() {
		return wordNetService;
	}
	
	public boolean isNoun(String s) {
		if(s == null) {
			return false;
		}
		
		if(!dictionary.isOpen()) {
			openDictionary();
		}
		
		boolean isNoun = isInDictionaryAsNoun(s);
		
		if(!isNoun) {
			for(String stem : stemmer.findStems(s, POS.NOUN)) {
				isNoun = isInDictionaryAsNoun(stem);
				if(isNoun) {
					break;
				}
			}
		}
				
		dictionary.close();
		
		return isNoun;
	}
	
	private boolean isInDictionaryAsNoun(String s) {
		IIndexWord idxWord = dictionary.getIndexWord(s, POS.NOUN);
		return idxWord != null;
	}

	private void openDictionary() {
		try {
			dictionary.open();
		} catch (IOException e) {
			logger.error("Can't open wordnet dictionary",e);
		}
	}
}