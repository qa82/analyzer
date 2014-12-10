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


/**
 * Represents a need for information. This includes the format of the expected
 * information as well as a list of information types for the parameters.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public class InformationNeedDescription {

	private InformationType expectedInformationType;

	private ParametersTypes parametersTypes;

	public InformationNeedDescription(InformationType expectedInformationType, ParametersTypes parameters) {
		setExpectedInformationType(expectedInformationType);
		setParametersTypes(parameters);
	}

	public void setParametersTypes(ParametersTypes parametersTypes) {
		this.parametersTypes = parametersTypes;
	}

	public ParametersTypes getParametersTypes() {
		return parametersTypes;
	}

	public void setExpectedInformationType(InformationType expectedInformationType) {
		this.expectedInformationType = expectedInformationType;
	}

	public InformationType getExpectedInformationType() {
		return expectedInformationType;
	}

}
