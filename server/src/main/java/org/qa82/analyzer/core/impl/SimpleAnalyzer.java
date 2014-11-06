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

import java.util.ArrayList;
import java.util.List;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.InformationProvider;
import org.qa82.analyzer.core.Parameters;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;


public class SimpleAnalyzer implements Analyzer {
	 // Prioritized List
	private List<InformationProvider> providers = new ArrayList<InformationProvider>();
	private Project project;
	
	public SimpleAnalyzer(Project project) {

        this.project = project;
	}
	
	public Project getProject() {
		return project;
	}

	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.Analyzer#setProject(org.qa82.analyzer.core.impl.Project)
	 */
	@Override
	public void setProject(Project project) {
		this.project = project;
	}

	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.Analyzer#resolve(org.qa82.analyzer.core.bean.InformationType, org.qa82.analyzer.core.Parameters)
	 */
	@Override
	public Information resolve(InformationType expectedInformation, Parameters parameters) throws InformationNeedNotResolvableException {
		if (providers.stream().noneMatch((provider) -> provider.provides(expectedInformation, parameters))) {
			throw new InformationNeedNotResolvableException("No provider supports the information need.");
		}

		InformationProvider providerSupportingInformationNeed = providers.stream()
				.filter((provider) -> provider.provides(expectedInformation, parameters)).findFirst().get();
		return providerSupportingInformationNeed.resolve(expectedInformation, parameters);
	}
	
	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.Analyzer#setProviders(java.util.List)
	 */
	@Override
	public void setProviders(List<InformationProvider> providers) {
		this.providers = providers;
	}

	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.Analyzer#addInformationProvider(org.qa82.analyzer.core.impl.AbstractInformationProvider)
	 */
	@Override
	public void addInformationProvider(InformationProvider informationProvider) {
		this.providers.add(informationProvider);
	}

	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.Analyzer#getProviders()
	 */
	@Override
	public List<InformationProvider> getProviders() {
		return providers;
	}

	
}
