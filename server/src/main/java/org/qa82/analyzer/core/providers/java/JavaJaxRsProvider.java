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
import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.Parameters;
import org.qa82.analyzer.core.annotations.Parameter;
import org.qa82.analyzer.core.annotations.ProvidedFunction;
import org.qa82.analyzer.core.bean.InformationNeed;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.impl.AbstractInformationProvider;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.core.impl.EmptyInformation;
import org.qa82.analyzer.core.impl.EmptyParameters;

public class JavaJaxRsProvider extends AbstractInformationProvider {
	
	public JavaJaxRsProvider(Analyzer analyzer) {
		super(analyzer);
	}

	@ProvidedFunction
	public @Parameter(type="REST Services", classType=Element.class) List<Element> getAllRestServices() {
		List<Element> result = new ArrayList<Element>();
		
		for (int i = 0; i < 10; i++) {
			Element e = new Element("Test", "/service/rest");
			result.add(e);
		}
		
		return result;
	}
	
	@ProvidedFunction
	public @Parameter(type="restOperations", classType=Element.class) Object getAllRestServices(@Parameter(type="restService", classType=Element.class) Object restService) {
		return null;	
	}
	
	@ProvidedFunction
	public @Parameter(type="serviceInterface", classType=Element.class) Element getServiceInterfaceOfService(@Parameter(type="restService", classType=Element.class) Element restService) {
		return null;
	}

    @Override
    public Boolean provides(InformationType expectedInformation, Parameters parameters) {
        if ("http://cos.ontoware.org/cos#web-service".equals(expectedInformation.getType())
                && Element.class.equals(expectedInformation.getClassType()))
            return true;
        return false;
    }

    @Override
    public Information resolve(InformationType expoectedInformation, Parameters parameters) {
        // TODO Auto-generated method stub
        return new EmptyInformation();
    }
	@Override
    public Set<InformationNeed> getProvidedInformation() {
		// TODO Auto-generated method stub
		Set<InformationNeed> needs = new HashSet<InformationNeed>();
		InformationNeed need = new InformationNeed();
		need.setExpectedInformationType(new InformationType(Element.class, "http://cos.ontoware.org/cos#web-service"));
		need.setParameters(new EmptyParameters());
		needs.add(need);
		return needs;
	}
	@Override
	public String getDescritpion() {
		return "This provider exemplarily shows the usage of ontologies in the provided information";
	}
	
	@Override
	public String getName() {
		return "ExemplaryJavaJaxRsProvider";
	}
}
