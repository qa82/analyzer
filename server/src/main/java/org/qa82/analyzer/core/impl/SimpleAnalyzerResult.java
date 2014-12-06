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

import org.qa82.analyzer.core.AnalyzerResult;
import org.qa82.analyzer.core.Information;

/**
 * A simple result of an analysis including only the resolved information.
 *
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 */
public class SimpleAnalyzerResult implements AnalyzerResult {

    private Information resolvedInformation;

    public SimpleAnalyzerResult(Information resolvedInformation) {
        this.resolvedInformation = resolvedInformation;
    }

    @Override
    public Information getInformation() {
        return resolvedInformation;
    }

}
