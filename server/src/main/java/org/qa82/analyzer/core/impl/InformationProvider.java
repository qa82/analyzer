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

import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.Parameters;

public abstract class InformationProvider {
	protected Analyzer analyzer;
	
	public InformationProvider(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	
	public abstract Boolean provides(Information expectedInformation, Parameters parameters);
	
	public abstract Information resolve(Information expoectedInformation, Parameters parameters);
}
