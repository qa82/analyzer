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

import java.util.HashMap;

import org.qa82.analyzer.core.Information;
import org.qa82.analyzer.core.Parameters;

/**
 * A parameter set. The String may define the type, i.e. as a uri, of the added
 * object or semantic information for an information provider, i.e.,
 * predecessor, successor). Uses the java.util HashMap implementation.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 *
 */
public class NamedParameters extends HashMap<String, Information> implements Parameters {

	private static final long serialVersionUID = -6904390323564972714L;
}
