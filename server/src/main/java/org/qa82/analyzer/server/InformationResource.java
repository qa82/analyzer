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
import org.qa82.analyzer.core.impl.AbstractInformation;
import org.qa82.analyzer.core.impl.Element;
import org.qa82.analyzer.server.dto.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("information")
public class InformationResource extends AbstractResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AnalyzerResultDto resolveInformationNeed(InformationNeedDto informationNeed) {
		try {
			InformationTypeDto expectedInformationTypeDto = informationNeed.getExpectedInformationType();
			ArrayList<InformationDto> parameterListDto = informationNeed.getParameterList();

			ParameterList parameterList = new ParameterList(parameterListDto);
			InformationType expectedInformationType = new InformationType(null, "", "");

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

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public InformationNeedDto doSomething() {

		InformationType t = new InformationType(Element.class,"bla","bla2");
		InformationDto t2 = new InformationDto(new InformationTypeDto(t), "hallo");
		ArrayList<InformationDto> t3 = new ArrayList<InformationDto>();
		t3.add(t2);
		//t3.add(t2);
		//return p;
		return new InformationNeedDto(new InformationTypeDto(t), t3);
	}

	private AnalyzerResultDto handleAnalyzerException(Throwable e) {
		e.printStackTrace();
		return new AnalyzerResultDto();
	}
}
