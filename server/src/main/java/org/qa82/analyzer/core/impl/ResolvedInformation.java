package org.qa82.analyzer.core.impl;

import java.util.Optional;

public class ResolvedInformation {

	/**
	 * The information resolved by an analyzer. The resolved informationen corresponds to an information need.
	 */
	private Object information;
	
	public ResolvedInformation(Object information) {
		this.information = information;
	}
	
	public Object getInformation() {
		return information;
	}
	
	public Optional<Element> getAsElement() {
		return Optional.ofNullable((information != null && information instanceof Element) ? (Element) information : null);   
	}
	
	public Optional<Boolean> getAsBoolean() {
		return Optional.ofNullable((information != null && information instanceof Boolean) ? (Boolean) information : null);   
	}
	
	public Optional<String> getAsString() {
		return Optional.ofNullable((information != null && information instanceof String) ? (String) information : null);   
	}
	
	public Optional<Double> getAsDouble() {
		return Optional.ofNullable((information != null && information instanceof Double) ? (Double) information : null);   
	}
}
