package org.qa82.analyzer.core.providers.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.AnalyzerResult;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.StringInformation;
import org.qa82.analyzer.core.services.OntologyService;

public class WebserviceNameProvider extends AbstractInformationProvider {
	
	private Logger logger = Logger.getLogger(WebserviceNameProvider.class.getName());
	
	public WebserviceNameProvider(Analyzer analyzer) {
		super(analyzer);
	}

	@Override
	public String getName() {
		return "Webservice.Name";
	}

	@Override
	public String getDescription() {
		return "Webservice.Name";
	}

	@Override
	public InformationNeedDescription getProvidedInformation() {
		return new InformationNeedDescription(new InformationType(StringInformation.class, "http://cos.ontoware.org/cos#web-service#name", this.getDescription()),
				new ArrayList<>());
	}

	@Override
	public List<Information> resolve(InformationType expectedInformation, ParameterList parameters) {
		ArrayList<Information> informationList = new ArrayList<>();

		Map<String, Collection<String>>	map = OntologyService.getInstance().executeSelectQuery("SELECT ?spec WHERE { ?spec <http://www.qa82.com/ontologies/qa4swo.owl#specifies> <http://www.qa82.com/ontologies/qa4swo.owl#REST> }");
		for(String spec : map.get("spec")) {
			InformationType expectedInformationTypeForResourceNames = new InformationType(StringInformation.class, "http://qa82.com/ontologies/qa4swo.owl#resource#name#" + spec, null);

			try {
				AnalyzerResult result = analyzer.resolve(expectedInformationTypeForResourceNames, new ParameterList(null));
				informationList.addAll(result.getInformation());
			} catch (InformationNeedNotResolvableException e) {
				logger.error("Can't resolve information need", e);
			}
		}
		
		return informationList;
	}
}
