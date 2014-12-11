package org.qa82.analyzer.server.dto;

import java.util.ArrayList;

import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.impl.BooleanInformation;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.StringInformation;

public class ParameterListDto extends ArrayList<InformationDto> {

	private static final long serialVersionUID = -6636508607433176573L;

	public ParameterList convertToParameterList() {
		ParameterList parameters = new ParameterList();
		this.forEach((parameter) -> parameters.add(convert(parameter.getInformationType().getClassType(), parameter.getValue())));
		return parameters;
	}

	private Information convert(Class<? extends Information> classType, String value) {
		if (classType.equals(Element.class)) {
			return new Element(value);
		} else if (classType.equals(BooleanInformation.class)) {
			return new BooleanInformation(Boolean.valueOf(value));
		} else if (classType.equals(StringInformation.class)) {
			return new StringInformation(value);
		}
		throw new RuntimeException("Cast of parameter failed. " + value + " can not be cast to " + classType + ".");

	}

}
