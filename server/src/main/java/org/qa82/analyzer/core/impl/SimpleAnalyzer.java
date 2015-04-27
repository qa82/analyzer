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

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.AnalyzerResult;
import org.qa82.analyzer.core.InformationProvider;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.core.bean.Project;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;

/**
 * Simple analyzer implementation that goes through all providers and calls the
 * first matching. Thus, no merging between results of different providers
 * supporting the same need is implemented.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public class SimpleAnalyzer implements Analyzer {

	private List<InformationProvider> providers = new LinkedList<InformationProvider>();
	private Project project;

	public SimpleAnalyzer(Project project) {
		this.project = project;
		InformationProviderRepositoryImpl providerRepository = new InformationProviderRepositoryImpl(this);
		providers.addAll(providerRepository.getInformationProviders());
	}

	@Override
	public Project getProject() {
		return project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.qa82.analyzer.core.impl.Analyzer#setProject(org.qa82.analyzer.core
	 * .impl.Project)
	 */
	@Override
	public void setProject(Project project) {
		this.project = project;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.qa82.analyzer.core.impl.Analyzer#resolve(org.qa82.analyzer.core.bean
	 * .InformationType, org.qa82.analyzer.core.Parameters)
	 */
	@Override
	public AnalyzerResult resolve(InformationType expectedInformation, ParameterList parameters) throws InformationNeedNotResolvableException {
		if (providers.stream().noneMatch((provider) -> provider.provides(expectedInformation, parameters.getTypes()))) {
			throw new InformationNeedNotResolvableException("No provider supports the information need.");
		}

		InformationProvider providerSupportingInformationNeed = providers.stream()
				.filter((provider) -> provider.provides(expectedInformation, parameters.getTypes())).findFirst().get();
		AnalyzerResult analyzerResult = new SimpleAnalyzerResult(providerSupportingInformationNeed.resolve(expectedInformation, parameters));
		return analyzerResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.qa82.analyzer.core.impl.Analyzer#setProviders(java.util.Collection)
	 */
	@Override
	public void setProviders(Collection<InformationProvider> providers) {
		this.providers = new LinkedList<InformationProvider>(providers);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.qa82.analyzer.core.impl.Analyzer#addInformationProvider(org.qa82.
	 * analyzer.core.impl.AbstractInformationProvider)
	 */
	@Override
	public void addInformationProvider(InformationProvider informationProvider) {
		this.providers.add(informationProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.qa82.analyzer.core.impl.Analyzer#getInformationProviders()
	 */
	@Override
	public List<InformationProvider> getInformationProviders() {
		return providers;
	}
}
