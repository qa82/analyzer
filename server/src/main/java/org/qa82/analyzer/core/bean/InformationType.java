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

	public enum Multiplicity {
		SINGLE, COLLECTION, MAP
	};

	/** The java type of the information. */
	private Class<? extends Information> classType;
	/** The type described as string, may be connected to an ontology. */
	private String type;
	/** The multiplicity of the java type. */
	private Multiplicity multiplicity = Multiplicity.SINGLE;

	/**
	 * Define an information type with class and string. Multiplicity stays default single.
	 * 
	 * @param classType Java type expected
	 * @param type Type described as string, may be a ontology uri
	 */
	public InformationType(Class<? extends Information> classType, String type) {
		this.classType = classType;
		this.type = type;
	}

	/**
	 * Define an information type with class, string and multiplicity.
	 * 
	 * @param classType Java type expected
	 * @param type Type described as string, may be a ontology uri
	 * @param multiplicity How many element of the class
	 */
	public InformationType(Class<? extends Information> classType, String type, Multiplicity multiplicity) {
		this.classType = classType;
		this.type = type;
		this.multiplicity = multiplicity;
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

	public Multiplicity getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(Multiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}

}
