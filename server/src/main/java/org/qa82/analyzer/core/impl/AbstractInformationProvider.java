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

package org.qa82.analyzer.core.impl;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.InformationProvider;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParametersTypes;

/**
 * Offers a default implementation InformationProviders using the annotations @ProvidedFunction and @Parameter.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public abstract class AbstractInformationProvider implements InformationProvider {
	protected Analyzer analyzer;
	
	public AbstractInformationProvider(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	@Override
	//TODO: Constraint need to be fixed
	public Boolean provides(InformationType expectedInformation,
 ParametersTypes parameters) {
		if (expectedInformation == null) {
			return false;
		}
		return isInformationneedEqual(expectedInformation, parameters);
	}

	protected Boolean isInformationneedEqual(InformationType expectedInformation,
 ParametersTypes parameters) {
		boolean isEqualClassType = getProvidedInformation().getExpectedInformationType().getClassType().equals(expectedInformation.getClassType());
		boolean isEqualType = getProvidedInformation().getExpectedInformationType().getType().equals(expectedInformation.getType());
		return isEqualClassType && isEqualType;
	}
}
