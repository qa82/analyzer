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

import java.util.Collection;

import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;
import org.qa82.analyzer.core.impl.Project;

public interface Analyzer {

    public AnalyzerResult resolve(InformationType expectedInformation, Parameters parameters)
			throws InformationNeedNotResolvableException;

    public void setProject(Project project);
    
    public Project getProject();

    public void setProviders(Collection<InformationProvider> providers);

    public void addInformationProvider(InformationProvider informationProvider);

    public Collection<InformationProvider> getInformationProviders();

}