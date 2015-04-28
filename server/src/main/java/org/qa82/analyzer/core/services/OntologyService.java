package org.qa82.analyzer.core.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.shared.JenaException;

/**
 * This service offers some methods to interact with the qa4swo ontology
 * 
 * @author Pascal Burkhardt
 * @since 28.04.2015
 * 
 */
public class OntologyService {

	private static OntologyService ontologyService = new OntologyService();
	private Logger logger = Logger.getLogger(WordNetService.class.getName());
	
	private OntModel ontology;
	
	private OntologyService() {
		init();
	}
	
	private void init() {
		ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, null);
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream qa4swoAsInputStream = classLoader.getResourceAsStream("ontology/owl/qa4swo");
			ontology.read(qa4swoAsInputStream, "RDF/XML");
		} catch (JenaException je) {
			logger.error("Can't read qa4swo ontology!", je);
		}
	}
	
	public static OntologyService getInstance() {
		return ontologyService;
	}
	
	public List<String> getRESTResourceNameImplementations() {
		return new ArrayList<String>();
	}
	
}
