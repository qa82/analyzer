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
import java.util.Arrays;
import java.util.List;

import org.qa82.analyzer.server.dto.InformationNeedDescriptionDto;

/**
 * Represents a need for information. This includes the format of the expected
 * information as well as a list of information types for the parameters.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 * @author Pascal Giessler, Karlsruhe Institute of Technology, Germany
 */
public class InformationNeedDescription {

	private InformationType expectedInformationType;

	private List<InformationType> parametersTypes;

	public InformationNeedDescription(InformationType expectedInformationType, List<InformationType> parameters) {
		this.expectedInformationType = expectedInformationType;
		this.parametersTypes = parameters;
	}

	public InformationNeedDescription(InformationNeedDescriptionDto dto) {
		this.parametersTypes = new ArrayList<>();
		dto.getParametersTypes().forEach(parameterType -> {this.parametersTypes.add(new InformationType(parameterType));});
		this.expectedInformationType = new InformationType(dto.getExpectedInformationType());
	}

	public List<InformationType> getParametersTypes() {
		return parametersTypes;
	}

	public InformationType getExpectedInformationType() {
		return expectedInformationType;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(!(obj instanceof InformationNeedDescription)) return false;
		InformationNeedDescription givenInformationNeedDescription = (InformationNeedDescription) obj;

		return isInformationExpectedEqual(givenInformationNeedDescription) && areParameterTypesEqual(givenInformationNeedDescription);
	}

	public boolean isInformationExpectedEqual(InformationNeedDescription informationNeedDesc) {
		return expectedInformationType.equals(informationNeedDesc.getExpectedInformationType());
	}

	private boolean isParameterSizeEqual(InformationNeedDescription informationNeedDesc) {
		return (informationNeedDesc.getParametersTypes().size() == this.getParametersTypes().size());
	}

	public boolean areParameterTypesEqual(InformationNeedDescription informationNeedDesc) {
		if(!isParameterSizeEqual(informationNeedDesc)) {
			return false;
		}

		Boolean equalParameters[] = new Boolean[this.getParametersTypes().size()];
		Arrays.fill(equalParameters, Boolean.FALSE);
		for(int i = 0; i < parametersTypes.size(); i++) {
			for (InformationType givenParameterType : informationNeedDesc.getParametersTypes()) {
				if(parametersTypes.get(i).equals(givenParameterType)) {
					equalParameters[i] = Boolean.TRUE;
					continue;
				}
			}
			if(equalParameters[i] == Boolean.FALSE) return false;
		}
		return true;
	}
}
