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

package org.qa82.analyzer.core.bean;

import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.server.dto.InformationTypeDto;

/**
 * Defines a type of information. Can be used to define the information expected from the analyzer or information provider. By default a
 * single object is expected.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public class InformationType {

	/** The java type of the information. */
	private Class<? extends Information> classType;
	/** The type described as string, may be connected to an ontology. */
	private String type;
	/** The description of the information need */
	private String description;


	/**
	 * Define an information type with class and string.
	 * 
	 * @param classType Java type expected
	 * @param type Type described as string, may be a ontology uri
	 */
	public InformationType(Class<? extends Information> classType, String type, String description) {
		this.setClassType(classType);
		this.setType(type);
		this.setDescription(description);
	}

	public InformationType(InformationTypeDto dto) {
		this(dto.getClassType(), dto.getType(), dto.getDescription());
	}

	public Class<? extends Information> getClassType() {
		return classType;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setClassType(Class<? extends Information> classType) {
		this.classType = classType;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(!(obj instanceof InformationType)) return false;
		InformationType givenObject = (InformationType) obj;
		return (this.getClassType().equals(givenObject.getClassType()) &&
				this.getType().equalsIgnoreCase(givenObject.getType()) &&
				this.getDescription().equalsIgnoreCase(givenObject.getDescription()));
	}
}
