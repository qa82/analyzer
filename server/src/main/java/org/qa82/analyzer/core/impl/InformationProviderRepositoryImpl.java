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

import java.util.LinkedList;
import java.util.List;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.InformationProvider;
import org.qa82.analyzer.core.InformationProviderRepository;
import org.qa82.analyzer.core.providers.common.String_IsNounProvider;
import org.qa82.analyzer.core.providers.java.JaxRs_ServiceMethodProvider;
import org.qa82.analyzer.core.providers.java.JaxRs_ServiceNameProvider;
import org.qa82.analyzer.core.providers.java.JaxRs_ServiceProvider;
import org.qa82.analyzer.core.providers.java.WebserviceNameProvider;
import org.qa82.analyzer.core.providers.java.rest.RestMetric_UsageOfNounProvider;

public class InformationProviderRepositoryImpl implements InformationProviderRepository {

	/**
	 * Logically should be a set, but InformationProviders then must implement hashcode and equals
	 * or at least comparable for TreeSets. 
	 * LinkedList is ok at this moment.
	 * Watch out! Add an informationprovider only once! 
	 */
	private List<InformationProvider> informationProviders = new LinkedList<InformationProvider>();
	
	public InformationProviderRepositoryImpl(Analyzer analyzer) {
		informationProviders.add(new JaxRs_ServiceProvider(analyzer));
		informationProviders.add(new JaxRs_ServiceNameProvider(analyzer));
		informationProviders.add(new JaxRs_ServiceMethodProvider(analyzer));
		informationProviders.add(new RestMetric_UsageOfNounProvider(analyzer));
		informationProviders.add(new String_IsNounProvider(analyzer));
		informationProviders.add(new WebserviceNameProvider(analyzer));
	}
	
	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.InformationProviderRepository#setInformationProviders(java.util.Set)
	 */
	@Override
	public void setInformationProviders(
			List<InformationProvider> informationProviders) {
		this.informationProviders = informationProviders;
	}
	
	/* (non-Javadoc)
	 * @see org.qa82.analyzer.core.impl.InformationProviderRepository#getInformationProviders()
	 */
	@Override
	public List<InformationProvider> getInformationProviders() {
		return informationProviders;
	}
}
