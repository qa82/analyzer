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

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.qa82.analyzer.core.InformationProvider;

@Path("informationproviders")
public class InformationProviderResource extends AbstractResource {

	@GET
	public Collection<InformationProvider> getInformationProviders() {
		return analyzer.getInformationProviders();
	}
}
