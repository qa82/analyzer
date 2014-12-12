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

package org.qa82.analyzer.core;


/**
 * Information that can be accepted as parameter or returned by an analyzer or information provider. The combination of information and
 * parameters describes an information need.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public interface Information {

	/**
	 * Describes the type of information, e.g., a URI of an ontology object or a SPARQL Query on the ontology.
	 * 
	 * @return the type of the information
	 */
	public String getType();

	/**
	 * Getter for the name of the information
	 *
	 * @return the information name
	 */

	public String getName();

	/**
	 * Getter for the value of the information.
	 *
	 * @return the information value
	 */
	public Object getValue();

}
