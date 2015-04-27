package org.qa82.analyzer.core.providers.common;

import java.util.ArrayList;
import java.util.List;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.BooleanInformation;
import org.qa82.analyzer.core.impl.StringInformation;
import org.qa82.analyzer.core.services.WordNetService;

/**
 * 
 * This information provider checks if a given string is a noun or not. 
 * 
 * @author Pascal Burkhardt
 * @since 17.04.2015
 */
public class String_IsNounProvider extends AbstractInformationProvider {

	public String_IsNounProvider(Analyzer analyzer) {
		super(analyzer);
	}

	@Override
	public String getName() {
		return "String.IsNoun";
	}

	@Override
	public String getDescription() {
		return "String.IsNoun";
	}

	@Override
	public InformationNeedDescription getProvidedInformation() {
		List<InformationType> acceptedInformationTypes = new ArrayList<InformationType>();
		acceptedInformationTypes.add(new InformationType(StringInformation.class, null, null));
		return new InformationNeedDescription(new InformationType(BooleanInformation.class, "stringIsNoun", this.getDescription()),
				acceptedInformationTypes);
	}

	@Override
	public List<Information> resolve(InformationType expectedInformation, ParameterList parameters) {
		ArrayList<Information> informationList = new ArrayList<>();

		for(Information param : parameters) {
			if(param.getValue() != null && param.getValue() instanceof String) {
				String name = param.getValue().toString();
				informationList.add(new BooleanInformation(isNoun(name)));
			}
		}
		
		return informationList;
	}

	private boolean isNoun(String word) {
		if(word == null || isNotARegularWord(word)) {
			return false;
		}
		
		return WordNetService.getInstance().isNoun(word);
	}

	private boolean isNotARegularWord(String word) {
		return word.matches(".*[^a-zA-Z- ].*");
	}
}
