package org.qa82.analyzer.core.providers.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.Repository;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.bean.ParametersTypes;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.providers.java.parser.jaxrs.JaxRs_Compatibility;
import org.qa82.analyzer.core.providers.java.parser.jaxrs.JaxRs_Parser;

public class JaxRs_ServiceMethodProvider extends AbstractInformationProvider {

	private JaxRs_Compatibility parser;

	public JaxRs_ServiceMethodProvider(Analyzer analyzer) {
		super(analyzer);
		this.parser = new JaxRs_Parser();
	}

	@Override
	public String getName() {
		return "JaxRs.Service.Method";
	}

	@Override
	public String getDescription() {
		return "Get the methods of a specified JAX-RS service in the current repository";
	}

	@Override
	public List<Information> resolve(InformationType expectedInformation, ParameterList parameters) {
		// TODO: Information providers should be able to throw
		// InformationNotResolvableException, if the parameters or the expected
		// information are not supported
		if (parameters.size() > 1)
			throw new RuntimeException("The provider can resolve only one web service at a time.");
		if (parameters.size() < 1)
			throw new RuntimeException("The provider needs the web service to get methods of.");
		if (parameters.get(0).getType().equals("http://cos.ontoware.org/cos#web-service"))
			throw new RuntimeException("The provider needs a parameter of type web service to get methods of.");
		String webServiceId = (String) parameters.get(0).getValue();

		ArrayList<Information> informationList = new ArrayList<>();
		Integer id = 0;
		Set<Repository> repositories = this.analyzer.getProject().getRepositories();
		for (Repository repository : repositories) {
			for (File javaFile : repository.searchFileEndingWith("java")) {
				parser.parseFile(javaFile);
				if (parser.isFileAJaxRsClass()) {
					id++;
				}
				if (id.toString().equals(webServiceId)) {
					parser.getSupportedMethods().forEach((method) -> {
						informationList.add(new Element(webServiceId + "#" + method.name(), "http://cm.tm.kit.edu/ws#method"));
					});
				}
			}
		}
		return informationList;
	}

	@Override
	public InformationNeedDescription getProvidedInformation() {
		ParametersTypes parametersTypes = new ParametersTypes();
		parametersTypes.add(new InformationType(Element.class, "http://cos.ontoware.org/cos#web-service", "JaxRs.Service"));
		InformationType informationType = new InformationType(Element.class, "http://cm.tm.kit.edu/ws#method", getDescription());
		return new InformationNeedDescription(informationType, parametersTypes);
	}
}
