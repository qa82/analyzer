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
	
	/**
	 * Empty constructor for serialization.
	 */
	public InformationType() {
	}

	/**
	 * Define an information type with class and string.
	 * 
	 * @param classType Java type expected
	 * @param type Type described as string, may be a ontology uri
	 */
	public InformationType(Class<? extends Information> classType, String type) {
		this.classType = classType;
		this.type = type;
	}

	public Class<? extends Information> getClassType() {
		return classType;
	}

	public void setClassType(Class<? extends Information> classType) {
		this.classType = classType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
