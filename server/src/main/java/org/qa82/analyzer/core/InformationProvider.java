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

package org.qa82.analyzer.core;

import java.util.List;

import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.bean.ParametersTypes;

/**
 * An information provider can resolve an information need for a certain software. Information provider can be separated into technology
 * providers and refinement providers. Technology providers gather information from artifacts of the software. Refinement providers gather
 * information by refining the information need and requesting new information from the analyzer.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public interface InformationProvider {
	
	public String getName();
	
	public String getDescription();

	public Boolean provides(InformationType expectedInformation, ParametersTypes parameters);

	public List<Information> resolve(InformationType expectedInformation, ParameterList parameters);

    public InformationNeedDescription getProvidedInformation();

}