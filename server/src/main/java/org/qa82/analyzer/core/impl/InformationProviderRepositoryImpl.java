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
import org.qa82.analyzer.core.InformationProviderRepository;
import org.qa82.analyzer.core.providers.java.JaxRs_ServiceProvider;

import java.util.HashSet;
import java.util.Set;

public class InformationProviderRepositoryImpl implements InformationProviderRepository {

	private Set<InformationProvider> informationProviders = new HashSet<InformationProvider>();
	
	public InformationProviderRepositoryImpl(Analyzer analyzer) {
		informationProviders.add(new JaxRs_ServiceProvider(analyzer));
	}
	
	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.InformationProviderRepository#setInformationProviders(java.util.Set)
	 */
	@Override
	public void setInformationProviders(
			Set<InformationProvider> informationProviders) {
		this.informationProviders = informationProviders;
	}
	
	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.InformationProviderRepository#getInformationProviders()
	 */
	@Override
	public Set<InformationProvider> getInformationProviders() {
		return informationProviders;
	}
}
