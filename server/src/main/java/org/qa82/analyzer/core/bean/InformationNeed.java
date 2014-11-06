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

import org.qa82.analyzer.core.Parameters;

/**
 * Represents a need for information. This includes the format of the expected information as well as a list of parameters.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public class InformationNeed {

	private InformationType expectedInformationType;

	private Parameters parameters;

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setExpectedInformationType(InformationType expectedInformationType) {
		this.expectedInformationType = expectedInformationType;
	}

	public InformationType getExpectedInformationType() {
		return expectedInformationType;
	}

}
