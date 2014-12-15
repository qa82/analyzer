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

/**
 * An element in structured information, e.g., an UML class or a WSDL service. Use as parameter for a new analyzer request to get further
 * information on the element.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public class Element extends AbstractInformation {

	/**
	 * An element should support to be used as parameter for further requests on the element. Therefore, the id should be unique and refer
	 * to the concrete element.
	 */
	private String id;

	/**
	 * A new element with default type "element".
	 * 
	 * @param id {@link #id}
	 */
	public Element(String id) {
		super(/* type is */"element");
		this.id = id;
	}

	/**
	 * A new element with id and type.
	 * 
	 * @param id {@link #id}
	 * @param type {@link #type}
	 */
	public Element(String id, String type) {
		super(/* type is */type);
		this.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public Object getValue() {
		return getId();
	}
}
