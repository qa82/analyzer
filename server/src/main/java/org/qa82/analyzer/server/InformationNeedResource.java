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

package org.qa82.analyzer.server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.bean.InformationNeed;
import org.qa82.analyzer.core.impl.Project;
import org.qa82.analyzer.core.impl.SimpleAnalyzer;

@Path("informationneeds")
public class InformationNeedResource {

	private Analyzer analyzer = new SimpleAnalyzer(new Project());

	@Path("provided")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<InformationNeed> getProvidedInformation() {
		Set<InformationNeed> providedInformationNeeds = new HashSet<InformationNeed>();

		analyzer.getInformationProviders().forEach(
				(provider) -> providedInformationNeeds.addAll(provider
						.getProvidedInformation()));

		return providedInformationNeeds;
	}
}
