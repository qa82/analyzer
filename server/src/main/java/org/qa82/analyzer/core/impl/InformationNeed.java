package org.qa82.analyzer.core.impl;

public class InformationNeed {
	
	private String need;
	
	/**
	 * Defines a needed information, that can be resolved by an analyzer.
	 *
	 * @param need the needed information, for example the URI of an ontology object
	 */
	public InformationNeed(String need) {
		this.need = need;
	}

	public String getNeed() {
		return need;
	}

	public void setNeed(String need) {
		this.need = need;
	}

}
