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

import java.util.List;

/**
 * Common interface for results of an analyzer. May be enhanced to offer additional information such as a trace of the analysis or its
 * duration.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public interface AnalyzerResult {

    public List<Information> getInformation();

}
