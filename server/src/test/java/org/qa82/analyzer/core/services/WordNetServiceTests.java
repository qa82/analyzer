package org.qa82.analyzer.core.services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class WordNetServiceTests {

	private WordNetService service = WordNetService.getInstance();
		
	@Test
	public void testIsNoun() {
		assertTrue(service.isNoun("information"));
		assertTrue(service.isNoun("provider"));
		assertTrue(service.isNoun("user"));
		assertTrue(service.isNoun("resource"));
		assertTrue(service.isNoun("data"));
		assertTrue(service.isNoun("item"));
		assertTrue(service.isNoun("dog"));
		
		assertTrue(service.isNoun("users"));
		assertTrue(service.isNoun("cats"));
		
		assertFalse(service.isNoun(null));
		assertFalse(service.isNoun("myresource"));
		assertFalse(service.isNoun("getUserByName"));
		assertFalse(service.isNoun("postUser"));
		assertFalse(service.isNoun("information2"));
	}

}
