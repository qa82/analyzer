package org.qa82.analyzer.core.services;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map;

import org.junit.Test;

public class OntologyServiceTests {

	private OntologyService service = OntologyService.getInstance();
			
	@Test
	public void testExecuteSelectQuery() {
		Map<String, Collection<String>>	map = service.executeSelectQuery("SELECT ?spec WHERE { ?spec <http://www.qa82.com/ontologies/qa4swo.owl#specifies> <http://www.qa82.com/ontologies/qa4swo.owl#REST> }");
		boolean containsJaxRs = false;
		for(String specs : map.get("spec")) {
			if(specs.equalsIgnoreCase("jaxrs")) {
				containsJaxRs = true;
				break;
			}
		}
		assertTrue(containsJaxRs);
		
		service.executeSelectQuery("SELECT ?neverUsed WHERE { ?spec <http://www.qa82.com/ontologies/qa4swo.owl#specifies> <http://www.qa82.com/ontologies/qa4swo.owl#REST> }");
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testIllegalArgumentExceptionForQueryString() {
		service.executeSelectQuery(null);
	}

}
