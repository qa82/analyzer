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

package org.qa82.analyzer.core.providers.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.AnalyzerResult;
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.Parameters;
import org.qa82.analyzer.core.annotations.Parameter;
import org.qa82.analyzer.core.annotations.ProvidedFunction;
import org.qa82.analyzer.core.bean.InformationNeed;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.exceptions.InformationNeedNotResolvableException;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.EmptyInformation;
import org.qa82.analyzer.core.impl.EmptyParameters;
import org.qa82.analyzer.core.impl.SimpleInformation;
import org.qa82.analyzer.core.impl.SortedParameters;
import org.qa82.analyzer.core.impl.StringInformation;

public class RefinementProvider extends AbstractInformationProvider {
	
	public RefinementProvider(Analyzer analyzer) {
		super(analyzer);
	}
	
	@ProvidedFunction
	@Parameter(type = "test", classType = StringInformation.class)
	public Object doSomething2(@Parameter(type="test",classType=Element.class) Object a) {
        return "L�uft2";
	}
	
	@ProvidedFunction
	@Parameter(type = "test", classType = StringInformation.class)
	public Object doSomething() {
        return "L�uft";
	}
	
	@ProvidedFunction
	@Parameter(type = "operations", classType = Element.class)
    public Information getAllProvidedOperations() throws InformationNeedNotResolvableException {
		List<Element> services = getServicesFromAnalyzer();
		
        List<Element> serviceOperations = new ArrayList<Element>();
        services.forEach((service) -> {
        	InformationType expectedInformation = new InformationType(Element.class, "operation");
        	SortedParameters parameters = new SortedParameters();
        	parameters.add(service);
        	
        	AnalyzerResult result;
			try {
				result = analyzer.resolve(expectedInformation, parameters);
	        	serviceOperations.add((Element) result.getInformation());
			} catch (InformationNeedNotResolvableException e) {
				e.printStackTrace();
			}
        });
			
        return new SimpleInformation(serviceOperations);
	}

    private List<Element> getServicesFromAnalyzer() throws InformationNeedNotResolvableException {
		List<Element> services = new ArrayList<Element>();
        AnalyzerResult result = analyzer.resolve(new InformationType(Element.class, "service"),
				new EmptyParameters());
        if (result.getInformation().isInformationPresent()) {

            // TODO: IMPLEMENT CONVERSION
		}

		return services;
	}
	
	@Override
	public String getDescritpion() {
		return "This refinement provider examplarily shows how to call the analyzer again within an information provider.";
	}
	
	@Override
	public String getName() {
		return "ExemplaryRefinementProvider";
	}

	@Override
	public Information resolve(InformationType expoectedInformation,
			Parameters parameters) {
		return new EmptyInformation();
	}

	@Override
	public Set<InformationNeed> getProvidedInformation() {
		return new HashSet<InformationNeed>();
	}
}
