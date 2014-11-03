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

import java.util.Optional;

import org.qa82.analyzer.core.Information;

public abstract class AbstractInformation implements Information {

	protected String type;

	public AbstractInformation(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public Boolean isInformationPresent() {
		return getValue() != null;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Optional<Element> getAsElement() {
		return Optional.ofNullable((getValue() != null && getValue() instanceof Element) ? (Element) getValue() : null);
	}

	public Optional<Boolean> getAsBoolean() {
		return Optional.ofNullable((getValue() != null && getValue() instanceof Boolean) ? (Boolean) getValue() : null);
	}

	public Optional<String> getAsString() {
		return Optional.ofNullable((getValue() != null && getValue() instanceof String) ? (String) getValue() : null);
	}

	public Optional<Double> getAsDouble() {
		return Optional.ofNullable((getValue() != null && getValue() instanceof Double) ? (Double) getValue() : null);
	}

}
