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
 * Information with type float.
 * 
 * @author Pascal Burkhardt
 * @since  17.04.2015
 */
public class FloatInformation extends AbstractInformation {

	private Float value;

	public FloatInformation(Float value) {
		super(/* type is */"float");
		this.setValue(value);
	}

	@Override
	public Object getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
}
