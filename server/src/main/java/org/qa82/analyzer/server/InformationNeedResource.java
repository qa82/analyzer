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

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.qa82.analyzer.core.bean.InformationNeedDescription;
import org.qa82.analyzer.server.dto.InformationNeedDescriptionDto;
import org.qa82.analyzer.server.dto.InformationNeedDto;

@Path("informationneeds")
public class InformationNeedResource extends AbstractResource {

	@Path("provided")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Set<InformationNeedDescriptionDto> getProvidedInformation() {
		
		Set<InformationNeedDescription> providedInformationNeeds = new HashSet<InformationNeedDescription>();

		analyzer.getInformationProviders().stream().filter(provider -> provider.getProvidedInformation().getParametersTypes().size() == 0).forEach(
				(provider) -> providedInformationNeeds.add(provider
						.getProvidedInformation()));

		Set<InformationNeedDescriptionDto> providedInformationNeedDtos = new HashSet<InformationNeedDescriptionDto>();
		providedInformationNeeds.forEach((need) -> providedInformationNeedDtos.add(new InformationNeedDescriptionDto(need)));
		return providedInformationNeedDtos;
	}

	@Path("provided")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Set<InformationNeedDescriptionDto> getProvidedInformation(InformationNeedDescriptionDto informationNeedDescriptionDto) {

		InformationNeedDescription givenInformationNeedDescription = new InformationNeedDescription(informationNeedDescriptionDto);
		Set<InformationNeedDescription> providedInformationNeeds = new HashSet<InformationNeedDescription>();

		analyzer.getInformationProviders().stream().filter(provider -> provider.getProvidedInformation().areParameterTypesEqual(givenInformationNeedDescription)).forEach(
				(provider) -> providedInformationNeeds.add(provider
						.getProvidedInformation()));

		Set<InformationNeedDescriptionDto> providedInformationNeedDtos = new HashSet<InformationNeedDescriptionDto>();
		providedInformationNeeds.forEach((need) -> providedInformationNeedDtos.add(new InformationNeedDescriptionDto(need)));
		return providedInformationNeedDtos;
	}
}
