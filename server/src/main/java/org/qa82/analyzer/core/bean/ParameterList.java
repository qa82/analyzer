/*******************************************************************************
* Copyright (c) 2014 Michael Gebhart (michael.gebhart@qa82.org).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
* Michael Gebhart - initial idea and concept
* 
*******************************************************************************/
package org.qa82.analyzer.core.bean;

import java.util.ArrayList;
import java.util.List;

import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.impl.BooleanInformation;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.FloatInformation;
import org.qa82.analyzer.core.impl.StringInformation;
import org.qa82.analyzer.server.dto.InformationDto;

/**
 * A list of parameters represented by informations.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 *
 */
public class ParameterList extends ArrayList<Information> {

	private static final long serialVersionUID = 3013857040435548110L;

	public List<InformationType> getTypes() {
		List<InformationType> parametersTypes = new ArrayList<>();
		this.forEach((information) -> parametersTypes.add(new InformationType(information.getClass(), information.getType(), "")));
		return parametersTypes;
	}

	public ParameterList(List<InformationDto> informationDtoList) {
		super();
		if(informationDtoList != null) {
			informationDtoList.forEach((parameter) -> this.add(convert(parameter.getInformationType().getClassType(), parameter.getValue())));
		}
	}

	private Information convert(Class<? extends Information> classType, String value) {
		if (classType.equals(Element.class)) {
			return new Element(value);
		} else if (classType.equals(BooleanInformation.class)) {
			return new BooleanInformation(Boolean.valueOf(value));
		} else if (classType.equals(StringInformation.class)) {
			return new StringInformation(value);
		} else if (classType.equals(FloatInformation.class)) {
			return new FloatInformation(Float.valueOf(value));
		}
		throw new RuntimeException("Cast of parameter failed. " + value + " can not be cast to " + classType + ".");

	}
}
