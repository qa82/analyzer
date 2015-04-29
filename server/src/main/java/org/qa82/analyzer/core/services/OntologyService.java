package org.qa82.analyzer.core.services;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.shared.JenaException;

/**
 * This service offers some methods to interact with the qa4swo
 * 
 * @author Pascal Burkhardt
 * @since 28.04.2015
 * 
 */
public class OntologyService {

	private static OntologyService ontologyService = new OntologyService();
	private Logger logger = Logger.getLogger(OntologyService.class.getName());
	
	private OntModel ontology;
	
	private OntologyService() {
		init();
	}
	
	private void init() {
		ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, null);
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream qa4swoAsInputStream = classLoader.getResourceAsStream("ontology/owl/qa4swo.owl");
			ontology.read(qa4swoAsInputStream, "RDF/XML");
		} catch (JenaException je) {
			logger.error("Can't read qa4swo ontology!", je);
		} catch (NullPointerException ex) {
			logger.error("Failed to find onology in resources", ex);
		}
	}
	
	public static OntologyService getInstance() {
		return ontologyService;
	}
		
	public Map<String, Collection<String>> executeSelectQuery(String queryString) {
		Query query = getSelectQuery(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, ontology);
		
		Multimap<String, String> multiMap = ArrayListMultimap.create();
		
		try {
			ResultSet results = qexec.execSelect();
			while(results.hasNext()) {
				QuerySolution solution = results.nextSolution();
				solution.varNames().forEachRemaining((var) -> multiMap.put(var, solution.getResource(var).getLocalName())); 
			}
		} finally {
			qexec.close();
		}
		
		return multiMap.asMap();
	}
	
	private Query getSelectQuery(String queryString) {
		if(Strings.isNullOrEmpty(queryString)) {
			logger.error("Required attribute queryString is null!");
			throw new IllegalArgumentException("Required attribute queryString is null!");
		}
		
		Query query = QueryFactory.create(queryString);
		if(query.getQueryType() != Query.QueryTypeSelect) {
			String error = "Required attribute queryString is not a select query. (queryString=" + queryString + ")";
			logger.error(error);
			throw new RuntimeException(error);
		}
		return query;
	}
	
}
