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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.qa82.analyzer.core.Analyzer;
import org.qa82.analyzer.core.AnalyzerResult;
import org.qa82.analyzer.core.bean.InformationNeed;
import org.qa82.analyzer.core.impl.EmptyInformation;
import org.qa82.analyzer.core.impl.Project;
import org.qa82.analyzer.core.impl.SimpleAnalyzer;
import org.qa82.analyzer.core.impl.SimpleAnalyzerResult;

@Path("information")
public class InformationResource {

	private Analyzer analyzer = new SimpleAnalyzer(new Project());

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AnalyzerResult resolveInformationNeed(InformationNeed informationNeed) {
		try {
			return analyzer.resolve(
					informationNeed.getExpectedInformationType(),
					informationNeed.getParameters());
		} catch (Throwable e) {
			return handleAnalyzerException(e);
		}
	}

	private AnalyzerResult handleAnalyzerException(Throwable e) {
		return new SimpleAnalyzerResult(new EmptyInformation());
	}
}
