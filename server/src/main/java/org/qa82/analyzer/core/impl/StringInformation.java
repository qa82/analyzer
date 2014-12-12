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
 * Information with type string.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public class StringInformation extends AbstractInformation {


	private String name;
	private String value;

	public StringInformation(String name, String value) {
		super(/* type is */"string");
		this.setName(name);
		this.setValue(value);
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String getName() {
		return null;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}
}
