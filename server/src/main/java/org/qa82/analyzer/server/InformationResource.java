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

import org.qa82.analyzer.core.AnalyzerResult;
import org.qa82.analyzer.core.bean.InformationType;
import org.qa82.analyzer.core.bean.ParameterList;
import org.qa82.analyzer.server.dto.AnalyzerResultDto;
import org.qa82.analyzer.server.dto.InformationNeedDto;
import org.qa82.analyzer.server.dto.InformationTypeDto;
import org.qa82.analyzer.server.dto.ParameterListDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("information")
public class InformationResource extends AbstractResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AnalyzerResultDto resolveInformationNeed(InformationNeedDto informationNeed) {
		try {
			InformationTypeDto expectedInformationTypeDto = informationNeed.getExpectedInformationType();
			ParameterListDto parameterListDto = informationNeed.getParameterList();

			ParameterList parameterList = new ParameterList();
			InformationType expectedInformationType = new InformationType(null, "", "");

			if (parameterListDto != null) {
				parameterList = parameterListDto.convertToParameterList();
			}

			if (expectedInformationTypeDto != null) {
				expectedInformationType = expectedInformationTypeDto.convertToInformationType();
			}

			AnalyzerResult result = analyzer.resolve(expectedInformationType, parameterList);
			AnalyzerResultDto analyzerResultDto = new AnalyzerResultDto(result);

			return analyzerResultDto;

		} catch (Throwable e) {
			return handleAnalyzerException(e);
		}
	}

	private AnalyzerResultDto handleAnalyzerException(Throwable e) {
		e.printStackTrace();
		return new AnalyzerResultDto();
	}
}
