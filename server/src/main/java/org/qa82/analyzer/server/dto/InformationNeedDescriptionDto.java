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
package org.qa82.analyzer.server.dto;

import java.util.ArrayList;
import java.util.List;

import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.ParametersTypes;

public class InformationNeedDescriptionDto {

	private InformationTypeDto exptectedInformationType;

	private List<InformationTypeDto> parametersTypes;

	/**
	 * Empty constructor for serialization.
	 */
	public InformationNeedDescriptionDto() {
		super();
	}

	public InformationNeedDescriptionDto(InformationTypeDto exptectedInformationType, List<InformationTypeDto> parametersTypes) {
		super();
		this.exptectedInformationType = exptectedInformationType;
		this.parametersTypes = parametersTypes;
	}

	public InformationNeedDescriptionDto(InformationNeedDescription need) {
		setExptectedInformationType(new InformationTypeDto(need.getExpectedInformationType()));
		List<InformationTypeDto> informationTypeDtos = new ArrayList<InformationTypeDto>();
		need.getParametersTypes().forEach((type) -> informationTypeDtos.add(new InformationTypeDto(type)));
		setParametersTypes(informationTypeDtos);
	}

	public InformationTypeDto getExptectedInformationType() {
		return exptectedInformationType;
	}

	public void setExptectedInformationType(InformationTypeDto exptectedInformationType) {
		this.exptectedInformationType = exptectedInformationType;
	}

	public List<InformationTypeDto> getParametersTypes() {
		return parametersTypes;
	}

	public void setParametersTypes(List<InformationTypeDto> parametersTypes) {
		this.parametersTypes = parametersTypes;
	}

	public InformationNeedDescription convertToInformationNeed() {
		ParametersTypes parametersTypes = new ParametersTypes();
		getParametersTypes().forEach((type) -> parametersTypes.add(type.convertToInformationType()));
		InformationNeedDescription informationNeed = new InformationNeedDescription(getExptectedInformationType().convertToInformationType(), parametersTypes);
		return informationNeed;
	}

}
