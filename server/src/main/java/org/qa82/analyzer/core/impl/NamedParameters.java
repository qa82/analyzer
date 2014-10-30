package org.qa82.analyzer.core.impl;

import java.util.HashMap;

import org.qa82.analyzer.core.Parameters;

/**
 * A parameter set. The String defines the type, e.g. as a uri, of the added object.
 * Uses the java.util HashMap implementation.
 * 
 * @author Roland Steinegger, Karlsruhe Institute of Technology, Germany
 *
 */
public class NamedParameters extends HashMap<String, Object> implements Parameters {

	private static final long serialVersionUID = -6904390323564972714L;
}
