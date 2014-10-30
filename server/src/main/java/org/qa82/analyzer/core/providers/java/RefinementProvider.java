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
import java.util.List;

import org.qa82.analyzer.core.annotations.Parameter;
import org.qa82.analyzer.core.annotations.ProvidedFunction;
import org.qa82.analyzer.core.impl.Analyzer;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.InformationNeed;
import org.qa82.analyzer.core.impl.InformationProvider;
import org.qa82.analyzer.core.impl.Parameters;

public class RefinementProvider extends InformationProvider {
	
	public RefinementProvider(Analyzer analyzer) {
		super(analyzer);
	}
	
	@ProvidedFunction
	public @Parameter(uri="test2") Object doSomething2(@Parameter(uri="") Object a) {
		return "L�uft2";
	}
	
	@ProvidedFunction
	public @Parameter(uri="test") Object doSomething(@Parameter(uri="") Object a) {
		return "L�uft";
	}
	
	@ProvidedFunction
	public @Parameter(uri="operations") List<Element> getAllProvidedOperations() {
		
		List<Element> services = analyzer.getList("services");
		
		System.out.println(analyzer.getProject().getRepository());
		
		if (services == null) return null;
		
		List<Element> operations = new ArrayList<Element>();
		
		for (Element service : services) {
			Element i = analyzer.getElement("serviceInterface", "service", service);
			
			if (i == null) continue;
			
			List<Element> currentOperations = analyzer.getList("providedOperations", "serviceInterface", i);
			
			if (currentOperations != null) operations.addAll(currentOperations);
		}
		
		return operations;
	}

	@Override
	public Boolean supports(InformationNeed informationNeed,
			Parameters parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object resolve(InformationNeed informationNeed, Parameters parameters) {
		// TODO Auto-generated method stub
		return null;
	}
}
