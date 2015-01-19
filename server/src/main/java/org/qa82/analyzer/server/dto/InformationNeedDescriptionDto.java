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

import org.qa82.analyzer.core.bean.InformationNeedDescription;

import java.util.ArrayList;
import java.util.List;

public class InformationNeedDescriptionDto {

	private InformationTypeDto expectedInformationType;

	private List<InformationTypeDto> parametersTypes;

	/**
	 * Empty constructor for serialization.
	 */
	public InformationNeedDescriptionDto() {
		super();
	}

	public InformationNeedDescriptionDto(InformationTypeDto expectedInformationType, List<InformationTypeDto> parametersTypes) {
		super();
		this.expectedInformationType = expectedInformationType;
		this.parametersTypes = parametersTypes;
	}

	public InformationNeedDescriptionDto(InformationNeedDescription need) {
		setExpectedInformationType(new InformationTypeDto(need.getExpectedInformationType()));
		List<InformationTypeDto> informationTypeDtos = new ArrayList<InformationTypeDto>();
		need.getParametersTypes().forEach((type) -> informationTypeDtos.add(new InformationTypeDto(type)));
		setParametersTypes(informationTypeDtos);
	}

	public InformationTypeDto getExpectedInformationType() {
		return expectedInformationType;
	}

	public List<InformationTypeDto> getParametersTypes() {
		return parametersTypes;
	}

	public void setExpectedInformationType(InformationTypeDto expectedInformationType) {
		this.expectedInformationType = expectedInformationType;
	}
	public void setParametersTypes(List<InformationTypeDto> parametersTypes) {
		this.parametersTypes = parametersTypes;
	}



}
