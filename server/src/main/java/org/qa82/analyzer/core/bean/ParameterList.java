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

import org.qa82.analyzer.core.Information;

/**
 * A list of parameters represented by informations.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 *
 */
public class ParameterList extends ArrayList<Information> {

	private static final long serialVersionUID = 3013857040435548110L;

	public ParametersTypes getTypes() {
		ParametersTypes parametersTypes = new ParametersTypes();
		this.forEach((information) -> parametersTypes.add(new InformationType(information.getClass(), information.getType(), "")));
		return parametersTypes;
	}
}
