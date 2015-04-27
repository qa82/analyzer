package org.qa82.analyzer.core.providers.java.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.AnalyzerResult;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.BooleanInformation;
import org.qa82.analyzer.core.impl.FloatInformation;
import org.qa82.analyzer.core.impl.StringInformation;
import org.qa82.analyzer.server.dto.InformationDto;
import org.qa82.analyzer.server.dto.InformationTypeDto;

/**
 * A refinement provider which calculates the 'REST-UsageOfNoun-metric' based on
 * other information providers.
 * 
 * @author Pascal Burkhardt
 * @since 17.04.2015.
 */
public class RestMetric_UsageOfNounProvider extends AbstractInformationProvider {

	private Logger logger = Logger.getLogger(RestMetric_UsageOfNounProvider.class.getName());

	public RestMetric_UsageOfNounProvider(Analyzer analyzer) {
		super(analyzer);
	}

	@Override
	public String getName() {
		return "RestMetric.UsageOfNoun";
	}

	@Override
	public String getDescription() {
		return "RestMetric.UsageOfNoun";
	}

	@Override
	public InformationNeedDescription getProvidedInformation() {
		return new InformationNeedDescription(new InformationType(FloatInformation.class, "http://qa82.org/qa4swo#metric#usageofnoun", this.getDescription()), 
				new ArrayList<>());
	}

	@Override
	public List<Information> resolve(InformationType expectedInformation,
			ParameterList parameters) {

		ArrayList<Information> informationList = new ArrayList<Information>();

		List<Information> restResourceNames = getResourceNames();

		int countResources = restResourceNames.size();
		if (countResources == 0) {
			logger.info("No resource names were found");
			return informationList;
		}

		List<Information> areNamesNouns = areResourceNamesNouns(restResourceNames);

		int countNouns = 0;
		for (Information isNoun : areNamesNouns) {
			if (Boolean.TRUE.equals((Boolean) isNoun.getValue())) {
				countNouns++;
			}
		}

		// calculate metric
		Float resultOfMetric = (1.0f / countResources) * countNouns;
		informationList.add(new FloatInformation(resultOfMetric));
		return informationList;
	}

	private List<Information> getResourceNames() {
		List<Information> names = new ArrayList<Information>();
		InformationType expectedInformationTypeForResourceNames = new InformationType(StringInformation.class, "http://cos.ontoware.org/cos#web-service#name", "JaxRs.ServiceName");

		try {
			AnalyzerResult result = analyzer.resolve(expectedInformationTypeForResourceNames, new ParameterList(null));
			names = result.getInformation();
		} catch (InformationNeedNotResolvableException e) {
			logger.error("Can't resolve information need", e);
		}

		return names;
	}

	private List<Information> areResourceNamesNouns(List<Information> restResourceNames) {
		List<Information> areNamesNouns = new ArrayList<Information>();
		try {
			InformationType expectedInformationType = new InformationType(BooleanInformation.class, "stringIsNoun", "String.IsNoun");
			
			List<InformationDto> informationDtoList = new ArrayList<InformationDto>();
			restResourceNames
					.forEach((resourceName) -> informationDtoList
							.add(new InformationDto(
									new InformationTypeDto(new InformationType(StringInformation.class,null, null)), 
									resourceName.getValue().toString()
								)
							)
					);
			
			AnalyzerResult result = analyzer.resolve(expectedInformationType,
					new ParameterList(informationDtoList));
			areNamesNouns = result.getInformation();
		} catch (InformationNeedNotResolvableException e) {
			logger.error("Can't resolve information need", e);
		}
		return areNamesNouns;
	}

}
